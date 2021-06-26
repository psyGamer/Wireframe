package dev.psygamer.construct.impl.v16.registry;

import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.impl.common.registry.CommonBlockRegistry;
import dev.psygamer.construct.lib.block.BlockWrapper;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class BlockRegistryImpl16 extends CommonBlockRegistry {
	
	public static final Map<String, DeferredRegister<Block>> BLOCK_REGISTRIES = new HashMap<>();
	
	@Override
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void registerBlocksToForge(final RegistryEvent.Register<Block> event) {
		setupRegistries();
		
		BLOCK_REGISTRIES.forEach((namespace, registry) -> blockWrappers.forEach(blockWrapper -> {
			registerBlockWrapper(blockWrapper, registry);
			
			ConstructCore.LOGGER.info(
					String.format("Successfully registered block %s:%s",
							blockWrapper.getNamespace().get(),
							blockWrapper.getRegistryName()
					)
			);
			
			if (blockWrapper.hasBlockVariants()) {
				blockWrapper.getBlockVariants().forEach(wrapper -> {
					registerBlockWrapper(wrapper, registry);
					
					ConstructCore.LOGGER.info(
							String.format("   |=> Successfully registered block variant%s:%s",
									blockWrapper.getNamespace().get(),
									blockWrapper.getRegistryName()
							)
					);
				});
			}
		}));
	}
	
	protected void registerBlockWrapper(final BlockWrapper blockWrapper, final DeferredRegister<Block> registry) {
		blockWrapper.getBlock().setRegistryName(
				blockWrapper.getNamespace().get(),
				blockWrapper.getRegistryName()
		);
		
		registry.register(
				blockWrapper.getRegistryName(),
				blockWrapper::getBlock
		);
		
		blocks.add(blockWrapper.getBlock());
	}
	
	protected void setupRegistries() {
		for (final BlockWrapper blockWrapper : blockWrappers) {
			final String namespace = blockWrapper.getNamespace().get();
			
			if (!BLOCK_REGISTRIES.containsKey(namespace)) {
				BLOCK_REGISTRIES.put(namespace, DeferredRegister.create(ForgeRegistries.BLOCKS, namespace));
				BLOCK_REGISTRIES.get(namespace).register(ConstructCore.Util.getCurrentDependant().getModLoadingContext().getModEventBus());
			}
		}
	}
}
