package dev.psygamer.wireframe.nativeapi.registry

import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.api.registry.ItemRegistry
import dev.psygamer.wireframe.event.nativeapi.NativeModEventBusSubscriber
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent.Register
import net.minecraftforge.eventbus.api.SubscribeEvent

@NativeModEventBusSubscriber
class NativeItemRegistry(private val modID: String) {
	
	companion object {
		
		fun createInstance(modID: String): NativeItemRegistry {
			return NativeItemRegistry(modID)
		}
	}
	
	@SubscribeEvent
	fun onBlockRegistry(event: Register<Item>) {
		ItemRegistry.freeze()
		ItemRegistry.elements
			.filter { it.identifier.namespace == modID }
			.forEach {
				event.registry.register(it.mcNative)
				
				Wireframe.LOGGER.info("Successfully registered item ${it.identifier}")
			}
	}
}