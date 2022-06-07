package dev.psygamer.wireframe.api.world

import dev.psygamer.wireframe.api.block.*
import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.util.BlockPosition

interface BlockReader {

	val maxLightLevel: Int

	val maxBuildHeight: Int
	val minBuildHeight: Int

	fun getBlock(position: BlockPosition): Block
	fun getBlockState(position: BlockPosition): BlockState
	fun getBlockEntity(position: BlockPosition): BlockEntity?

	fun getLightEmission(position: BlockPosition): Int
}