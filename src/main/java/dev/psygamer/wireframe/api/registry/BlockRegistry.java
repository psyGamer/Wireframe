package dev.psygamer.wireframe.api.registry;

import dev.psygamer.wireframe.api.block.BlockFoundation;

import dev.psygamer.wireframe.core.impl.Instancer;

import com.google.common.collect.ImmutableList;

public abstract class BlockRegistry {
	
	private static final BlockRegistry INSTANCE = Instancer.createInstance();
	
	public static void register(final BlockFoundation block) {
		INSTANCE.registerBlockFoundation(block);
	}
	
	public static ImmutableList<BlockFoundation> getBlocks() {
		return INSTANCE.getBlockFoundations();
	}
	
	protected abstract void registerBlockFoundation(final BlockFoundation block);
	
	protected abstract ImmutableList<BlockFoundation> getBlockFoundations();
}
