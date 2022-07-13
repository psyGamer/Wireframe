package dev.psygamer.wireframe.api.client.input

import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.InputEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import org.lwjgl.glfw.GLFW.*
import dev.psygamer.wireframe.event.nativeapi.NativeForgeEventBusSubscriber

private val MINECRAFT = Minecraft.getInstance()

@NativeForgeEventBusSubscriber
object Mouse {

	val x: Double get() = MINECRAFT.mouseHandler.xpos() * MINECRAFT.window.guiScaledWidth / MINECRAFT.window.screenWidth
	val y: Double get() = MINECRAFT.mouseHandler.ypos() * MINECRAFT.window.guiScaledHeight / MINECRAFT.window.screenHeight

	val xVelocity: Double get() = MINECRAFT.mouseHandler.xVelocity
	val yVelocity: Double get() = MINECRAFT.mouseHandler.yVelocity

	var isLeftButtonPressed: Boolean = false
		private set
	var isRightButtonPressed: Boolean = false
		private set
	var isMiddleButtonPressed: Boolean = false
		private set

	fun isInArea(x: Int, y: Int, width: Int, height: Int): Boolean {
		val xPos = this.x
		val yPos = this.y
		return xPos > x && yPos > y && xPos < (x + width) && yPos < (y + height)
	}

	@JvmStatic
	@SubscribeEvent
	fun onRawMouseClicked(event: InputEvent.RawMouseEvent) {
		if (event.action != GLFW_PRESS && event.action != GLFW_RELEASE) return

		when (event.button) {
			GLFW_MOUSE_BUTTON_LEFT -> this.isLeftButtonPressed = event.action == GLFW_PRESS
			GLFW_MOUSE_BUTTON_RIGHT -> this.isRightButtonPressed = event.action == GLFW_PRESS
			GLFW_MOUSE_BUTTON_MIDDLE -> this.isMiddleButtonPressed = event.action == GLFW_PRESS
		}
	}
}