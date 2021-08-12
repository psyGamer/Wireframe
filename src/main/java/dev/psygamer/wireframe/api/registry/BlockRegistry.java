package dev.psygamer.wireframe.api.registry;

import dev.psygamer.wireframe.api.block.BasicBlock;

import dev.psygamer.wireframe.core.event.ModEventBusSubscriber;
import dev.psygamer.wireframe.core.impl.Instancer;

import com.google.common.collect.ImmutableList;

@ModEventBusSubscriber
public abstract class BlockRegistry {
	
	private static final BlockRegistry INSTANCE = Instancer.createInstance();
	
	public static BlockRegistry create(final String modID) {
		return INSTANCE.createInstance(modID);
	}
	
	public static void register(final BasicBlock block) {
		INSTANCE.registerBasicBlock(block);
	}
	
	public static ImmutableList<BasicBlock> getBlocks() {
		return INSTANCE.getBlocksBasicBlocks();
	}
	
	protected abstract BlockRegistry createInstance(final String modID);
	
	protected abstract void registerBasicBlock(final BasicBlock block);
	
	protected abstract ImmutableList<BasicBlock> getBlocksBasicBlocks();
}
