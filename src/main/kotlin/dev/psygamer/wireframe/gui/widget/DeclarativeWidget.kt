package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.gui.WidgetCompiler
import dev.psygamer.wireframe.gui.modifier.ModifierBuilder

abstract class DeclarativeWidget(modifier: ModifierBuilder? = null, childrenFn: () -> Unit) :
	ParentWidget(modifier, childrenFn) {

	abstract fun setup()

	protected fun children() = children.forEach(WidgetCompiler::newWidgetCallback)

	final override fun render() {
		TODO("Not yet implemented")
	}
}