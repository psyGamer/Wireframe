package dev.psygamer.wireframe.impl.v16.registry;

import dev.psygamer.wireframe.api.registry.BlockRegistry;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.event.ModEventBusSubscriber;
import dev.psygamer.wireframe.core.impl.MinecraftVersion;
import dev.psygamer.wireframe.core.impl.ImplementationVersion;

import dev.psygamer.wireframe.impl.common.registry.CommonBlockRegistry;
import dev.psygamer.wireframe.impl.v16.block.CompiledBlockFoundationImpl16;

import net.minecraft.block.Block;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

@ModEventBusSubscriber
@ImplementationVersion(MinecraftVersion.v16)
public class BlockRegistryImpl16 extends CommonBlockRegistry {
	
	private final String modID;
	
	public BlockRegistryImpl16(final String modID) {
		this.modID = modID;
	}
	
	public static BlockRegistry create(final String modID) {
		return new BlockRegistryImpl16(modID);
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onBlockRegistry(final RegistryEvent.Register<Block> event) {
		blocks.stream()
				.filter(block -> Objects.equals(
						block.getNamespace().evaluate(), this.modID))
				.forEach(block -> {
					final CompiledBlockFoundationImpl16 compiledBlock = new CompiledBlockFoundationImpl16(block, block.getAttributes());
					
					event.getRegistry().register(compiledBlock);
					
					WireframeCore.LOGGER.info(
							String.format("Successfully registered block %s:%s",
									block.getNamespace().evaluate(),
									block.getRegistryName()
							)
					);
				});
	}
}
