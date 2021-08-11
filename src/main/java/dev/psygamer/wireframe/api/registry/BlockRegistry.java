package dev.psygamer.wireframe.api.registry;

import dev.psygamer.wireframe.api.block.BlockWrapper;

import dev.psygamer.wireframe.core.event.ModEventBusSubscriber;
import dev.psygamer.wireframe.core.impl.Implementor;

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
