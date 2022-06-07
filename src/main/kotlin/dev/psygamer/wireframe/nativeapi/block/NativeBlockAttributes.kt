package dev.psygamer.wireframe.nativeapi.block

import net.minecraft.block.*
import net.minecraft.entity.EntityType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import net.minecraftforge.common.ToolType
import dev.psygamer.wireframe.api.block.BlockAttributes
import dev.psygamer.wireframe.api.block.attributes.*

class NativeBlockAttributes(private val attributes: BlockAttributes) {

	fun createProperties(): AbstractBlock.Properties {
		val properties = AbstractBlock.Properties.of(attributes.material.mcNative)

		if (attributes.hardness >= 0 && attributes.blastResistance >= 0)
			properties.strength(attributes.hardness, attributes.blastResistance)
		else if (attributes.hardness >= 0)
			properties.strength(attributes.hardness)
		else
			properties.instabreak()

		if (attributes.correctTool != null)
			properties.harvestTool(attributes.correctTool as ToolType)

		properties.harvestLevel(attributes.harvestLevel)

		if (attributes.harvestLevel != HarvestLevel.NONE.harvestLevel)
			properties.requiresCorrectToolForDrops()

		if (attributes.sound != null)
			properties.sound(attributes.sound as SoundType)

		if (attributes.fullBlock && attributes.opaque) {
			properties.isValidSpawn(::always)
			properties.isSuffocating(::always)
			properties.isViewBlocking(::always)
		} else {
			properties.isValidSpawn(::never)
			properties.isSuffocating(::never)
			properties.isViewBlocking(::never)
		}

		if (attributes.material === Material.AIR)
			properties.air()

		return properties
	}

	private fun always(blockState: BlockState, blockReader: IBlockReader, pos: BlockPos): Boolean {
		return true
	}

	private fun always(blockState: BlockState, blockReader: IBlockReader, pos: BlockPos, entityType: EntityType<*>): Boolean {
		return true
	}

	private fun never(blockState: BlockState, blockReader: IBlockReader, pos: BlockPos): Boolean {
		return false
	}

	private fun never(blockState: BlockState, blockReader: IBlockReader, pos: BlockPos, entityType: EntityType<*>): Boolean {
		return false
	}
}