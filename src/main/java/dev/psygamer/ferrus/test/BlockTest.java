package dev.psygamer.ferrus.test;

import dev.psygamer.ferrus.lib.block.BlockFactory;
//import dev.psygamer.ferrus.lib.block.variants.BlockVariants;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class BlockTest {
	
	//	@BlockVariants
	public static final Block TUFF_SANDSTONE = BlockFactory.create("tuff_sandstone")
			.inheritFromBlock(Blocks.SANDSTONE)
			.multiplyStrength(1.2f)
			.build();
	
}
