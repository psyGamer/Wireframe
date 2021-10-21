package dev.psygamer.wireframe.block.state;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.internal.block.InternalBlockState;

import java.util.Optional;

public interface BlockState {
	
	static BlockState get(final net.minecraft.block.BlockState internalBlockState) {
		if (internalBlockState == null)
			return null;
		
		return new InternalBlockState(internalBlockState);
	}
	
	Block getBlock();
	
	<T extends Comparable<T>> BlockState setComparableValue(final BlockProperty<T> property, Comparable<?> value);
	
	<T extends Comparable<T>> BlockState setValue(final BlockProperty<T> property, T value);
	
	<T extends Comparable<T>> T getValue(final BlockProperty<T> property);
	
	<T extends Comparable<T>> Optional<T> getOptionalValue(final BlockProperty<T> property);
	
	boolean is(final Block block);
	
	net.minecraft.block.BlockState getInternal();
}

