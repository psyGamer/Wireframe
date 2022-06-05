package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.gui.widget.Widget

abstract class GUI {

	private val widgets: List<Widget>

	init {
		this.widgets = WidgetCompiler.compileWidgets(this::setup)
		this.widgets.forEach { it.compileChildren() }
	}

	abstract fun setup()
}