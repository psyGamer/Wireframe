package dev.psygamer.wireframe.internal.world

import dev.psygamer.wireframe.block.Block
import dev.psygamer.wireframe.block.entity.BlockEntity
import dev.psygamer.wireframe.block.state.BlockState
import dev.psygamer.wireframe.mcNative
import dev.psygamer.wireframe.util.BlockPosition
import dev.psygamer.wireframe.wfWrapped
import dev.psygamer.wireframe.world.BlockReader
import net.minecraft.world.IBlockReader

open class NativeBlockReader(private val mcNative: IBlockReader) : BlockReader {
	
	override val maxLightLevel: Int = mcNative.maxLightLevel
	
	override val maxBuildHeight: Int = mcNative.maxBuildHeight
	override val minBuildHeight: Int = 0
	
	override fun getBlock(position: BlockPosition): Block {
		return mcNative.getBlockState(position.mcNative).block.wfWrapped
	}
	
	override fun getBlockState(position: BlockPosition): BlockState {
		return mcNative.getBlockState(position.mcNative).wfWrapped
	}
	
	override fun getBlockEntity(position: BlockPosition): BlockEntity? {
		return mcNative.getBlockEntity(position.mcNative)?.wfWrapped
	}
	
	override fun getLightEmission(position: BlockPosition): Int {
		return mcNative.getLightEmission(position.mcNative)
	}
}