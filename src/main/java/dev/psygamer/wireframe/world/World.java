package dev.psygamer.wireframe.world;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.internal.world.InternalWorld;
import dev.psygamer.wireframe.util.BlockPosition;

public interface World extends BlockReader {
	
	enum UpdateFlag {
		
		/**
		 * Causes neighbor updates to be sent to all surrounding blocks.
		 */
		NOTIFY_NEIGHBORS(1 << 0),
		
		/** Updates path-finding */
		BLOCK_UPDATE(1 << 1),
		
		/** Stops the blocks from being marked for a render update */
		NO_RERENDER(1 << 2),
		
		/**
		 * Makes the block be re-rendered immediately, on the main thread.
		 * If NO_RERENDER is set, then this will be ignored
		 */
		RERENDER_MAIN_THREAD(1 << 3),
		
		/**
		 * Causes neighbor updates to be sent to all surrounding blocks (including
		 * diagonals).
		 */
		UPDATE_NEIGHBORS(1 << 4),
		
		/**
		 * Prevents neighbor changes from spawning item drops, used by
		 */
		NO_NEIGHBOR_DROPS(1 << 5),
		
		/**
		 * Tell the block being changed that it was moved, rather than removed/replaced,
		 */
		IS_MOVING(1 << 6),
		
		/**
		 * Causes neighbor updates to be sent to all surrounding blocks
		 * and updates path-finding.
		 */
		DEFAULT(NOTIFY_NEIGHBORS.internalFlag | BLOCK_UPDATE.internalFlag),
		
		/**
		 * Causes neighbor updates to be sent to all surrounding blocks, updates path-finding
		 * and Makes the block be re-rendered immediately, on the main thread.
		 */
		DEFAULT_AND_RERENDER(DEFAULT.internalFlag | RERENDER_MAIN_THREAD.internalFlag);
		
		private final int internalFlag;
		
		UpdateFlag(final int internalFlag) {
			this.internalFlag = internalFlag;
		}
		
		public int getInternalFlag() {
			return this.internalFlag;
		}
	}
	
	static World get(final net.minecraft.world.World internalWorld) {
		if (internalWorld == null)
			return null;
		
		return new InternalWorld(internalWorld);
	}
	
	boolean isClientSide();
	
	boolean isServerSide();
	
	boolean setBlock(final Block block, final BlockPosition position);
	
	boolean setBlock(final Block block, final BlockPosition position, final UpdateFlag updateFlag);
	
	boolean setBlockState(final BlockState blockState, final BlockPosition position);
	
	boolean setBlockState(final BlockState blockState, final BlockPosition position, final UpdateFlag updateFlag);
	
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
}
