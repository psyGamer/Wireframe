package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.debug.debug
import dev.psygamer.wireframe.gui.widget.*

internal object WidgetCompiler {

	internal var currentParent: Widget? = null
		private set

	private var currentWidgets = mutableListOf<Widget>()
	private var compiling = false

	/* IMPORTANT: This is called multiple times per frame as long as a GUI is open!
	 *            It is important to keep performance in mind when editing this function! */
	internal fun compileWidgets(parent: Widget?, widgetFn: () -> Unit): List<Widget> {
		debug {
			if (compiling)
				throw IllegalStateException("Cannot compile widgets while other widgets are being compiled!")
		}
		currentWidgets.clear()

		compiling = true
		currentParent = parent
		// In this call the constructors will be called, which than calls newWidgetCallback and adds the widgets.
		runCatching(widgetFn).onFailure { Wireframe.LOGGER.error("Failed executing widget function of $parent", it) }

		currentParent = null
		compiling = false

		return currentWidgets.toList()
			.onEach { if (it is ParentWidget) it.compileChildren() }
	}

	internal fun newWidgetCallback(widget: Widget) {
		currentWidgets.add(widget)
	}
}