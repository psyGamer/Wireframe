package dev.psygamer.wireframe.api.client.screen

import dev.psygamer.wireframe.nativeapi.client.screen.NativeScreen
import java.nio.file.Path

open class Screen {
	
	val mcNative: NativeScreen = NativeScreen(this)
	
	fun isMouseOver(mouseX: Double, mouseY: Double) =
		mcNative.isMouseOver(mouseX, mouseY)
	
	open fun tick() {}
	open fun render() {}
	
	open fun onOpen(oldScreen: Screen?) {}
	open fun onClose(newScreen: Screen?) {}
	
	open fun onMouseMoved(mouseX: Double, mouseY: Double) {}
	open fun onMousePressed(mouseX: Double, mouseY: Double, mouseButton: Int) {}
	open fun onMouseReleased(mouseX: Double, mouseY: Double, mouseButton: Int) {}
	open fun onMouseDragged(mouseX: Double, mouseY: Double, deltaX: Double, deltaY: Double, mouseButton: Int) {}
	open fun onMouseScrolled(mouseX: Double, mouseY: Double, amount: Double) {}
	
	open fun onKeyPressed(keyCode: Int, scanCode: Int, modifiers: Int) {}
	open fun onKeyReleased(keyCode: Int, scanCode: Int, modifiers: Int) {}
	
	open fun onFilesDropped(filePaths: List<Path>) {}
}