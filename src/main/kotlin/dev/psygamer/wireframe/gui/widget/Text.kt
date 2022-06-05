package dev.psygamer.wireframe.gui.widget

import net.minecraft.client.Minecraft
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.Color

class Text(val text: String) : Widget() {

	override fun render(poseStack: PoseStack) {
		Minecraft.getInstance().font.draw(poseStack.mcNative, text, 0.0f, 0.0f, Color.WHITE.mcNative)
	}

	override val width = Minecraft.getInstance().font.width(text)
	override val height = Minecraft.getInstance().font.lineHeight
}