package dev.psygamer.wireframe.registry;

import dev.psygamer.wireframe.Wireframe;
import dev.psygamer.wireframe.block.BlockWrapper;
import dev.psygamer.wireframe.core.impl.ImplementationHandler;

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
