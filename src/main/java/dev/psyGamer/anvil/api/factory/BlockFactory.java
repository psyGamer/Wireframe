package dev.psyGamer.anvil.api.factory;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public abstract class BlockFactory implements IFactory<Block> {

	public static BlockFactory create() {
		return null;
	}
	
	public BlockFactory(String name, Material material, CreativeTabs creativeTab) {
	
	}

}
