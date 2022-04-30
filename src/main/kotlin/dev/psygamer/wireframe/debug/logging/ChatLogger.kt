package dev.psygamer.wireframe.debug.logging

import net.minecraft.client.Minecraft
import net.minecraft.util.text.StringTextComponent
import org.apache.logging.log4j.Logger

class ChatLogger(private val name: String, private val consoleLogger: Logger? = null) : SimpleLogger {
	
	override fun trace(text: String) {
		logText("§a$text") // Light green
		consoleLogger?.trace(text)
	}
	
	override fun info(text: String) {
		logText("§b$text") // Aqua
		consoleLogger?.info(text)
	}
	
	override fun warn(text: String) {
		logText("§6$text") // Orange
		consoleLogger?.warn(text)
	}
	
	override fun error(text: String) {
		logText("§c$text") // Light red
		consoleLogger?.error(text)
	}
	
	override fun fatal(text: String) {
		logText("§4$text") // Dark red
		consoleLogger?.fatal(text)
	}
	
	private fun logText(text: String) {
		Minecraft.getInstance().player?.displayClientMessage(StringTextComponent("§8[§l§f$name§r§8] §r$text"), false)
	}
}