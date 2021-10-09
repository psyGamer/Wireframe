package dev.psygamer.wireframe.block.state;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.internal.block.InternalBlockStateDefinition;

public interface BlockStateDefinition {
	
	static BlockStateDefinition get(
			final net.minecraft.state.StateContainer<net.minecraft.block.Block, net.minecraft.block.BlockState> internalStateContainer
	) {
		if (internalStateContainer == null)
			return null;
		
		return new InternalBlockStateDefinition(internalStateContainer);
	}
	
	Block getBlock();
	
	BlockState getDefaultState();
	
	ImmutableList<BlockState> getPossibleStates();
	
	BlockProperty<?> getProperty(final String propertyName);
	
	net.minecraft.state.StateContainer<net.minecraft.block.Block, net.minecraft.block.BlockState> getInternal();
}
