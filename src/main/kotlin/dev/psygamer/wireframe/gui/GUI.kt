package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.gui.widget.Widget

abstract class GUI {

	private var widgets: List<Widget>

	init {
		this.widgets = WidgetCompiler.compileWidgets(this::setup)
		this.widgets.forEach {
			it.applyModifiers(PoseStack(), ScreenRenderHelper.screenWidth, ScreenRenderHelper.screenHeight)
		}
	}

	abstract fun setup()

	fun render() {
		this.widgets.forEach {
			it.render()
		}
	}

	internal fun recompile() {
		this.widgets = WidgetCompiler.compileWidgets(this::setup)
		this.widgets.forEach {
			it.applyModifiers(PoseStack(), ScreenRenderHelper.screenWidth, ScreenRenderHelper.screenHeight)
		}
	}
}