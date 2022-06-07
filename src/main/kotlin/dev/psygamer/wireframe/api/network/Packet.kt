package dev.psygamer.wireframe.api.network

abstract class Packet {

	abstract fun encode(packetBuffer: PacketBuffer)
	abstract fun handle()
}

abstract class PacketDecoder<T : Packet> {

	abstract fun decode(packetBuffer: PacketBuffer): T
}