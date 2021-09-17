package dev.psygamer.wireframe.test.item_display;

import dev.psygamer.wireframe.block.BlockFoundation;
import dev.psygamer.wireframe.block.BlockAttributes;
import dev.psygamer.wireframe.block.attributes.HarvestLevel;
import dev.psygamer.wireframe.block.state.property.DirectionBlockProperty;

import dev.psygamer.wireframe.util.Identifier;
import net.minecraft.block.material.Material;

/*
 * This class is an example of how to create blocks in Wireframe.
 * The goal is to use as few classes from net.minecraft or net.minecraftforge as possible.
 *
 * Once this block is finished it should be an item display.
 */

public class ItemDisplayBlock extends BlockFoundation {
	
	public static final DirectionBlockProperty FACING = new DirectionBlockProperty("facing");
	
	public ItemDisplayBlock() {
		super(new Identifier("wireframe", "item_display"),
				
				new BlockAttributes(Material.WOOD)
						.hardness(1.0f)
						.blastResistance(1.0f)
						.harvestLevel(HarvestLevel.STONE)
		);
		
		registerBlockProperty(FACING);
	}
	
	
}
