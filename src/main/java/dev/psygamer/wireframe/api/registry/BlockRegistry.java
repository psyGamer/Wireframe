package dev.psygamer.wireframe.api.registry;

import dev.psygamer.wireframe.api.block.BlockFoundation;

import dev.psygamer.wireframe.core.impl.Instancer;

import com.google.common.collect.ImmutableList;

public abstract class BlockRegistry {
	
	private static final BlockRegistry INSTANCE = Instancer.createInstance();
	
	public static void register(final BlockFoundation block) {
		INSTANCE.registerBasicBlock(block);
	}
	
	public static ImmutableList<BlockFoundation> getBlocks() {
		return INSTANCE.getBlocksBasicBlocks();
	}
	
	protected abstract void registerBasicBlock(final BlockFoundation block);
	
	protected abstract ImmutableList<BlockFoundation> getBlocksBasicBlocks();
}
