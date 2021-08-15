package dev.psygamer.wireframe.impl.common.registry;

import com.google.common.collect.ImmutableList;

import dev.psygamer.wireframe.api.item.ItemFoundation;
import dev.psygamer.wireframe.api.registry.ItemRegistry;

import dev.psygamer.wireframe.core.impl.ImplementationVersion;
import dev.psygamer.wireframe.core.impl.MinecraftVersion;
import dev.psygamer.wireframe.util.collection.FreezableList;
import dev.psygamer.wireframe.util.collection.FreezableArrayList;

@ImplementationVersion(MinecraftVersion.COMMON)
public class CommonItemRegistry extends ItemRegistry {
	
	protected FreezableList<ItemFoundation> items = new FreezableArrayList<>();
	
	@Override
	protected void registerItemFoundation(final ItemFoundation item) {
		this.items.add(item);
	}
	
	@Override
	protected ImmutableList<ItemFoundation> getItemFoundations() {
		return this.items.toImmutable();
	}
}
