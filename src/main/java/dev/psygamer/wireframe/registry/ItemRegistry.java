package dev.psygamer.wireframe.registry;

import dev.psygamer.wireframe.internal.registry.InternalItemRegistry;
import dev.psygamer.wireframe.item.Item;
import dev.psygamer.wireframe.util.collection.FreezableHashSet;
import dev.psygamer.wireframe.util.collection.FreezableSet;

import com.google.common.collect.ImmutableSet;

public class ItemRegistry {
	
	protected static FreezableSet<Item> items = new FreezableHashSet<>();
	
	protected final String modID;
	protected final InternalItemRegistry internal;
	
	public ItemRegistry(final String modID) {
		this.modID = modID;
		
		this.internal = new InternalItemRegistry(this);
	}
	
	public static void register(final Item item) {
		items.add(item);
	}
	
	public static ImmutableSet<Item> getItems() {
		return items.toImmutable();
	}
	
	public static void freeze() {
		items.freeze();
	}
	
	public String getModID() {
		return this.modID;
	}
	
	public InternalItemRegistry getInternal() {
		return this.internal;
	}
}
