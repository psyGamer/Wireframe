package dev.psyGamer.anvil.core.apiImpl.v12.factory;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockFactory extends dev.psyGamer.anvil.api.factory.BlockFactory {
	
	public BlockFactory(String name, CreativeTabs creativeTab) {
		super(name, Material.ANVIL, creativeTab);
	}
	
	@Override
	public Block build() {
		return null;
	}
}
