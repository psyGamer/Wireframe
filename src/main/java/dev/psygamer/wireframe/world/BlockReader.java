package dev.psygamer.wireframe.world;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.entity.BlockEntity;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.internal.world.InternalBlockReader;
import dev.psygamer.wireframe.util.BlockPosition;

public interface BlockReader {
	
	static BlockReader get(final net.minecraft.world.IBlockReader internal) {
		if (internal == null)
			return null;
		
		return new InternalBlockReader(internal);
	}
	
	Block getBlock(final BlockPosition position);
	
	BlockState getBlockState(final BlockPosition position);
	
	BlockEntity getBlockEntity(final BlockPosition position);
	
	int getHighestBlockPosition();
	
	int getLowestBlockPosition();
}
