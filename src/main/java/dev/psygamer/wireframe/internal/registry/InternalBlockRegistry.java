package dev.psygamer.wireframe.internal.registry;

import dev.psygamer.wireframe.Wireframe;
import dev.psygamer.wireframe.event.api.ModEventBusSubscriber;
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
	
	public static InternalBlockRegistry createInstance(final String modID) {
		return new BlockRegistry(modID).getInternal();
	}
	
	@SubscribeEvent
	public void onBlockRegistry(final RegistryEvent.Register<Block> event) {
		BlockRegistry.freeze();
		BlockRegistry.getBlocks().stream()
				.filter(block -> Objects.equals(
						block.getIdentifier().getNamespace(), this.registry.getModID()))
				.forEach(block -> {
					event.getRegistry().register(block.getInternal());
					
					Wireframe.LOGGER.info(
							String.format("Successfully registered block %s:%s",
									block.getIdentifier().getNamespace(),
									block.getIdentifier().getPath()
							)
					);
				});
	}
}
