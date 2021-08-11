package dev.psygamer.wireframe.impl.v16.registry;

import dev.psygamer.wireframe.api.registry.BlockRegistry;
import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.impl.MinecraftVersion;
import dev.psygamer.wireframe.core.impl.ImplementationVersion;
import dev.psygamer.wireframe.impl.common.registry.CommonBlockRegistry;
import dev.psygamer.wireframe.api.block.BlockWrapper;

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
				});
	}
	
	protected void registerBlockWrapper(final BlockWrapper blockWrapper, final RegistryEvent.Register<Block> registryEvent) {
//		blockWrapper.getBlock().setRegistryName(
//				blockWrapper.getNamespace().evaluate(),
//				blockWrapper.getRegistryName()
//		);
//
//		registryEvent.getRegistry().register(blockWrapper.getBlock());
//
//		blocks.add(blockWrapper.getBlock());
	}
}
