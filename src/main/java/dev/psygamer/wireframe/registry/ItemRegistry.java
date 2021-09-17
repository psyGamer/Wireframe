package dev.psygamer.wireframe.registry;

import dev.psygamer.wireframe.internal.registry.InternalItemRegistry;
import dev.psygamer.wireframe.item.Item;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.util.collection.FreezableArrayList;
import dev.psygamer.wireframe.util.collection.FreezableList;

public class ItemRegistry {
	
	protected static FreezableList<Item> items = new FreezableArrayList<>();
	protected final String modID;
	protected final InternalItemRegistry internal;
	
	public ItemRegistry(final String modID) {
		this.modID = modID;
		
		this.internal = new InternalItemRegistry(this);
	}
	
	public static void register(final Item item) {
		items.add(item);
	}
	
	public static ImmutableList<Item> getItems() {
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
