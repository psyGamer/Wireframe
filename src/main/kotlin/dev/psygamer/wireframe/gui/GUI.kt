package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.gui.widget.Widget

abstract class GUI {

	private val widgets: List<Widget>

	init {
		this.widgets = WidgetCompiler.compileWidgets(this::setup)
		this.widgets.forEach(Widget::compileChildren)
	}

	abstract fun setup()

	fun render(poseStack: PoseStack) {
		poseStack.translate(0.5f, 0f, 0f)
		this.widgets.forEach {
			it.applyModifiers(poseStack, ScreenRenderHelper.screenWidth, ScreenRenderHelper.screenHeight)
			it.render(poseStack)
		}
	}
}