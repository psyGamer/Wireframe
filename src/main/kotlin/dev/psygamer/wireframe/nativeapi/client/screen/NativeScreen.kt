package dev.psygamer.wireframe.nativeapi.client.screen

import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.client.gui.screen.Screen
import net.minecraft.util.text.StringTextComponent
import java.nio.file.Path

class NativeScreen(
	val screen: dev.psygamer.wireframe.api.client.screen.Screen
) : Screen(StringTextComponent.EMPTY) {
	
	override fun tick() {
		screen.tick()
	}
	
	override fun render(matrix: MatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
		screen.render()
	}
	
	override fun mouseMoved(mouseX: Double, mouseY: Double) {
		screen.onMouseMoved(mouseX, mouseY)
	}
	
	override fun mouseClicked(mouseX: Double, mouseY: Double, mouseButton: Int): Boolean {
		screen.onMousePressed(mouseX, mouseY, mouseButton)
		return super.mouseClicked(mouseX, mouseY, mouseButton)
	}
	
	override fun mouseReleased(mouseX: Double, mouseY: Double, mouseButton: Int): Boolean {
		screen.onMouseReleased(mouseX, mouseY, mouseButton)
		return super.mouseReleased(mouseX, mouseY, mouseButton)
	}
	
	override fun mouseDragged(
		mouseX: Double, mouseY: Double, mouseButton: Int, deltaX: Double, deltaY: Double
	): Boolean {
		screen.onMouseDragged(mouseX, mouseY, deltaX, deltaY, mouseButton)
		return super.mouseDragged(mouseX, mouseX, mouseButton, deltaX, deltaY)
	}
	
	override fun mouseScrolled(mouseX: Double, mouseY: Double, amount: Double): Boolean {
		screen.onMouseScrolled(mouseX, mouseY, amount)
		return super.mouseScrolled(mouseX, mouseY, amount)
	}
	
	override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
		screen.onKeyPressed(keyCode, scanCode, modifiers)
		return super.keyPressed(keyCode, scanCode, modifiers)
	}
	
	override fun keyReleased(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
		screen.onKeyReleased(keyCode, scanCode, modifiers)
		return super.keyReleased(keyCode, scanCode, modifiers)
	}
	
	override fun onFilesDrop(filePaths: MutableList<Path>) {
		screen.onFilesDropped(filePaths)
	}
}