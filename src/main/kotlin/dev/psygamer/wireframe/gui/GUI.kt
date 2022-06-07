package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.gui.widget.Widget

abstract class GUI {

	private val widgets: List<Widget>

	init {
		this.widgets = WidgetCompiler.compileWidgets(this::setup)
		this.widgets.forEach {
			it.compileChildren()
			it.applyModifiers(PoseStack(), ScreenRenderHelper.screenWidth, ScreenRenderHelper.screenHeight)
		}
	}

	abstract fun setup()

	fun render() {
		this.widgets.forEach {
			it.render()
		}
	}
}