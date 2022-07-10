package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.api.client.screen.Screen
import dev.psygamer.wireframe.api.client.screen.ScreenManager.open

internal class GUIScreen(private val gui: GUI) : Screen() {

	override fun render() {
		runCatching { gui.render() }
	}
}

fun GUI.open() = GUIScreen(this).open()