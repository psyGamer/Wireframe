package dev.psygamer.wireframe.test

import dev.psygamer.wireframe.block.Block
import dev.psygamer.wireframe.block.BlockAttributes
import dev.psygamer.wireframe.block.attributes.HarvestLevel
import dev.psygamer.wireframe.block.attributes.Material
import dev.psygamer.wireframe.block.state.property.DirectionBlockProperty
import dev.psygamer.wireframe.item.ItemAttributes
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