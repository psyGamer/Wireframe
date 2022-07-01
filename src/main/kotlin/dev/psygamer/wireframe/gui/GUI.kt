package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.modifier.applyModifiers
import dev.psygamer.wireframe.gui.widget.Widget

abstract class GUI {

	internal var widgets = //emptyList<Widget>()
		WidgetCompiler.compileWidgets(null, this::setup)
			.onEach(Widget::applyModifiers)

	// This must be constructed after all widgets have been compiled.
	private val widgetObserver = WidgetObserver(this)

	abstract fun setup()

	fun render() {
		this.widgets.forEach {
			val poseStack = PoseStack()
			it.renderBackground(poseStack)
			it.render(poseStack)
			it.renderForeground(poseStack)
		}
	}

	// This is the only easy way to do this with our current architecture
	fun markChanged() = recompile()

	internal fun recompile() {
		this.widgets = WidgetCompiler.compileWidgets(null, this::setup)
			.onEach(Widget::applyModifiers)
	}
}