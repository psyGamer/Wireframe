package dev.psygamer.wireframe.internal.registry;

import dev.psygamer.wireframe.Wireframe;
import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.entity.BlockEntity;
import dev.psygamer.wireframe.event.api.ModEventBusSubscriber;
import dev.psygamer.wireframe.registry.BlockEntityRegistry;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.util.collection.FreezableHashMap;
import dev.psygamer.wireframe.util.collection.FreezableMap;

import net.minecraft.tileentity.TileEntityType;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.Objects;

@ModEventBusSubscriber
public class InternalBlockEntityRegistry {
	
	private static final FreezableMap<Identifier, TileEntityType<?>> tileEntityTypeCache = new FreezableHashMap<>();
	
	private final BlockEntityRegistry registry;
	
	public InternalBlockEntityRegistry(final BlockEntityRegistry registry) {
		this.registry = registry;
	}
	
	public static TileEntityType<?> getTileEntityType(final Identifier identifier) {
		return tileEntityTypeCache.get(identifier);
	}
	
	public static InternalBlockEntityRegistry createInstance(final String modID) {
		return new BlockEntityRegistry(modID).getInternal();
	}
	
	public static TileEntityType<?> generateTileEntityType(final BlockEntity.Definition definition) {
		return TileEntityType.Builder
				.of(() -> definition.getBlockEntitySupplier().invoke().getMcNative(),
				
					Arrays.stream(definition.getBlockEntityHolders())
						  .map(Block::getMcNative$wireframe)
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
							   final TileEntityType<?> tileEntityType = generateTileEntityType(definition);
			
							   tileEntityTypeCache.put(definition.getIdentifier(), tileEntityType);
			
							   event.getRegistry()
									.register(tileEntityType);
			
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
