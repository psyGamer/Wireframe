package dev.psygamer.construct.lib.registry;

import dev.psygamer.construct.core.implementation.ImplementationHandler;
import dev.psygamer.construct.lib.Construct;
import dev.psygamer.construct.lib.block.BlockWrapper;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

import java.util.List;


public interface BlockRegistry {
	
	static void register(final BlockWrapper blockWrapper) {
		ImplementationHandler.executeImplementation(blockWrapper);
	}
	
	static List<Block> getBlocks() {
		return Construct.Core.executeImplementation();
	}
	
	static List<BlockWrapper> getBlockWrappers() {
		return ImplementationHandler.executeImplementation();
	}
	
	void registerBlocksToForge(RegistryEvent.Register<Block> event);
}
