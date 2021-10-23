package dev.psygamer.wireframe.registry;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.internal.registry.InternalBlockRegistry;
import dev.psygamer.wireframe.util.collection.FreezableHashSet;
import dev.psygamer.wireframe.util.collection.FreezableSet;

import com.google.common.collect.ImmutableSet;

public class BlockRegistry {
	
	protected static FreezableSet<Block> blocks = new FreezableHashSet<>();
	
	protected final String modID;
	protected final InternalBlockRegistry internal;
	
	public BlockRegistry(final String modID) {
		this.modID = modID;
		
		this.internal = new InternalBlockRegistry(this);
	}
	
	public static void register(final Block block) {
		blocks.add(block);
	}
	
	public static ImmutableSet<Block> getBlocks() {
		return blocks.toImmutable();
	}
	
	public static void freeze() {
		blocks.freeze();
	}
	
	public String getModID() {
		return this.modID;
	}
	
	public InternalBlockRegistry getInternal() {
		return this.internal;
	}
}
