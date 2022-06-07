package dev.psygamer.wireframe.api.network

import dev.psygamer.wireframe.api.entity.Player
import dev.psygamer.wireframe.nativeapi.network.NativePacketHandler

object PacketHandler {

	fun sendToServer(packet: Packet) =
		NativePacketHandler.sendToServer(packet)

	fun sendToAllClients(packet: Packet) =
		NativePacketHandler.sendToAllClients(packet)

	fun sendToClient(packet: Packet, client: Player) =
		NativePacketHandler.sendToClient(packet, client)

	fun sendToClients(packet: Packet, clients: Array<out Player>) {
		clients.forEach { sendToClient(packet, it) }
	}
}
