package dev.psyGamer.anvil.lib.registry;

import dev.psyGamer.anvil.core.version.ImplementationHandler;
import dev.psyGamer.anvil.core.version.MinecraftVersion;
import dev.psyGamer.anvil.core.version.SupportedSince;
import dev.psyGamer.anvil.lib.block.BlockWrapper;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

import java.util.List;


@SuppressWarnings("unchecked")
@SupportedSince(MinecraftVersion.v16)
public interface BlockRegistry {
	
	static void addBlockWrapper(final BlockWrapper block) {
		ImplementationHandler.executeImplementation(block);
	}
	
	static List<Block> getBlocks() {
		return (List<Block>) ImplementationHandler.executeImplementation();
	}
	
	static List<BlockWrapper> getBlockWrappers() {
		return (List<BlockWrapper>) ImplementationHandler.executeImplementation();
	}
	
	void registerBlocksToForge(RegistryEvent.Register<Block> event);
}
