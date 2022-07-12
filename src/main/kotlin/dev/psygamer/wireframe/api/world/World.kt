package dev.psygamer.wireframe.api.world

import dev.psygamer.wireframe.api.block.*
import dev.psygamer.wireframe.util.BlockPosition

interface World : BlockReader {
	enum class UpdateFlag(val flag: Int) {
		/** Causes neighbor updates to be sent to all surrounding blocks. */
		NOTIFY_NEIGHBORS(0x1),

		/** Updates path-finding. */
		BLOCK_UPDATE(0x2),

		/** Stops the blocks from being marked for a render update. */
		NO_RENDER(0x4),

		/**
		 * Makes the block be re-rendered immediately, on the main thread.
		 * If [NO_RENDER] is set, then this will be ignored.
		 */
		RENDER_MAIN_THREAD(0x8),

		/** Causes neighbor updates to be sent to all surrounding blocks (including diagonals). */
		UPDATE_NEIGHBORS(0x10),

		/** Prevents neighbor changes from spawning item drops. */
		NO_NEIGHBOR_DROPS(0x20),

		/** Tells the block which is being changed that it was moved, rather than removed/replaced. */
		IS_MOVING(0x40),

		/** Causes neighbor updates to be sent to all surrounding blocks (including diagonals) and updates path-finding. */
		DEFAULT(NOTIFY_NEIGHBORS.flag or BLOCK_UPDATE.flag),

		/** Causes neighbor updates to be sent to all surrounding blocks (including diagonals),
		 * updates path-finding and makes the block be re-rendered immediately, on the main thread. */
		DEFAULT_AND_RENDER(DEFAULT.flag or RENDER_MAIN_THREAD.flag);

	}

	val isClientSide: Boolean
	val isServerSide: Boolean

	val dayTime: Long
	val gameTime: Long

	fun setBlock(block: Block, position: BlockPosition): Boolean
	fun setBlock(block: Block, position: BlockPosition, updateFlag: UpdateFlag): Boolean

	fun setBlockState(blockState: BlockState, position: BlockPosition): Boolean
	fun setBlockState(blockState: BlockState, position: BlockPosition, updateFlag: UpdateFlag): Boolean

	fun isReplaceable(position: BlockPosition): Boolean

	fun notifyNeighbours(position: BlockPosition)
	fun notifyNeighbours(position: BlockPosition, block: Block)

	fun breakBlock(position: BlockPosition): Boolean
	fun breakBlock(position: BlockPosition, dropItems: Boolean): Boolean

	fun isAir(position: BlockPosition): Boolean
	fun isLoaded(position: BlockPosition): Boolean
	fun isBlock(position: BlockPosition, block: Block): Boolean

	fun getBlockLightLevel(position: BlockPosition): Int
	fun getSkyLightLevel(position: BlockPosition): Int
}