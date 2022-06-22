package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.modifier.Modifier

abstract class CanvasWidget(modifier: Modifier? = null) : Widget(modifier, { }) {

	final override fun setup() {}

	abstract fun render(poseStack: PoseStack)

	abstract val contentWidth: Int
	abstract val contentHeight: Int
}