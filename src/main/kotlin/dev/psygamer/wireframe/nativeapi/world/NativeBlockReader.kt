package dev.psygamer.wireframe.nativeapi.world

import net.minecraft.world.IBlockReader
import dev.psygamer.wireframe.api.block.*
import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.api.world.BlockReader
import dev.psygamer.wireframe.nativeapi.*
import dev.psygamer.wireframe.util.BlockPosition

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