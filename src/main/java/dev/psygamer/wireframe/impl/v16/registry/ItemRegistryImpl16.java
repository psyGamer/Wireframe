package dev.psygamer.wireframe.impl.v16.registry;

import dev.psygamer.wireframe.api.registry.ItemRegistry;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.impl.ImplementationVersion;
import dev.psygamer.wireframe.core.impl.MinecraftVersion;
import dev.psygamer.wireframe.core.event.ModEventBusSubscriber;

import dev.psygamer.wireframe.impl.common.registry.CommonItemRegistry;
import dev.psygamer.wireframe.impl.v16.block.CompiledBlockFoundationImpl16;
import dev.psygamer.wireframe.impl.v16.item.CompiledItemFoundationImpl16;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

@ModEventBusSubscriber
@ImplementationVersion(MinecraftVersion.v16)
public class ItemRegistryImpl16 extends CommonItemRegistry {
	
	private final String modID;
	
	public ItemRegistryImpl16(final String modID) {
		this.modID = modID;
	}
	
	public static ItemRegistry create(final String modID) {
		return new ItemRegistryImpl16(modID);
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onBlockRegistry(final RegistryEvent.Register<Item> event) {
		this.items.stream()
				.filter(item -> Objects.equals(
						item.getNamespace().evaluate(), this.modID))
				.forEach(item -> {
					final CompiledItemFoundationImpl16 compiledItem = new CompiledItemFoundationImpl16(item, item.getAttributes());
					
					event.getRegistry().register(compiledItem);
					
					WireframeCore.LOGGER.info(
							String.format("Successfully registered item %s:%s",
									item.getNamespace().evaluate(),
									item.getRegistryName()
							)
					);
				});
	}
}
