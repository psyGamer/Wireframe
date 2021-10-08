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
	
	@SubscribeEvent
	public void onBlockRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
		BlockEntityRegistry.freeze();
		BlockEntityRegistry.getTileEntities()
						   .stream()
						   .filter(tileEntity -> Objects.equals(
								   tileEntity.getIdentifier()
											 .getNamespace(), this.registry.getModID()))
						   .forEach(tileEntity -> {
							   event.getRegistry()
									.register(
											generateTileEntityType(tileEntity)
									);
			
							   Wireframe.LOGGER.info(
									   String.format("Successfully registered tile entity %s:%s",
													 tileEntity.getIdentifier()
															   .getNamespace(),
													 tileEntity.getIdentifier()
															   .getPath()
									   )
							   );
						   });
	}
	
	private TileEntityType<?> generateTileEntityType(final BlockEntity tileEntity) {
		return TileEntityType.Builder.of(
									 tileEntity::getInternal,
				
									 Arrays.stream(tileEntity.getTileEntityHolders())
										   .map(Block::getInternal)
										   .toArray(net.minecraft.block.Block[]::new)
							 )
									 .build(null);
	}
}
