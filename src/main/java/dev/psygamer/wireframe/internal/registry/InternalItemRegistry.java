package dev.psygamer.wireframe.internal.registry;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.event.ModEventBusSubscriber;
import dev.psygamer.wireframe.registry.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

@ModEventBusSubscriber
public class InternalItemRegistry {
	
	private final ItemRegistry itemRegistry;
	
	public InternalItemRegistry(final ItemRegistry itemRegistry) {
		this.itemRegistry = itemRegistry;
	}
	
	@SubscribeEvent
	public void onBlockRegistry(final RegistryEvent.Register<Item> event) {
		ItemRegistry.freeze();
		ItemRegistry.getItems().stream()
				.filter(item -> Objects.equals(
						item.getNamespace().evaluate(), this.itemRegistry.getModID()))
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
