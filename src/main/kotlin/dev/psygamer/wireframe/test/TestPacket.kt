package dev.psygamer.wireframe.test

import dev.psygamer.wireframe.api.network.Packet
import dev.psygamer.wireframe.api.network.PacketBuffer
import dev.psygamer.wireframe.api.network.PacketDecoder

class TestPacket(val msg: String) : Packet() {
	
	override fun encode(packetBuffer: PacketBuffer) {
		packetBuffer.writeString(msg)
	}
	
	override fun handle() {
	
	}
}

object TestPacketDecoder : PacketDecoder<TestPacket>() {
	
	override fun decode(packetBuffer: PacketBuffer): TestPacket {
		return TestPacket(packetBuffer.readString())
	}
}