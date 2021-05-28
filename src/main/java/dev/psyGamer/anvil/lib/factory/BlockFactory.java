package dev.psyGamer.anvil.lib.factory;

import dev.psyGamer.anvil.core.version.MinecraftVersion;
import dev.psyGamer.anvil.core.version.SupportedVersion;
import dev.psyGamer.anvil.core.version.VersionHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

@SupportedVersion(MinecraftVersion.v12)
public abstract class BlockFactory implements IFactory<Block> {
	
	public static BlockFactory create(final String blockName, final Material material, final CreativeTabs creativeTab) {
		return (BlockFactory) VersionHandler.executeVersionedMethod(blockName, material, creativeTab);
	}
	
}
