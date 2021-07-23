package dev.psygamer.wireframecore.impl.v16.registry;

import dev.psygamer.wireframe.registry.BlockRegistry;
import dev.psygamer.wireframecore.WireframeCore;
import dev.psygamer.wireframecore.impl.MinecraftVersion;
import dev.psygamer.wireframecore.impl.ImplementationVersion;
import dev.psygamer.wireframecore.impl.common.registry.CommonBlockRegistry;
import dev.psygamer.wireframe.block.BlockWrapper;

import net.minecraft.block.Block;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

@ImplementationVersion(MinecraftVersion.v16)
public class BlockRegistryImpl16 extends CommonBlockRegistry {
	
	private final String modID;
	
	public BlockRegistryImpl16(final String modID) {
		this.modID = modID;
	}
	
	public static BlockRegistry create(final String modID) {
		return new BlockRegistryImpl16(modID);
	}
	
	@Override
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onBlockRegistry(final RegistryEvent.Register<Block> event) {
		blockWrappers.stream()
				.filter(blockWrapper -> Objects.equals(
						blockWrapper.getNamespace().evaluate(), this.modID))
				.forEach(blockWrapper -> {
					registerBlockWrapper(blockWrapper, event);
					
					WireframeCore.LOGGER.info(
							String.format("Successfully registered block %s:%s",
									blockWrapper.getNamespace().evaluate(),
									blockWrapper.getRegistryName()
							)
					);
					
					if (blockWrapper.hasBlockVariants()) {
						blockWrapper.getBlockVariants().forEach(blockVariant -> {
							registerBlockWrapper(blockVariant, event);
							
							WireframeCore.LOGGER.info(
									String.format("   |=> Successfully registered block variant%s:%s",
											blockWrapper.getNamespace().evaluate(),
											blockWrapper.getRegistryName()
									)
							);
						});
					}
				});
	}
	
	protected void registerBlockWrapper(final BlockWrapper blockWrapper, final RegistryEvent.Register<Block> registryEvent) {
		blockWrapper.getBlock().setRegistryName(
				blockWrapper.getNamespace().evaluate(),
				blockWrapper.getRegistryName()
		);
		
		registryEvent.getRegistry().register(blockWrapper.getBlock());
		
		blocks.add(blockWrapper.getBlock());
	}
}
