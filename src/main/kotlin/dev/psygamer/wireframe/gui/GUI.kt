package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.modifier.applyModifiers
import dev.psygamer.wireframe.gui.widget.Widget

abstract class GUI {

	internal var widgets = emptyList<Widget>()

	private val widgetObserver = WidgetObserver(this)

	abstract fun setup()

	fun render() {
		this.recompile()

		this.widgets.forEach {
			it.renderBackground()
			it.render()
			it.renderForeground()
		}
	}

	internal fun recompile() {
		this.widgets = WidgetCompiler.compileWidgets(null, this::setup)
			.onEach(Widget::applyModifiers)
	}
}