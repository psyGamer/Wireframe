package dev.psygamer.wireframe.nativeapi.registry

import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.api.registry.PacketRegistry
import dev.psygamer.wireframe.event.nativeapi.NativeForgeEventBusSubscriber
import dev.psygamer.wireframe.nativeapi.network.NativePacketHandler
import dev.psygamer.wireframe.nativeapi.wfWrapped
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

@NativeForgeEventBusSubscriber
object NativePacketRegistry {
	
	@SubscribeEvent
	fun onCommonSetup(event: FMLCommonSetupEvent) {
		PacketRegistry.freeze()
		PacketRegistry.elements.forEach { (packetClass, packetDecoder) ->
			NativePacketHandler.registerPacket(
				packetClass,
				
				{ packet, packetBuffer ->
					packet.encode(packetBuffer.wfWrapped)
				},
				{ packetBuffer ->
					packetDecoder.decode(packetBuffer.wfWrapped)
				},
				{ packet, contextSupplier ->
					contextSupplier.get().enqueueWork { packet.handle() }
				}
			)
			
			Wireframe.LOGGER.info("Successfully registered packet ${packetClass.name}")
		}
	}
}