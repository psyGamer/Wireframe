package dev.psygamer.wireframe.api.registry;

import dev.psygamer.wireframe.api.block.BasicBlock;

import dev.psygamer.wireframe.core.impl.Instancer;

import com.google.common.collect.ImmutableList;

public abstract class BlockRegistry {
	
	private static final BlockRegistry INSTANCE = Instancer.createInstance();
	
	public static void register(final BasicBlock block) {
		INSTANCE.registerBasicBlock(block);
	}
	
	public static ImmutableList<BasicBlock> getBlocks() {
		return INSTANCE.getBlocksBasicBlocks();
	}
	
	protected abstract void registerBasicBlock(final BasicBlock block);
	
	protected abstract ImmutableList<BasicBlock> getBlocksBasicBlocks();
}
