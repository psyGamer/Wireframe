package dev.psygamer.wireframe.registry;

import dev.psygamer.wireframe.block.BlockWrapper;

import dev.psygamer.wireframecore.event.ModEventBusSubscriber;
import dev.psygamer.wireframecore.impl.handle.Implementor;

import net.minecraft.block.Block;

import net.minecraftforge.event.RegistryEvent;

import java.util.List;

@ModEventBusSubscriber
public interface BlockRegistry {
	
	static BlockRegistry create(final String modID) {
		return Implementor.execute(modID);
	}
	
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
