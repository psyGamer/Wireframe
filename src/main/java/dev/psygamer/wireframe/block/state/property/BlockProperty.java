package dev.psygamer.wireframe.block.state.property;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.util.helper.IFreezable;

import java.util.Objects;
import java.util.Optional;

public abstract class BlockProperty <T extends Comparable<T>> implements IFreezable {
	
	protected final String propertyName;
	
	public BlockProperty(final String propertyName) {
		Objects.requireNonNull(propertyName, "The property name may not be null");
		
		this.propertyName = propertyName;
	}
	
	public static <T extends Comparable<T>> BlockProperty<T> get(final net.minecraft.state.Property<T> internalProperty) {
		if (internalProperty == null)
			return null;
		
		return null;//new BlockProperty<>(internalProperty);
	}
	
	public String getPropertyName() {
		return this.propertyName;
	}
	
	public abstract Optional<T> getValue(final String valueName);
	
	public abstract String getValueName(final T value);
	
	public abstract T getDefaultValue();
	
	protected abstract void setDefaultValue(final T defaultValue);
	
	public abstract ImmutableList<T> getPossibleValues();
	
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

