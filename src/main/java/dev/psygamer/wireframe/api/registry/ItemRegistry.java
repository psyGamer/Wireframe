package dev.psygamer.wireframe.api.registry;

import com.google.common.collect.ImmutableList;

import dev.psygamer.wireframe.api.item.ItemFoundation;

import dev.psygamer.wireframe.core.impl.Instancer;

public abstract class ItemRegistry {
	
	private static final ItemRegistry INSTANCE = Instancer.createInstance();
	
	public static void register(final ItemFoundation item) {
		INSTANCE.registerItemFoundation(item);
	}
	
	public static ImmutableList<ItemFoundation> getItems() {
		return INSTANCE.getItemFoundations();
	}
	
	protected abstract void registerItemFoundation(final ItemFoundation item);
	
	protected abstract ImmutableList<ItemFoundation> getItemFoundations();
}
