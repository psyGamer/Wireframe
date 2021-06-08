package dev.psyGamer.anvil.impl.v16.registry;

import dev.psyGamer.anvil.core.AnvilCore;
import dev.psyGamer.anvil.lib.block.BlockWrapper;
import dev.psyGamer.anvil.lib.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistryImpl16 extends BlockRegistry {
	
	public static final DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, AnvilCore.getModImplementation().MODID);
	
	@Override
	public void registerBlocksToForge() {
		BLOCK_REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
		
		for (final BlockWrapper block : BLOCKS) {
			BLOCK_REGISTRY.register(block.getRegistryName(), block.getBlockSupplier());
			
			if (block.hasBlockVariants()) {
				for (final BlockWrapper blockVariant : block.getBlockVariants()) {
					BLOCK_REGISTRY.register(blockVariant.getRegistryName(), blockVariant.getBlockSupplier());
				}
			}
		}
	}
}
