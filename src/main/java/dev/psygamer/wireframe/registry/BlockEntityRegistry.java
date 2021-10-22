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
		blockEntityDefinitions.freeze();
	}
	
	private static void mergeDefinitions() {
		final Map<Identifier, Integer> identifierOccurrences = new HashMap<>();
		final Map<Identifier, List<BlockEntity.Definition>> definitionsByIdentifier = new HashMap<>();
		
		for (final BlockEntity.Definition definition : getBlockEntityDefinitions().values()) {
			if (!identifierOccurrences.containsKey(definition.getIdentifier()))
				identifierOccurrences.put(definition.getIdentifier(), 1);
			else
				identifierOccurrences.put(definition.getIdentifier(), identifierOccurrences.get(definition.getIdentifier()) + 1);
			
			if (!definitionsByIdentifier.containsKey(definition.getIdentifier()))
				definitionsByIdentifier.put(definition.getIdentifier(), new ArrayList<>());
			
			definitionsByIdentifier.get(definition.getIdentifier()).add(definition);
		}
		
		if (!identifierOccurrences.containsValue(1))
			return;
		
		identifierOccurrences.forEach((identifier, occurrences) -> {
			if (occurrences == 1)
				definitionsByIdentifier.remove(identifier);
		});
		
		definitionsByIdentifier.forEach((identifier, definitions) -> {
			final List<Block> blockEntityHolders = new ArrayList<>();
			
			for (final BlockEntity.Definition definition : definitions) {
				blockEntityHolders.addAll(
						Arrays.asList(definition.getBlockEntityHolders())
				);
			}
			
			final BlockEntity.Definition definition = new BlockEntity.Definition(
					identifier, definitions.get(0).getBlockEntitySupplier(), blockEntityHolders.toArray(new Block[0])
			);
			
			blockEntityDefinitions.put(identifier, definition);
		});
	}
	
	public String getModID() {
		return this.modID;
	}
	
	public InternalBlockEntityRegistry getInternal() {
		return this.internal;
	}
}
