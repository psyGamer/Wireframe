package dev.psygamer.wireframe.impl.common.registry;

import dev.psygamer.wireframe.api.block.BasicBlock;
import dev.psygamer.wireframe.api.registry.BlockRegistry;

import dev.psygamer.wireframe.core.impl.ImplementationVersion;
import dev.psygamer.wireframe.core.impl.MinecraftVersion;

import dev.psygamer.wireframe.util.collection.FreezableArrayList;
import dev.psygamer.wireframe.util.collection.FreezableList;

import com.google.common.collect.ImmutableList;

@ImplementationVersion(MinecraftVersion.COMMON)
public abstract class CommonBlockRegistry extends BlockRegistry {
	
	protected static FreezableList<BasicBlock> blocks = new FreezableArrayList<>();
	
	@Override
	protected void registerBasicBlock(final BasicBlock block) {
		blocks.add(block);
	}
	
	@Override
	protected ImmutableList<BasicBlock> getBlocksBasicBlocks() {
		return blocks.toImmutable();
	}
}
