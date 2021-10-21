package dev.psygamer.wireframe.block.state.property;

import com.google.common.collect.ImmutableSet;
import dev.psygamer.wireframe.internal.block.InternalBlockProperty;
import dev.psygamer.wireframe.internal.block.state.BlockPropertyWrapper;

import java.util.Objects;
import java.util.Optional;

public abstract class BlockProperty <T extends Comparable<T>> {
	
	protected final String propertyName;
	protected T defaultValue;
	
	protected net.minecraft.state.Property<T> internal;
	
	public BlockProperty(final String propertyName, final T defaultValue) {
		Objects.requireNonNull(propertyName, "The property name may not be null");
		
		this.propertyName = propertyName;
		this.defaultValue = defaultValue;
		
		this.internal = new InternalBlockProperty<>(this);
	}
	
	public static <T extends Comparable<T>> BlockProperty<T> get(final net.minecraft.state.Property<T> internalProperty) {
		if (internalProperty == null)
			return null;
		
		return new BlockPropertyWrapper<>(internalProperty);
	}
	
	public String getPropertyName() {
		return this.propertyName;
	}
	
	public T getDefaultValue() {
		return this.defaultValue;
	}
	
	protected void setDefaultValue(final T defaultValue) {
		if (!getPossibleValues().contains(defaultValue))
			throw new IllegalArgumentException("Default value '" + defaultValue + "' is not a possible value!");
		
		this.defaultValue = defaultValue;
	}
	
	public abstract Optional<T> getValue(final String valueName);
	
	public abstract String getValueName(final T value);
	
	public abstract ImmutableSet<T> getPossibleValues();
	
	public net.minecraft.state.Property<T> getInternal() {
		return this.internal;
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

