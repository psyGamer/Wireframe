package dev.psygamer.wireframe.block

import dev.psygamer.wireframe.block.attributes.HarvestLevel
import dev.psygamer.wireframe.block.attributes.Material
import dev.psygamer.wireframe.internal.block.InternalBlockAttributes
import net.minecraft.block.SoundType
import net.minecraftforge.common.ToolType

/**
 * A factory class for easily creating attributes related to blocks.
 *
 * @param material Used for sound, hardness, etc.
 * @see Block
 */
open class BlockAttributes(
	val material: Material
) : Cloneable {
	
	var sound: SoundType? = null
		private set
	
	var hardness = 0f
		private set
	var blastResistance = 0f
		private set
	
	var correctTool: ToolType? = null
		private set
	var harvestLevel = 0
		private set
	
	var toolRequired = false
		private set
	
	@get:JvmName("isFullBlock")
	var fullBlock = true
		private set
	
	@get:JvmName("isOpaque")
	var opaque = false
		private set
	
	var hasItem = true
		private set
	
	internal val mcNative: InternalBlockAttributes
	
	init {
		// Set default values based on the material
		hardness(material.hardness)
		blastResistance(material.blastResistance)
		
		toolRequired(material.hardness > 0)
		correctTool(material.correctTool)
		
		mcNative = InternalBlockAttributes(this)
	}
	
	fun hardness(hardness: Float): BlockAttributes {
		this.hardness = hardness
		return this
	}
	
	fun blastResistance(blastResistance: Float): BlockAttributes {
		this.blastResistance = blastResistance
		return this
	}
	
	fun sound(sound: SoundType): BlockAttributes {
		this.sound = sound
		return this
	}
	
	fun correctTool(tool: ToolType?): BlockAttributes {
		correctTool = tool
		return this
	}
	
	fun harvestLevel(level: Int): BlockAttributes {
		harvestLevel = level
		return this
	}
	
	fun harvestLevel(harvestLevel: HarvestLevel): BlockAttributes {
		this.harvestLevel = harvestLevel.harvestLevel
		return this
	}
	
	fun toolRequired(toolRequired: Boolean): BlockAttributes {
		this.toolRequired = toolRequired
		return this
	}
	
	fun fullBlock(fullBlock: Boolean): BlockAttributes {
		this.fullBlock = fullBlock
		return this
	}
	
	fun opaque(opaque: Boolean): BlockAttributes {
		this.opaque = opaque
		return this
	}
	
	fun hasItem(hasItem: Boolean): BlockAttributes {
		this.hasItem = hasItem
		return this
	}
	
	fun noItem(): BlockAttributes {
		return hasItem(false)
	}
	
	override fun clone(): BlockAttributes {
		return BlockAttributes(material)
			.hardness(hardness)
			.blastResistance(blastResistance)
			.correctTool(correctTool)
			.harvestLevel(harvestLevel)
			.toolRequired(toolRequired)
			.fullBlock(fullBlock)
	}
}