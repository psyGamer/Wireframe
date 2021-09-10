package dev.psygamer.wireframe.impl.common.registry;

import dev.psygamer.wireframe.block.BlockFoundation;
import dev.psygamer.wireframe.registry.BlockRegistry;

import dev.psygamer.wireframe.core.impl.ImplementationVersion;
import dev.psygamer.wireframe.core.impl.MinecraftVersion;

import dev.psygamer.wireframe.util.collection.FreezableArrayList;
import dev.psygamer.wireframe.util.collection.FreezableList;

import com.google.common.collect.ImmutableList;

@ImplementationVersion(MinecraftVersion.COMMON)
public class CommonBlockRegistry extends BlockRegistry {
	
	protected static FreezableList<BlockFoundation> blocks = new FreezableArrayList<>();
	
	@Override
	protected void registerBlockFoundation(final BlockFoundation block) {
		blocks.add(block);
	}
	
	@Override
	protected ImmutableList<BlockFoundation> getBlockFoundations() {
		return blocks.toImmutable();
	}
	
	@Override
	public void freeze() {
		blocks.freeze();
	}
	
	@Override
	public boolean isFrozen() {
		return blocks.isFrozen();
	}
}
