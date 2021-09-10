package dev.psygamer.wireframe.registry;

import dev.psygamer.wireframe.item.ItemFoundation;

import dev.psygamer.wireframe.core.WireframeCore;

import dev.psygamer.wireframe.util.IFreezable;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.util.collection.FreezableArrayList;
import dev.psygamer.wireframe.util.collection.FreezableList;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class ItemRegistry {
	
	protected static FreezableList<ItemFoundation> items = new FreezableArrayList<>();
	protected final String modID;
	protected Internal internal;
	
	public ItemRegistry(final String modID) {
		this.modID = modID;
		
		this.internal = new Internal();
	}
	
	public static void register(final ItemFoundation item) {
		items.add(item);
	}
	
	public static ImmutableList<ItemFoundation> getItems() {
		return items.toImmutable();
	}
	
	public Internal getInternal() {
		return this.internal;
	}
	
	protected class Internal {
		@SubscribeEvent()
		public void onBlockRegistry(final RegistryEvent.Register<Item> event) {
			items.freeze();
			items.stream()
					.filter(item -> Objects.equals(
							item.getNamespace().evaluate(), ItemRegistry.this.modID))
					.forEach(item -> {
						event.getRegistry().register(item.getInternal());
						
						WireframeCore.LOGGER.info(
								String.format("Successfully registered item %s:%s",
										item.getNamespace().evaluate(),
										item.getRegistryName()
								)
						);
					});
		}
	}
}
