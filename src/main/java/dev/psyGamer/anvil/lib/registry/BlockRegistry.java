package dev.psyGamer.anvil.lib.registry;

import dev.psyGamer.anvil.core.version.MinecraftVersion;
import dev.psyGamer.anvil.core.version.SupportedOnlyIn;
import dev.psyGamer.anvil.lib.block.BlockWrapper;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SupportedOnlyIn(MinecraftVersion.v16)
public abstract class BlockRegistry {
	
	protected static final List<BlockWrapper> BLOCKS = new ArrayList<>();
	
	public abstract void registerBlocksToForge(RegistryEvent.Register<Block> event);
	
	public void registerBlock(final BlockWrapper block) {
		BLOCKS.add(block);
	}
	
	public void registerBlocks(final BlockWrapper... blocks) {
		BLOCKS.addAll(Arrays.asList(blocks));
	}
	
	public void registerBlocks(final List<BlockWrapper> blocks) {
		BLOCKS.addAll(blocks);
	}
}
