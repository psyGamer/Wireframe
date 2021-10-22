package dev.psygamer.wireframe.internal.registry;

import dev.psygamer.wireframe.Wireframe;
import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.entity.BlockEntity;
import dev.psygamer.wireframe.event.api.ModEventBusSubscriber;
import dev.psygamer.wireframe.registry.BlockEntityRegistry;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.Objects;

@ModEventBusSubscriber
public class InternalBlockEntityRegistry {
	
	private final BlockEntityRegistry registry;
	
	public InternalBlockEntityRegistry(final BlockEntityRegistry registry) {
		this.registry = registry;
	}
	
	public static InternalBlockEntityRegistry createInstance(final String modID) {
		return new BlockEntityRegistry(modID).getInternal();
	}
	
	private static TileEntityType<?> generateTileEntityType(final BlockEntity.Definition definition) {
		return TileEntityType.Builder
				.of(() -> definition.getBlockEntitySupplier().get().getInternal(),
				
					Arrays.stream(definition.getBlockEntityHolders())
						  .map(Block::getInternal)
						  .toArray(net.minecraft.block.Block[]::new)
				)
				.build(null)
				.setRegistryName(
						definition.getIdentifier().getNamespace(),
						definition.getIdentifier().getPath()
				);
	}
	
	@SubscribeEvent
	public void onBlockRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
		BlockEntityRegistry.freeze();
		BlockEntityRegistry.getBlockEntityDefinitions().values()
						   .stream()
						   .filter(definition -> Objects.equals(
								   definition.getIdentifier()
											 .getNamespace(), this.registry.getModID()))
						   .forEach(definition -> {
							   event.getRegistry()
									.register(
											generateTileEntityType(definition)
									);
			
							   Wireframe.LOGGER.info(
									   String.format("Successfully registered tile entity %s:%s",
													 definition.getIdentifier()
															   .getNamespace(),
													 definition.getIdentifier()
															   .getPath()
									   )
							   );
						   });
	}
}
