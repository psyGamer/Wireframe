package dev.psygamer.wireframe.api.block.state.property;

import dev.psygamer.wireframe.util.ICloneable;
import dev.psygamer.wireframe.util.IFreezable;
import dev.psygamer.wireframe.util.collection.FreezableMap;
import dev.psygamer.wireframe.util.collection.FreezableLinkedHashMap;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Objects;

public class BlockProperty <T> implements IFreezable {
	
	protected final String propertyName;
	protected final FreezableMap<String, T> entries;
	
	protected T defaultValue;
	
	public BlockProperty(final String propertyName) {
		Objects.requireNonNull(propertyName, "The property name may not be null");
		
		this.propertyName = propertyName;
		this.entries = new FreezableLinkedHashMap<>();
	}
	
	public String getPropertyName() {
		return this.propertyName;
	}
	
	public T getValue(final String valueName) {
		if (this.entries.containsValue(valueName))
			return this.entries.get(valueName);
		
		return null;
	}
	
	public String getValueName(final T value) {
		for (final String valueName : this.entries.keySet()) {
			if (this.entries.get(valueName).equals(value))
				return valueName;
		}
		
		return null;
	}
	
	protected void addEntry(final String valueName, final T value) {
		Objects.requireNonNull(valueName, "Value name can not be null names");
		Objects.requireNonNull(value, "Value can not be null names");
		
		if (this.entries.containsKey(valueName))
			throw new NameAlreadyDefinedException(valueName);
		if (this.entries.containsValue(value))
			throw new ValueAlreadyDefinedException(value.toString());
		
		this.entries.put(valueName, value);
	}
	
	public T getDefaultValue() {
		return this.defaultValue;
	}
	
	protected void setDefaultValue(final T defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public ImmutableList<T> getPossibleValues() {
		return ImmutableList.copyOf(this.entries.values());
	}
	
	public int getValueIndex(final T value) {
		return new ArrayList<>(this.entries.values()).indexOf(value);
	}
	
	@Override
	public void freeze() {
		this.entries.freeze();
	}
	
	@Override
	public boolean isFrozen() {
		return this.entries.isFrozen();
	}
	
	public static final class ValuePair <T> implements IFreezable, ICloneable<ValuePair<T>> {
		private final BlockProperty<T> property;
		
		private volatile boolean frozen = false;
		private int valueIndex;
		private T value;
		
		public ValuePair(final BlockProperty<T> property) {
			this(property, property.getDefaultValue());
		}
		
		public ValuePair(final BlockProperty<T> property, final T value) {
			this.property = property;
			this.value = value;
			
			this.valueIndex = property.getValueIndex(value);
		}
		
		public BlockProperty<T> getProperty() {
			return this.property;
		}
		
		public T getValue() {
			return this.value;
		}
		
		public void setValue(final T value) {
			if (!this.property.getPossibleValues().contains(value))
				throw new IllegalArgumentException("This value is not supported by this property");
			
			IFreezable.throwIfFrozen(this);
			
			this.value = value;
			this.valueIndex = this.property.getValueIndex(value);
		}
		
		public void setObjectValue(final Object value) {
			setValue((T) value);
		}
		
		public int getValueIndex() {
			return this.valueIndex;
		}
		
		@Override
		public ValuePair<T> copy() {
			return new ValuePair<>(this.property, this.value);
		}
		
		@Override
		public void freeze() {
			this.frozen = true;
		}
		
		@Override
		public boolean isFrozen() {
			return this.frozen;
		}
	}
	
	public static final class NameAlreadyDefinedException extends RuntimeException {
		
		public NameAlreadyDefinedException(final String name) {
			super("The name '" + name + "' is already defined");
		}
	}
	
	public static final class ValueAlreadyDefinedException extends RuntimeException {
		
		public ValueAlreadyDefinedException(final String stringValue) {
			super("The value '" + stringValue + "' is already defined");
		}
	}
}
