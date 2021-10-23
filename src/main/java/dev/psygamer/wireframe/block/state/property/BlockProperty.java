package dev.psygamer.wireframe.block.state.property;

import dev.psygamer.wireframe.internal.block.InternalBlockProperty;
import dev.psygamer.wireframe.internal.block.state.BlockPropertyWrapper;

import com.google.common.collect.ImmutableSet;

import java.util.Objects;
import java.util.Optional;

/** Properties which are using within {@link dev.psygamer.wireframe.block.Block Blocks} to create {@link dev.psygamer.wireframe.block.state.BlockState BlockStates}. */
public abstract class BlockProperty <T extends Comparable<T>> {
	
	private final String propertyName;
	private final T defaultValue;
	private final net.minecraft.state.Property<T> internal;
	
	/**
	 * @param propertyName The {@link BlockProperty}'s name.
	 * @param defaultValue The {@link BlockProperty}'s default value.
	 */
	public BlockProperty(final String propertyName, final T defaultValue) {
		Objects.requireNonNull(propertyName, "The property name may not be null");
		
		this.propertyName = propertyName;
		this.defaultValue = defaultValue;
		
		this.internal = new InternalBlockProperty<>(this);
	}
	
	/** Internal, do not use! */
	public static <T extends Comparable<T>> BlockProperty<T> get(final net.minecraft.state.Property<T> internalProperty) {
		if (internalProperty == null)
			return null;
		
		return new BlockPropertyWrapper<>(internalProperty);
	}
	
	public String getPropertyName() {
		return this.propertyName.toLowerCase();
	}
	
	public T getDefaultValue() {
		return this.defaultValue;
	}
	
	/** @return The value with the specified name, or {@link Optional#empty()} if it doesn't exist. */
	public abstract Optional<T> getValue(final String valueName);
	
	/** @return The name of the specified value. */
	public abstract String getValueName(final T value);
	
	/** @return All possible values. */
	public abstract ImmutableSet<T> getPossibleValues();
	
	/** Internal, do not use! */
	public net.minecraft.state.Property<T> getInternal() {
		return this.internal;
	}
}

