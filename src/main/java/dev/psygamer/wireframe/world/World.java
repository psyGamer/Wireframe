package dev.psygamer.wireframe.world;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.internal.world.InternalWorld;
import dev.psygamer.wireframe.util.BlockPosition;

public interface World extends BlockReader {
	
	static World get(final net.minecraft.world.World internalWorld) {
		if (internalWorld == null)
			return null;
		
		return new InternalWorld(internalWorld);
	}
	
	boolean isClientSide();
	
	boolean isServerSide();
	
	boolean setBlock(final Block block, final BlockPosition position);
	
	boolean setBlock(final Block block, final BlockPosition position, final int updateFlag);
	
	boolean setBlockState(final BlockState blockState, final BlockPosition position);
	
	boolean setBlockState(final BlockState blockState, final BlockPosition position, final int updateFlag);
	
	boolean isReplaceable(final BlockPosition position);
	
	void notifyNeighbours(final BlockPosition position);
	
	void notifyNeighbours(final BlockPosition position, Block block);
	
	boolean breakBlock(final BlockPosition position);
	
	boolean breakBlock(final BlockPosition position, final boolean dropItems);
	
	boolean isAir(final BlockPosition position);
	
	boolean isBlock(final Block block, final BlockPosition position);
	
	boolean isLoaded(final BlockPosition position);
	
	float getBlockLightLevel(final BlockPosition blockPosition);
	
	float getSkyLightLevel(final BlockPosition blockPosition);
	
	long getTime();
	
	long getTicks();
	
	final class UpdateFlags {
		
		/**
		 * Causes neighbor updates to be sent to all surrounding blocks.
		 */
		public static final int NOTIFY_NEIGHBORS = (1 << 0);
		
		/** Updates path-finding */
		public static final int BLOCK_UPDATE = (1 << 1);
		
		/** Stops the blocks from being marked for a render update */
		public static final int NO_RERENDER = (1 << 2);
		
		/**
		 * Makes the block be re-rendered immediately, on the main thread.
		 * If NO_RERENDER is set, then this will be ignored
		 */
		public static final int RERENDER_MAIN_THREAD = (1 << 3);
		
		/**
		 * Causes neighbor updates to be sent to all surrounding blocks (including
		 * diagonals).
		 */
		public static final int UPDATE_NEIGHBORS = (1 << 4);
		
		/**
		 * Prevents neighbor changes from spawning item drops, used by
		 */
		public static final int NO_NEIGHBOR_DROPS = (1 << 5);
		
		/**
		 * Tell the block being changed that it was moved, rather than removed/replaced,
		 */
		public static final int IS_MOVING = (1 << 6);
		
		/**
		 * Causes neighbor updates to be sent to all surrounding blocks
		 * and updates path-finding.
		 */
		public static final int DEFAULT = NOTIFY_NEIGHBORS | BLOCK_UPDATE;
		
		/**
		 * Causes neighbor updates to be sent to all surrounding blocks, updates path-finding
		 * and Makes the block be re-rendered immediately, on the main thread.
		 */
		public static final int DEFAULT_AND_RERENDER = DEFAULT | RERENDER_MAIN_THREAD;
	}
}
