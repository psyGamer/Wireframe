package dev.psygamer.wireframe.world;

import dev.psygamer.wireframe.block.BlockFoundation;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.internal.world.InternalWorld;
import dev.psygamer.wireframe.util.BlockPosition;

public abstract class World {
	
	boolean isClientSide();
	
	public abstract boolean isClientSide();
	
	public abstract boolean isServerSide();
	
	public abstract BlockFoundation getBlock(final BlockPosition position);
	
	public abstract void setBlock(final BlockFoundation block, final BlockPosition position);
	
	public abstract BlockState getBlockState(final BlockPosition position);
	
	public abstract void setBlockState(final BlockState blockState, final BlockPosition position);
	
	public abstract boolean isReplaceable(final BlockPosition position);
	
	public abstract void notifyNeighbours(final BlockPosition position);
	
	public abstract void breakBlock(final BlockPosition position);
	
	public abstract void breakBlock(final BlockPosition position, final boolean dropItems);
	
	public abstract boolean isAir(final BlockPosition position);
	
	public abstract boolean isBlock(final BlockFoundation block, final BlockPosition position);
	
	public abstract boolean isLoaded(final BlockPosition position);
	
	public abstract float getBlockLightLevel(final BlockPosition blockPosition);
	
	public abstract float getSkyLightLevel(final BlockPosition blockPosition);
	
	public abstract long getTime();
	
	public abstract long getTicks();
}
