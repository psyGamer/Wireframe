package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.api.client.screen.*
import dev.psygamer.wireframe.api.client.screen.ScreenManager.open
import dev.psygamer.wireframe.debug.profiling.profile
import dev.psygamer.wireframe.gui.widget.Widget

abstract class GUI {

	internal var widgets = emptyList<Widget>()

	abstract fun setup()

	fun open() = GUIScreen(this).open()

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

private class GUIScreen(private val gui: GUI) : Screen() {

	override fun render() {
		runCatching { gui.render() }
	}
}
