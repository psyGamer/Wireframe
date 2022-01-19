package dev.psygamer.wireframe.nativeapi.client.screen

import dev.psygamer.wireframe.api.client.screen.Screen
import net.minecraft.client.Minecraft

object NativeScreenManager {
	
	fun openScreen(screen: Screen) {
		val oldScreen = Minecraft.getInstance().screen
		
		if (oldScreen is NativeScreen)
			oldScreen.screen.onClose(screen)
		
		Minecraft.getInstance().setScreen(screen.mcNative)
		
		if (oldScreen is NativeScreen)
			screen.onOpen(oldScreen.screen)
		else
			screen.onOpen(null)
	}
}