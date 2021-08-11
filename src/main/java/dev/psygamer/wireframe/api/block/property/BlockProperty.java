package dev.psygamer.wireframe.api.block.property;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.util.collection.FreezableHashMap;

import java.util.Objects;

public class BlockProperty <T> {
	
	protected final String propertyName;
	protected final FreezableHashMap<String, T> entries;
	
	protected T defaultValue;
	
	public BlockProperty(final String propertyName) {
		Objects.requireNonNull(propertyName, "The property name may not be null");
		
		this.propertyName = propertyName;
		this.entries = new FreezableHashMap<>();
	}
	
	public T getValue(final String valueName) {
		if (this.entries.containsValue(valueName))
			return this.entries.get(valueName);
		
		return null;
	}
	
	public String getName(final T value) {
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
	
	protected void freeze() {
		this.entries.freeze();
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