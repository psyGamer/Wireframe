package dev.psygamer.wireframe.block.state.property;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.internal.block.InternalBlockProperty;
import dev.psygamer.wireframe.util.collection.FreezableLinkedHashMap;
import dev.psygamer.wireframe.util.collection.FreezableMap;
import dev.psygamer.wireframe.util.helper.ICloneable;
import dev.psygamer.wireframe.util.helper.IFreezable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class BlockProperty <T extends Comparable<T>> implements IFreezable {
	
	protected final String propertyName;
	protected final FreezableMap<String, T> entries;
	
	protected T defaultValue;
	protected net.minecraft.state.Property<T> internal;
	
	private BlockProperty(final net.minecraft.state.Property<T> internal) {
		this.propertyName = internal.getName();
		this.entries = new FreezableLinkedHashMap<>();
		
		this.freeze();
	}
	
	public BlockProperty(final String propertyName) {
		Objects.requireNonNull(propertyName, "The property name may not be null");
		
		this.propertyName = propertyName;
		this.entries = new FreezableLinkedHashMap<>();
	}
	
	public static <T extends Comparable<T>> BlockProperty<T> get(final net.minecraft.state.Property<T> internalProperty) {
		if (internalProperty == null)
			return null;
		
		return new BlockProperty<>(internalProperty);
	}
	
	public String getPropertyName() {
		if (isFrozen())
			return this.internal.getName();
		
		return this.propertyName;
	}
	
	public Optional<T> getValue(final String valueName) {
		if (isFrozen())
			return this.internal.getValue(valueName);
		
		if (this.entries.containsValue(valueName))
			return Optional.of(this.entries.get(valueName));
		
		return Optional.empty();
	}
	
	public String getValueName(final T value) {
		if (isFrozen())
			return this.internal.getName(value);
		
		for (final String valueName : this.entries.keySet()) {
			if (this.entries.get(valueName)
							.equals(value))
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
		
		if (this.defaultValue == null) {
			this.defaultValue = value;
		}
	}
	
	public T getDefaultValue() {
		if (isFrozen()) {
			final Optional<T> optionalDefaultValue = this.internal.getPossibleValues().stream().findFirst();
			
			if (!optionalDefaultValue.isPresent())
				throw new IllegalStateException("Block property with no possible values don't have a default value.");
			
			return optionalDefaultValue.get();
		}
		
		return this.defaultValue;
	}
	
	protected void setDefaultValue(final T defaultValue) {
		IFreezable.throwIfFrozen(this);
		
		this.defaultValue = defaultValue;
	}
	
	public ImmutableList<T> getPossibleValues() {
		if (isFrozen())
			return ImmutableList.copyOf(this.internal.getPossibleValues());
		
		return ImmutableList.copyOf(this.entries.values());
	}
	
	public int getValueIndex(final T value) {
		if (isFrozen())
			return new ArrayList<>(this.internal.getPossibleValues()).indexOf(value);
		
		return new ArrayList<>(this.entries.values()).indexOf(value);
	}
	
	public net.minecraft.state.Property<T> getInternal() {
		return this.internal;
	}
	
	@Override
	public void freeze() {
		this.entries.freeze();
		
		if (this.internal == null)
			this.internal = new InternalBlockProperty<>(this);
	}
	
	@Override
	public boolean isFrozen() {
		return this.entries.isFrozen();
	}
	
	public static final class ValuePair <T extends Comparable<T>> implements ICloneable<ValuePair<T>> {
		
		private final BlockProperty<T> property;
		
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
			if (!this.property.getPossibleValues()
							  .contains(value))
				throw new IllegalArgumentException("This value is not supported by this property");
			
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

