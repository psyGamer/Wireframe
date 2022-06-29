package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.gui.WidgetCompiler
import dev.psygamer.wireframe.gui.modifier.Modifier

abstract class ParentWidget(
	modifiers: Modifier? = null,
	private val childrenFn: (() -> Unit),
) : Widget(modifiers) {

	constructor(childrenFn: (() -> Unit)) : this(null, childrenFn)

	var children = emptyList<Widget>()
		private set

	override val contentWidth = children.maxOf { it.width }
	override val contentHeight = children.maxOf { it.height }

	internal fun compileChildren() {
		this.children = WidgetCompiler.compileWidgets(this, childrenFn)
	}
}