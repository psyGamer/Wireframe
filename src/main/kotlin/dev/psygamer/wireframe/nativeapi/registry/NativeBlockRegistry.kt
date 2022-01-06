package dev.psygamer.wireframe.nativeapi.registry

import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.api.registry.BlockRegistry
import dev.psygamer.wireframe.event.api.ModEventBusSubscriber
import net.minecraft.block.Block
import net.minecraftforge.event.RegistryEvent.Register
import net.minecraftforge.eventbus.api.SubscribeEvent

@ModEventBusSubscriber
class NativeBlockRegistry(private val modID: String) {
	
	@SubscribeEvent
	fun onBlockRegistry(event: Register<Block>) {
		BlockRegistry.freeze()
		BlockRegistry.elements
			.filter { it.identifier.namespace == this.modID }
			.forEach {
				event.registry.register(it.mcNative)
				
				Wireframe.LOGGER.info("Successfully registered block ${it.identifier}")
			}
	}
}