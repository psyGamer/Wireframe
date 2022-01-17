package dev.psygamer.wireframe.api.registry

import dev.psygamer.wireframe.api.network.Packet
import dev.psygamer.wireframe.api.network.PacketDecoder
import dev.psygamer.wireframe.util.collection.FreezableMap

object PacketRegistry : MapRegistry<Class<out Packet>, PacketDecoder<*>>() {
	
	@JvmStatic
	fun <T : Packet> register(packetClass: Class<T>, packetDecoder: PacketDecoder<T>) {
		(elements as FreezableMap)[packetClass] = packetDecoder
	}
}