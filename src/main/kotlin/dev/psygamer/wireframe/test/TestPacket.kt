package dev.psygamer.wireframe.test

import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.api.network.Packet
import dev.psygamer.wireframe.api.network.PacketBuffer
import dev.psygamer.wireframe.api.network.PacketDecoder
import net.minecraftforge.fml.common.thread.SidedThreadGroups
import net.minecraftforge.fml.loading.FMLEnvironment

class TestPacket(val msg: String) : Packet() {
	
	override fun encode(packetBuffer: PacketBuffer) {
		Wireframe.LOGGER.info(
			"Encoded TestPacket on ${FMLEnvironment.dist}, Client=${Thread.currentThread().threadGroup == SidedThreadGroups.CLIENT}, Server=${Thread.currentThread().threadGroup == SidedThreadGroups.SERVER} with msg = $msg"
		)
		packetBuffer.writeString(msg)
	}
	
	override fun handle() {
		Wireframe.LOGGER.info(
			"Received TestPacket on ${FMLEnvironment.dist},Client=${Thread.currentThread().threadGroup == SidedThreadGroups.CLIENT}, Server=${Thread.currentThread().threadGroup == SidedThreadGroups.SERVER} with msg = $msg"
		)
	}
}

object TestPacketDecoder : PacketDecoder<TestPacket>() {
	
	override fun decode(packetBuffer: PacketBuffer): TestPacket {
		val msg = packetBuffer.readString()
		Wireframe.LOGGER.info(
			"Decoded TestPacket on ${FMLEnvironment.dist},Client=${Thread.currentThread().threadGroup == SidedThreadGroups.CLIENT}, Server=${Thread.currentThread().threadGroup == SidedThreadGroups.SERVER} with msg = $msg"
		)
		return TestPacket(msg)
	}
}