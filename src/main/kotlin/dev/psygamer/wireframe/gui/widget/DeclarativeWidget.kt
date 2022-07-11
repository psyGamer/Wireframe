package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.gui.*

abstract class DeclarativeWidget(modifier: ModifierBuilder? = null, childrenFn: () -> Unit) :
	ParentWidget(modifier, childrenFn) {

	abstract fun setup()

	protected fun children() = children.forEach(WidgetCompiler::newWidgetCallback)

	final override fun render() {
		super.render()
	}
}