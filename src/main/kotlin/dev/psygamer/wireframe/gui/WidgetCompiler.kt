package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.debug.debug
import dev.psygamer.wireframe.gui.widget.Widget

internal object WidgetCompiler {

	private var currentWidgets = mutableListOf<Widget>()

	internal fun compileWidgets(widgetFn: () -> Unit): List<Widget> {
		debug {
			if (currentWidgets.isNotEmpty())
				throw IllegalStateException("Cannot compile widgets while other widgets are being compiled!")
		}

		// In this call the constructors will be called, which than calls newWidgetCallback and adds the widgets.
		widgetFn()

		val widgets = currentWidgets.toList() // Copy
		currentWidgets.clear()
		return widgets
	}

	internal fun newWidgetCallback(widget: Widget) {
		currentWidgets.add(widget)
	}
}