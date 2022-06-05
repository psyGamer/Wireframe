package dev.psygamer.wireframe.test

import net.minecraft.client.Minecraft
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.gui.GUI
import dev.psygamer.wireframe.gui.widget.*
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.*

object TestGUI : GUI() {

	override fun setup() {
		TestButton(padding = 2) {
			TestText("Abc")
		}
	}
}

private class TestButton(private val padding: Int, children: () -> Unit) : Widget(children) {

	override fun render(poseStack: PoseStack) {
		ScreenRenderHelper.drawTexturedQuad(100, 0, 200, 100, 0, 0, 16, 16, Identifier(path = "textures/block/stone.png"))
		children.render(poseStack)
	}

	override val width: Int
		get() = children.width + padding * 2
	override val height: Int
		get() = children.height + padding * 2
}

private class TestText(val text: String) : Widget() {

	override fun render(poseStack: PoseStack) {
		Minecraft.getInstance().font.draw(poseStack.mcNative, text, 0.0f, 0.0f, Color.WHITE.mcNative)
	}

	override val width = Minecraft.getInstance().font.width(text)
	override val height = Minecraft.getInstance().font.lineHeight
}