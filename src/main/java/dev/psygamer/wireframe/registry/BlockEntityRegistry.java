package dev.psygamer.wireframe.registry;

import com.google.common.collect.ImmutableMap;
import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.entity.BlockEntity;
import dev.psygamer.wireframe.internal.registry.InternalBlockEntityRegistry;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.util.collection.FreezableHashMap;
import dev.psygamer.wireframe.util.collection.FreezableMap;

import java.util.*;

public class BlockEntityRegistry {
	
	protected static FreezableMap<Identifier, BlockEntity.Definition> blockEntityDefinitions = new FreezableHashMap<>();
	
	protected final String modID;
	protected final InternalBlockEntityRegistry internal;
	
	public BlockEntityRegistry(final String modID) {
		this.modID = modID;
		
		this.internal = new InternalBlockEntityRegistry(this);
	}
	
	public static void register(final BlockEntity.Definition blockEntityDefinition) {
		blockEntityDefinitions.put(blockEntityDefinition.getIdentifier(), blockEntityDefinition);
	}
	
	public static ImmutableMap<Identifier, BlockEntity.Definition> getBlockEntityDefinitions() {
		return blockEntityDefinitions.toImmutable();
	}
	
	public static void freeze() {
		tileEntities.freeze();
	}
	
	public String getModID() {
		return this.modID;
	}
	
	public InternalBlockEntityRegistry getInternal() {
		return this.internal;
	}
}
