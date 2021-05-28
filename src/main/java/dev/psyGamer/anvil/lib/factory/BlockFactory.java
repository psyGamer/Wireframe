package dev.psyGamer.anvil.lib.factory;

import dev.psyGamer.anvil.core.VersionHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public abstract class BlockFactory implements IFactory<Block> {
	
	public static BlockFactory create(final String blockName, final Material material, final CreativeTabs creativeTab) {
		return (BlockFactory) VersionHandler.executeVersionedMethod(blockName, material, creativeTab);
	}
	
}
