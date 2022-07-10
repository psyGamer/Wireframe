package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.debug.profiling.profile
import dev.psygamer.wireframe.gui.modifier.applyModifierSettings
import dev.psygamer.wireframe.gui.widget.Widget

abstract class GUI {

	internal var widgets = emptyList<Widget>()

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
		profile("applyModifiers") { this.widgets.onEach(Widget::applyModifierSettings) }
	}
}