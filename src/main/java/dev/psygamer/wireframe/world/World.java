package dev.psygamer.wireframe.world;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.internal.world.InternalWorld;
import dev.psygamer.wireframe.util.BlockPosition;

public interface World extends BlockReader {
	
	static World get(final net.minecraft.world.World internalWorld) {
		return new InternalWorld(internalWorld);
	}
	
	boolean isClientSide();
	
	boolean isServerSide();
	
	boolean setBlock(final Block block, final BlockPosition position);
	
	boolean setBlockState(final BlockState blockState, final BlockPosition position);
	
	boolean isReplaceable(final BlockPosition position);
	
	void notifyNeighbours(final BlockPosition position);
	
	boolean breakBlock(final BlockPosition position);
	
	boolean breakBlock(final BlockPosition position, final boolean dropItems);
	
	boolean isAir(final BlockPosition position);
	
	boolean isBlock(final Block block, final BlockPosition position);
	
	boolean isLoaded(final BlockPosition position);
	
	float getBlockLightLevel(final BlockPosition blockPosition);
	
	float getSkyLightLevel(final BlockPosition blockPosition);
	
	long getTime();
	
	long getTicks();
}
