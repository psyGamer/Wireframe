package dev.psygamer.wireframe.debug.logging

/** A logger which just doesn't log anything */
object VoidLogger : SimpleLogger {

	override fun trace(text: String) {}
	override fun info(text: String) {}
	override fun warn(text: String) {}
	override fun error(text: String) {}
	override fun fatal(text: String) {}
}