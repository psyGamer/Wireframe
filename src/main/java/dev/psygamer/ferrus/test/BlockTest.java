package dev.psyGamer.anvil.test;

import dev.psyGamer.anvil.lib.block.BlockFactory;
//import dev.psyGamer.anvil.lib.block.variants.BlockVariants;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class BlockTest {
	
	//	@BlockVariants
	public static final Block TUFF_SANDSTONE = BlockFactory.create("tuff_sandstone")
			.inheritFromBlock(Blocks.SANDSTONE)
			.multiplyStrength(1.2f)
			.build();
	
}
