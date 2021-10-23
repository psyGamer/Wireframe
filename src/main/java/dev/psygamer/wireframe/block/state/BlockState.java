package dev.psygamer.wireframe.block.state;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.internal.block.InternalBlockState;

import java.util.Optional;

public interface BlockState {
	
	/** Internal, do not use! */
	static BlockState get(final net.minecraft.block.BlockState internalBlockState) {
		if (internalBlockState == null)
			return null;
		
		return new InternalBlockState(internalBlockState);
	}
	
	/** @return The {@link Block} which is the owner of this {@link BlockState} */
	Block getBlock();
	
	/**
	 * @param property The {@link BlockProperty} which should be changed.
	 * @param value    The new value of the {@link BlockProperty}
	 * @param <T>      The object type of the {@link BlockProperty}.
	 *
	 * @return A <strong>new</strong> {@link BlockState} with the wanted value.
	 */
	<T extends Comparable<T>> BlockState setValue(final BlockProperty<T> property, T value);
	
	/** Unsafe, do not use! */
	<T extends Comparable<T>> BlockState setComparableValue(final BlockProperty<T> property, Comparable<?> value);
	
	/**
	 * @param property The {@link BlockProperty} of which the value should be returned.
	 * @param <T>      The object type of the {@link BlockProperty}.
	 *
	 * @return The value of the specified {@link BlockProperty}.
	 *
	 * @throws IllegalArgumentException If the specified {@link BlockProperty} does not exist on the {@link Block}.
	 */
	<T extends Comparable<T>> T getValue(final BlockProperty<T> property);
	
	/**
	 * @param property The {@link BlockProperty} of which the value should be returned.
	 * @param <T>      The object type of the {@link BlockProperty}.
	 *
	 * @return The value of the specified {@link BlockProperty} in an {@link Optional}
	 * which will be empty if the specified {@link BlockProperty} does not exist on the {@link Block}.
	 */
	<T extends Comparable<T>> Optional<T> getOptionalValue(final BlockProperty<T> property);
	
	/**
	 * @param block The {@link Block} to which it should get compared.
	 *
	 * @return Weather the specified {@link Block} is the same as the {@link BlockState}'s owner.
	 */
	boolean is(final Block block);
	
	/** Internal, do not use! */
	net.minecraft.block.BlockState getInternal();
}

