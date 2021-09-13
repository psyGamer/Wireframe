package dev.psygamer.wireframe.internal.registry;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.event.ModEventBusSubscriber;
import dev.psygamer.wireframe.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

@ModEventBusSubscriber
public class InternalBlockRegistry {
	
	private final BlockRegistry registry;
	
	public InternalBlockRegistry(final BlockRegistry registry) {
		this.registry = registry;
	}
	
	@SubscribeEvent
	public void onBlockRegistry(final RegistryEvent.Register<Block> event) {
		BlockRegistry.freeze();
		BlockRegistry.getBlocks().stream()
				.filter(block -> Objects.equals(
						block.getNamespace().evaluate(), this.registry.getModID()))
				.forEach(block -> {
					event.getRegistry().register(block.getInternal());
					
					WireframeCore.LOGGER.info(
							String.format("Successfully registered block %s:%s",
									block.getNamespace().evaluate(),
									block.getRegistryName()
							)
					);
				});
	}
}
