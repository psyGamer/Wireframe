package dev.psygamer.wireframe.world;

import dev.psygamer.wireframe.block.BlockFoundation;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.util.BlockPosition;

public interface IWorld {
	
	boolean isClientSide();
	
	boolean isServerSide();
	
	BlockFoundation getBlock(final BlockPosition position);
	
	void setBlock(final BlockFoundation block, final BlockPosition position);
	
	BlockState getBlockState(final BlockPosition position);
	
	void setBlockState(final BlockState blockState, final BlockPosition position);
	
	boolean isReplaceable(final BlockPosition position);
	
	void notifyNeighbours(final BlockPosition position);
	
	void breakBlock(final BlockPosition position);
	
	void breakBlock(final BlockPosition position, final boolean dropItems);
	
	boolean isAir(final BlockPosition position);
	
	boolean isBlock(final BlockFoundation block, final BlockPosition position);
	
	boolean isLoaded(final BlockPosition position);
	
	float getBlockLightLevel(final BlockPosition blockPosition);
	
	float getSkyLightLevel(final BlockPosition blockPosition);
	
	long getTime();
	
	long getTicks();
}
