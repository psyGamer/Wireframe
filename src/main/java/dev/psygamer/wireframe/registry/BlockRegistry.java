package dev.psygamer.wireframe.registry;

import dev.psygamer.wireframe.block.Block;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.internal.registry.InternalBlockRegistry;
import dev.psygamer.wireframe.util.collection.FreezableArrayList;
import dev.psygamer.wireframe.util.collection.FreezableList;

public class BlockRegistry {
	
	protected static FreezableList<Block> blocks = new FreezableArrayList<>();
	
	protected final String modID;
	protected final InternalBlockRegistry internal;
	
	public BlockRegistry(final String modID) {
		this.modID = modID;
		
		this.internal = new InternalBlockRegistry(this);
	}
	
	
	public static void register(final Block block) {
		blocks.add(block);
	}
	
	public static ImmutableList<Block> getBlocks() {
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
