package dev.psygamer.wireframe.world;

import dev.psygamer.wireframe.block.BlockFoundation;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.internal.world.InternalBlockReader;
import dev.psygamer.wireframe.util.BlockPosition;

public interface BlockReader {
	
	static BlockReader get(final net.minecraft.world.IBlockReader internal) {
		return new InternalBlockReader(internal);
	}
	
	int getHighestBlockPosition();
	
	int getLowestBlockPosition();
	
	BlockFoundation getBlock(final BlockPosition position);
	
	BlockState getBlockState(final BlockPosition position);
}