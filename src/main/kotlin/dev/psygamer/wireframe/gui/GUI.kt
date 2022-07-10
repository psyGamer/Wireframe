package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.debug.profiling.profile
import dev.psygamer.wireframe.gui.modifier.applyModifiers
import dev.psygamer.wireframe.gui.widget.Widget

abstract class GUI {

	internal var widgets = emptyList<Widget>()

	private val widgetObserver = WidgetObserver(this)

	abstract fun setup()

	fun render() {
		profile("recompileGUI", this::recompile)

		this.widgets.forEach {
			it.renderBackground()
			it.render()
			it.renderForeground()
		}
	}

	internal fun recompile() {
		profile("compileWidgets") { this.widgets = WidgetCompiler.compileWidgets(null, this::setup) }
		profile("applyModifiers") { this.widgets.onEach(Widget::applyModifiers) }
	}
}