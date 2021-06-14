package dev.psyGamer.anvil.impl.v16.registry;

import dev.psyGamer.anvil.core.AnvilCore;
import dev.psyGamer.anvil.impl.common.registry.CommonBlockRegistry;
import dev.psyGamer.anvil.lib.block.BlockWrapper;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class BlockRegistryImpl16 extends CommonBlockRegistry {
	
	public static final Map<String, DeferredRegister<Block>> BLOCK_REGISTRIES = new HashMap<>();
	
	@Override
	@SubscribeEvent
	public void registerBlocksToForge(final RegistryEvent.Register<Block> event) {
		setupRegistries();
		
		BLOCK_REGISTRIES.forEach((namespace, registry) -> blockWrappers.forEach(blockWrapper -> {
			registerBlockWrapper(blockWrapper, registry, false);
			
			blockWrapper.getBlockVariants().forEach(wrapper -> registerBlockWrapper(wrapper, registry, true));
		}));
	}
	
	private void registerBlockWrapper(final BlockWrapper blockWrapper, final DeferredRegister<Block> registry, final boolean isVariant) {
		registry.register(
				blockWrapper.getRegistryName(),
				blockWrapper::getBlock
		);
		
		blocks.add(blockWrapper.getBlock());
		
		if (isVariant) {
			AnvilCore.LOGGER.info(
					String.format("   |=> Successfully registered block variant -> %s:%s",
							blockWrapper.getNamespace(),
							blockWrapper.getRegistryName()
					)
			);
		} else {
			AnvilCore.LOGGER.info(
					String.format("Successfully registered block -> %s:%s",
							blockWrapper.getNamespace(),
							blockWrapper.getRegistryName()
					)
			);
		}
	}
	
	private void setupRegistries() {
		for (final BlockWrapper blockWrapper : blockWrappers) {
			final String namespace = blockWrapper.getNamespace().get();
			
			if (!BLOCK_REGISTRIES.containsKey(namespace)) {
				BLOCK_REGISTRIES.put(namespace, DeferredRegister.create(ForgeRegistries.BLOCKS, namespace));
				BLOCK_REGISTRIES.get(namespace).register(FMLJavaModLoadingContext.get().getModEventBus());
			}
		}
	}
}
