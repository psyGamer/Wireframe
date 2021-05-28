package dev.psyGamer.anvil.lib.factory;

import dev.psyGamer.anvil.core.util.HasStaticMember;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

@HasStaticMember
public abstract class BlockFactory implements IFactory<Block> {
	
	public static BlockFactory create(final String blockName, final Material material, final CreativeTabs creativeTab) {
		return null;
	}
	
}
