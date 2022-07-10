package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.gui.WidgetCompiler
import dev.psygamer.wireframe.gui.modifier.Modifier

abstract class ParentWidget(
	modifiers: Modifier? = null,
	private val childrenFn: (() -> Unit),
) : Widget(modifiers) {

	constructor(childrenFn: (() -> Unit)) : this(null, childrenFn)

	var children = emptyList<Widget>()
		protected set

	override fun render() = children.forEach { it.render() }

	override val contentWidth get() = if (children.isEmpty()) 0 else children.maxOf { it.width }
	override val contentHeight get() = if (children.isEmpty()) 0 else children.maxOf { it.height }

	internal fun compileChildren() {
		this.children = WidgetCompiler.compileWidgets(this, childrenFn)
	}
}