package dev.psygamer.wireframe.test

import net.minecraft.client.Minecraft
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.gui.GUI
import dev.psygamer.wireframe.gui.modifier.*
import dev.psygamer.wireframe.gui.widget.*
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.Color

object TestGUI : GUI() {

	override fun setup() {
		TestButton(
			modifier = Modifier
				.align(Alignment.CENTER)
				.margin(5)
		) {
			TestText(
				text = "Abc",
				modifier = Modifier.align(Alignment.TOP_RIGHT)
			)
		}
	}
}

private class TestButton(modifier: Modifier, children: () -> Unit) : Widget(modifier, children) {

	override fun render() {
		ScreenRenderHelper.drawQuad(poseStack, children.width, children.height, Color.DARK_GRAY)
		children.render()
	}

	override val contentWidth: Int
		get() = children.width
	override val contentHeight: Int
		get() = children.height
}

private class TestText(val text: String, modifier: Modifier) : Widget(modifier) {

	override fun render() {
		Minecraft.getInstance().font.draw(poseStack.mcNative, text, 0.0f, 0.0f, Color.WHITE.mcNative)
	}

	override val contentWidth = Minecraft.getInstance().font.width(text)
	override val contentHeight = Minecraft.getInstance().font.lineHeight
}