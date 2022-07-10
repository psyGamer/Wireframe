package dev.psygamer.wireframe.test

import net.minecraft.client.Minecraft
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import kotlin.random.Random
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.event.nativeapi.NativeForgeEventBusSubscriber
import dev.psygamer.wireframe.gui.*
import dev.psygamer.wireframe.gui.modifier.*
import dev.psygamer.wireframe.gui.widget.*
import dev.psygamer.wireframe.nativeapi.client.screen.NativeScreen
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.Color

@NativeForgeEventBusSubscriber
object TestGUI : GUI() {

	var countDown = 20
	var color: Color = Color.WHITE

	@JvmStatic
	@SubscribeEvent
	fun onTick(event: TickEvent.ClientTickEvent) {
		if (Minecraft.getInstance().screen !is NativeScreen ||
			(Minecraft.getInstance().screen as NativeScreen).screen !is GUIScreen
		) return
		if (--countDown <= 0) {
			countDown = 20
			color = Color(Random.Default.nextFloat(), Random.Default.nextFloat(), Random.Default.nextFloat(), 1.0f)
		}
	}

	override fun setup() {
		Text(modifier = Modifier.align(Alignment.TOP), text = "T")
		Text(modifier = Modifier.align(Alignment.BOTTOM), text = "B")
		Text(modifier = Modifier.align(Alignment.RIGHT), text = "R")
		Text(modifier = Modifier.align(Alignment.LEFT), text = "L")

		Text(modifier = Modifier.align(Alignment.TOP_RIGHT), text = "TR")
		Text(modifier = Modifier.align(Alignment.TOP_LEFT), text = "TL")
		Text(modifier = Modifier.align(Alignment.BOTTOM_RIGHT), text = "BR")
		Text(modifier = Modifier.align(Alignment.BOTTOM_LEFT), text = "BL")

		Button(
			backgroundColor = color,
			modifier = Modifier.align(Alignment.CENTER).width(30)
		) {
			Text(text = "Hello, World", modifier = WidthModifier(20))
		}
	}
}

private class Button(
	val backgroundColor: Color,
	modifier: Modifier? = null, children: () -> Unit,
) : ParentWidget(modifier, children) {

	override fun renderBackground() {
		ScreenRenderHelper.drawQuad(this.poseStack, this.width, this.height, backgroundColor)
	}
}

private class Text(val text: String, modifier: Modifier? = null) : Widget(modifier) {

	override fun render() {
		Minecraft.getInstance().font.draw(this.poseStack.mcNative, text, 0.0f, 0.0f, Color.WHITE.mcNative)
	}

	override val contentWidth = Minecraft.getInstance().font.width(text)
	override val contentHeight = Minecraft.getInstance().font.lineHeight
}