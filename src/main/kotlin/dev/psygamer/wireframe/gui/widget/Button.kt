package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.util.Identifier

class Button(private val padding: Int, children: () -> Unit) : Widget(children) {

	override fun render(poseStack: PoseStack) {
		ScreenRenderHelper.drawTexturedQuad(100, 0, 200, 100, 0, 0, 16, 16, Identifier(path = "textures/block/stone.png"))
		children.render(poseStack)
	}

	override val width: Int
		get() = children.width + padding * 2
	override val height: Int
		get() = children.height + padding * 2
}