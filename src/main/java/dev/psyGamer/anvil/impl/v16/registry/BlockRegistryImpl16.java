package dev.psyGamer.anvil.impl.v16.registry;

import dev.psyGamer.anvil.core.AnvilCore;
import dev.psyGamer.anvil.lib.block.BlockWrapper;
import dev.psyGamer.anvil.lib.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistryImpl16 extends BlockRegistry {
	
	public static final DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, AnvilCore.getModImplementation().MODID);
	
	@Override
	@SubscribeEvent
	public void registerBlocksToForge(final RegistryEvent.Register<Block> event) {
		BLOCK_REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
		
		for (final BlockWrapper blockWrapper : blockWrappers) {
			BLOCK_REGISTRY.register(blockWrapper.getRegistryName(), blockWrapper::getBlock);
			blocks.add(blockWrapper.getBlock());
			
			if (blockWrapper.hasBlockVariants()) {
				for (final BlockWrapper blockVariantWrapper : blockWrapper.getBlockVariants()) {
					BLOCK_REGISTRY.register(blockVariantWrapper.getRegistryName(), blockVariantWrapper::getBlock);
					blocks.add(blockVariantWrapper.getBlock());
				}
			}
		}
	}
}
