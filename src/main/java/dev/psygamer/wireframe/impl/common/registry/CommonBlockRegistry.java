package dev.psygamer.wireframe.impl.common.registry;

import dev.psygamer.wireframe.core.implementation.ImplementationVersion;
import dev.psygamer.wireframe.core.implementation.MinecraftVersion;

import dev.psygamer.wireframe.lib.block.BlockWrapper;
import dev.psygamer.wireframe.lib.registry.BlockRegistry;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

@ImplementationVersion(MinecraftVersion.COMMON)
public abstract class CommonBlockRegistry implements BlockRegistry {
	
	protected static List<Block> blocks = new ArrayList<>();
	protected static List<BlockWrapper> blockWrappers = new ArrayList<>();
	
	static List<Block> getBlocks() {
		return blocks;
	}
	
	static List<BlockWrapper> getBlockWrappers() {
		return blockWrappers;
	}
	
	public static void register(final BlockWrapper blockWrapper) {
		blockWrappers.add(blockWrapper);
	}
}
