package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.gui.WidgetCompiler
import dev.psygamer.wireframe.gui.modifier.Modifier

abstract class DeclarativeWidget(modifiers: Modifier? = null, childrenFn: () -> Unit) : ParentWidget(modifiers, childrenFn) {

	abstract fun setup()

	protected fun children() = children.forEach(WidgetCompiler::newWidgetCallback)

	final override fun render() {
		TODO("Not yet implemented")
	}
}