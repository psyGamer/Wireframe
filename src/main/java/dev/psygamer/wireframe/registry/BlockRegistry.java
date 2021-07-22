package dev.psygamer.wireframe.registry;

import dev.psygamer.wireframe.block.BlockWrapper;

import dev.psygamer.wireframecore.impl.handle.Implementor;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

import java.util.List;


public interface BlockRegistry {
	
	static void register(final BlockWrapper blockWrapper) {
		Implementor.execute(blockWrapper);
	}
	
	static List<Block> getBlocks() {
		return Implementor.execute();
	}
	
	static List<BlockWrapper> getBlockWrappers() {
		return Implementor.execute();
	}
	
	void onBlockRegistry(RegistryEvent.Register<Block> event);
}
