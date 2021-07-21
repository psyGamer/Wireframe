package dev.psygamer.wireframecore.impl.common.registry;

import dev.psygamer.wireframecore.impl.ImplementationVersion;
import dev.psygamer.wireframecore.impl.MinecraftVersion;

import dev.psygamer.wireframe.block.BlockWrapper;
import dev.psygamer.wireframe.registry.BlockRegistry;

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
