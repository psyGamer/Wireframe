package dev.psygamer.wireframe.block.state;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.internal.block.InternalBlockStateDefinition;

import com.google.common.collect.ImmutableList;

public interface BlockStateDefinition {
	
	/** Internal, do not ues! */
	static BlockStateDefinition get(
			final net.minecraft.state.StateContainer<net.minecraft.block.Block, net.minecraft.block.BlockState> internalStateContainer
	) {
		if (internalStateContainer == null)
			return null;
		
		return new InternalBlockStateDefinition(internalStateContainer);
	}
	
	/** @return The {@link Block} which is the owner of this {@link BlockStateDefinition} */
	Block getBlock();
	
	/** @return The {@link BlockState} with the default values of all {@link BlockProperty BlockProperties}. */
	BlockState getDefaultBlockState();
	
	/** @return All possible {@link BlockState BlockStates} that exist. */
	ImmutableList<BlockState> getPossibleStates();
	
	/** @return The {@link BlockProperty} with the specified name. */
	BlockProperty<?> getProperty(final String propertyName);
	
	/** Internal, do not use! */
	net.minecraft.state.StateContainer<net.minecraft.block.Block, net.minecraft.block.BlockState> getInternal();
}
