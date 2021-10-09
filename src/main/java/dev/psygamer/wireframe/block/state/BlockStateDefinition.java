package dev.psygamer.wireframe.block.state;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.state.property.BlockProperty;

public interface BlockStateDefinition {
	
	Block getBlock();
	
	BlockState getDefaultState();
	
	ImmutableList<BlockState> getPossibleStates();
	
	BlockProperty<?> getProperty(final String propertyName);
}
