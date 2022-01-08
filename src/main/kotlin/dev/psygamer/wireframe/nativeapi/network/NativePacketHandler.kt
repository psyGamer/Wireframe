package dev.psygamer.wireframe.nativeapi.network

import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.api.entity.Player
import dev.psygamer.wireframe.api.network.Packet
import dev.psygamer.wireframe.nativeapi.mcNative
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.network.NetworkEvent
import net.minecraftforge.fml.network.NetworkRegistry
import net.minecraftforge.fml.network.PacketDistributor
import java.util.function.Supplier

object NativePacketHandler {
	
	private const val PROTOCOL_VERSION = "1"
	
	private var channelID = 0
	private val mcNative = NetworkRegistry.newSimpleChannel(
		ResourceLocation(Wireframe.MODID, "main"), { PROTOCOL_VERSION }, { it == PROTOCOL_VERSION }, { it == PROTOCOL_VERSION }
	)
	
	fun sendToServer(packet: Packet) {
		mcNative.sendToServer(packet)
	}
	
	fun sendToAllClients(packet: Packet) {
		mcNative.sendToServer(packet)
	}
	
	fun sendToClient(packet: Packet, client: Player) {
		mcNative.send(PacketDistributor.PLAYER.with { client.mcNative as ServerPlayerEntity }, packet)
	}
	
	@Suppress("INACCESSIBLE_TYPE", "UNCHECKED_CAST")
	fun <T : Packet> registerPacket(
		packetClass: Class<T>,
		
		encodeFunc: (T, PacketBuffer) -> Unit, decodeFunc: (PacketBuffer) -> Packet,
		handleFunc: (Packet, Supplier<NetworkEvent.Context>) -> Unit
	) {								  /* This is fine since PacketRegistry enforces it. vvvv */
		mcNative.registerMessage(channelID++, packetClass, encodeFunc, { decodeFunc(it) as T }, handleFunc)
	}
}