package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.widget.Widget

abstract class GUI {

	private val widgets: List<Widget>

	init {
		this.widgets = WidgetCompiler.compileWidgets(this::setup)
		this.widgets.forEach(Widget::compileChildren)
	}

	abstract fun setup()

	fun render(poseStack: PoseStack) {
		this.widgets.forEach { it.render(poseStack) }
	}
}