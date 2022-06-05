package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.api.client.screen.Screen
import dev.psygamer.wireframe.api.client.screen.ScreenManager.open

internal class GUIScreen(private val gui: GUI) : Screen() {

	override fun render() {
		gui.render(PoseStack())
	}
}

fun GUI.open() = GUIScreen(this).open()