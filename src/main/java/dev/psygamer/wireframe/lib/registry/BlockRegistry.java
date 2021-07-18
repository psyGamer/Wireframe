package dev.psygamer.wireframe.lib.registry;

import dev.psygamer.wireframe.core.implementation.ImplementationHandler;
import dev.psygamer.wireframe.lib.Wireframe;
import dev.psygamer.wireframe.lib.block.BlockWrapper;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

import java.util.List;


public interface BlockRegistry {
	
	static void register(final BlockWrapper blockWrapper) {
		ImplementationHandler.executeImplementation(blockWrapper);
	}
	
	static List<Block> getBlocks() {
		return Wireframe.Core.executeImplementation();
	}
	
	static List<BlockWrapper> getBlockWrappers() {
		return ImplementationHandler.executeImplementation();
	}
	
	void registerBlocksToForge(RegistryEvent.Register<Block> event);
}
