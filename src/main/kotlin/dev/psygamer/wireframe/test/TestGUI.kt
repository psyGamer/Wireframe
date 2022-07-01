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

	val state = true

	override fun setup() {
//		TestButton {
//			TestButton(
//				color = Color.DARK_GRAY,
//				modifier = Modifier
////				.align(Alignment.CENTER)
//					.margin(5)
//					.width(100)
//			) {
//				TestText(
//					text = "Abc",
//					modifier = Modifier//.align(Alignment.TOP_RIGHT)
//						.margin(10)
//				)
//			}
//		}
//		TestButton(
//			text = "Click me!",
//			modifier = Modifier
//				.align(Alignment.CENTER)
//				.padding(3)
//		)
		Button(onPressed = { state.value = !state.value }) {
			if (state.value)
				Text("Hello, world!")
			else
				Text("Moin, Servus, Moin!")
		}
	}
}

private class Stack(
	modifier: Modifier? = null, children: () -> Unit,
) : ParentWidget(modifier, children) {

	override fun render(poseStack: PoseStack) {
		children.forEach {
			poseStack.push()
			val posX = (this.width - it.width) / 2
			val posY = (this.height - it.height) / 2
			poseStack.translate(posX, posY, 0)
			it.render(poseStack)
			poseStack.pop()
		}
	}
}

private class Button(
	onPressed: (() -> Unit)?,
	modifier: Modifier? = null, children: () -> Unit,
) : ParentWidget(modifier, children) {

	override fun renderBackground(poseStack: PoseStack) {
		ScreenRenderHelper.drawQuad(poseStack, this.width, this.height)
	}
}

//private class TestButton(
//	private val color: Color = Color.DARK_RED,
//	modifier: Modifier? = null, children: () -> Unit,
//) : Widget(modifier, children) {
//
//	override fun render() {
//		ScreenRenderHelper.drawQuad(poseStack, children.width, children.height, color)
//		children.render()
//	}
//
//	override val contentWidth: Int
//		get() = children.width
//	override val contentHeight: Int
//		get() = children.height
//}

//private class TestButton(
//	private val color: Color = Color.DARK_RED,
//	private val text: String, modifier: Modifier? = null,
//) : Widget(modifier) {
//
//	override fun setup() {
//		TestText(text = text)
//	}
//}
//
private class Text(val text: String, modifier: Modifier? = null) : Widget(modifier) {

	override fun render(poseStack: PoseStack) {
		Minecraft.getInstance().font.draw(this.poseStack.mcNative, text, 0.0f, 0.0f, Color.WHITE.mcNative)
	}

	override val contentWidth = Minecraft.getInstance().font.width(text)
	override val contentHeight = Minecraft.getInstance().font.lineHeight
}