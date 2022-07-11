package dev.psygamer.wireframe.nativeapi.client.screen

import net.minecraft.client.Minecraft
import dev.psygamer.wireframe.api.client.screen.Screen

object NativeScreenManager {

	fun openScreen(screen: Screen) {
		val oldScreen = Minecraft.getInstance().screen
		if (oldScreen is NativeScreen)
			oldScreen.screen.onClose()

		Minecraft.getInstance().setScreen(screen.mcNative)
		screen.onOpen()
	}
}