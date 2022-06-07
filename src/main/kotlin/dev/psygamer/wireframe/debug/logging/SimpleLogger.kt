package dev.psygamer.wireframe.debug.logging

interface SimpleLogger {

	fun trace(text: String)
	fun info(text: String)
	fun warn(text: String)
	fun error(text: String)
	fun fatal(text: String)
}