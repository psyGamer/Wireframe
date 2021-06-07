package dev.psyGamer.anvil.lib.registry;

import dev.psyGamer.anvil.core.version.MinecraftVersion;
import dev.psyGamer.anvil.core.version.SupportedOnlyIn;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

@SupportedOnlyIn(MinecraftVersion.v16)
public interface BlockRegistry {
	
	void registerBlocks(List<Block> blocks);
	
	void registerTileEntities(List<TileEntity> tileEntities);
}
