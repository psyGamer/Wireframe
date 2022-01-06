package dev.psygamer.wireframe.test

import dev.psygamer.wireframe.api.block.Block
import dev.psygamer.wireframe.api.block.BlockAttributes
import dev.psygamer.wireframe.api.block.attributes.HarvestLevel
import dev.psygamer.wireframe.api.block.attributes.Material
import dev.psygamer.wireframe.api.block.DirectionBlockProperty
import dev.psygamer.wireframe.api.item.ItemAttributes
import dev.psygamer.wireframe.util.Identifier

class BlockTest : Block(
	Identifier("wireframe", "block_test"),
	
	BlockAttributes(Material.WOOD)
		.harvestLevel(HarvestLevel.STONE),
	ItemAttributes()
		.maxStackSize(15),
	
	FACING
) {
	
	companion object {
		
		val FACING = DirectionBlockProperty("facing")
	}
}