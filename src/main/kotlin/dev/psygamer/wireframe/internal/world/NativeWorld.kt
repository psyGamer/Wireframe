package dev.psygamer.wireframe.internal.world

import dev.psygamer.wireframe.block.Block
import dev.psygamer.wireframe.block.state.BlockState
import dev.psygamer.wireframe.mcNative
import dev.psygamer.wireframe.util.BlockPosition
import dev.psygamer.wireframe.world.World
import dev.psygamer.wireframe.world.World.UpdateFlag
import net.minecraft.block.FlowingFluidBlock
import net.minecraft.world.LightType

class NativeWorld(private val mcNative: net.minecraft.world.World) :
	NativeBlockReader(mcNative), World {
	
	override val isClientSide: Boolean
		get() = mcNative.isClientSide
	override val isServerSide: Boolean
		get() = !mcNative.isClientSide
	
	override val dayTime: Long
		get() = mcNative.dayTime
	override val gameTime: Long
		get() = mcNative.gameTime
	
	override fun setBlock(block: Block, position: BlockPosition): Boolean {
		return setBlock(block, position, UpdateFlag.BLOCK_UPDATE)
	}
	
	override fun setBlock(block: Block, position: BlockPosition, updateFlag: UpdateFlag): Boolean {
		return setBlockState(block.defaultBlockState, position)
	}
	
	override fun setBlockState(blockState: BlockState, position: BlockPosition): Boolean {
		return setBlockState(blockState, position, UpdateFlag.BLOCK_UPDATE)
	}
	
	override fun setBlockState(blockState: BlockState, position: BlockPosition, updateFlag: UpdateFlag): Boolean {
		return mcNative.setBlock(position.mcNative, blockState.mcNative, updateFlag.flag)
	}
	
	override fun isReplaceable(position: BlockPosition): Boolean {
		val blockState = mcNative.getBlockState(position.mcNative)
		
		return blockState.material.isReplaceable ||
			   blockState.block is FlowingFluidBlock
	}
	
	override fun notifyNeighbours(position: BlockPosition) {
		mcNative.updateNeighborsAt(position.mcNative, mcNative.getBlockState(position.mcNative).block)
	}
	
	override fun notifyNeighbours(position: BlockPosition, block: Block) {
		mcNative.updateNeighborsAt(position.mcNative, block.mcNative)
	}
	
	override fun breakBlock(position: BlockPosition): Boolean {
		return breakBlock(position, true)
	}
	
	override fun breakBlock(position: BlockPosition, dropItems: Boolean): Boolean {
		return mcNative.destroyBlock(position.mcNative, dropItems)
	}
	
	override fun isAir(position: BlockPosition): Boolean {
		return mcNative.isEmptyBlock(position.mcNative)
	}
	
	override fun isLoaded(position: BlockPosition): Boolean {
		return mcNative.isLoaded(position.mcNative)
	}
	
	override fun isBlock(position: BlockPosition, block: Block): Boolean {
		return mcNative.getBlockState(position.mcNative).block == block.mcNative
	}
	
	override fun getBlockLightLevel(position: BlockPosition): Float {
		return mcNative.getBrightness(LightType.BLOCK, position.mcNative).toFloat()
	}
	
	override fun getSkyLightLevel(position: BlockPosition): Float {
		return mcNative.getBrightness(LightType.SKY, position.mcNative).toFloat()
	}
}