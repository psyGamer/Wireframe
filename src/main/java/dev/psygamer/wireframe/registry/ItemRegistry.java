package dev.psygamer.wireframe.registry;

import dev.psygamer.wireframe.item.ItemFoundation;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.impl.Instancer;

import dev.psygamer.wireframe.util.IFreezable;

import com.google.common.collect.ImmutableList;

public abstract class ItemRegistry implements IFreezable {
	
	private static final ItemRegistry INSTANCE = Instancer.createInstance();
	
	static {
		WireframeCore.EVENT_BUS.register(INSTANCE);
	}
	
	public static void register(final ItemFoundation item) {
		INSTANCE.registerItemFoundation(item);
	}
	
	public static ImmutableList<ItemFoundation> getItems() {
		return INSTANCE.getItemFoundations();
	}
	
	protected abstract void registerItemFoundation(final ItemFoundation item);
	
	protected abstract ImmutableList<ItemFoundation> getItemFoundations();
}
