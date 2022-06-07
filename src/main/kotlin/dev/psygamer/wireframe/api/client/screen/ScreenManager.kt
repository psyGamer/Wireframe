package dev.psygamer.wireframe.api.client.screen

import dev.psygamer.wireframe.nativeapi.client.screen.NativeScreenManager

object ScreenManager {

	fun Screen.open() = openScreen(this)

	fun openScreen(screen: Screen) =
		NativeScreenManager.openScreen(screen)
}