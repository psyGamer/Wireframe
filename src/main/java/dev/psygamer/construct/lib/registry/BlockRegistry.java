package dev.psygamer.construct.lib.registry;

import dev.psygamer.construct.core.version.ImplementationHandler;
import dev.psygamer.construct.core.version.MinecraftVersion;
import dev.psygamer.construct.core.version.SupportedSince;
import dev.psygamer.construct.lib.block.BlockWrapper;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

import java.util.List;


@SupportedSince(MinecraftVersion.v16)
public interface BlockRegistry {
	
	static void register(final BlockWrapper blockWrapper) {
		ImplementationHandler.executeImplementation(blockWrapper);
	}
	
	static List<Block> getBlocks() {
		return ImplementationHandler.executeImplementation();
	}
	
	static List<BlockWrapper> getBlockWrappers() {
		return ImplementationHandler.executeImplementation();
	}
	
	void registerBlocksToForge(RegistryEvent.Register<Block> event);
}
