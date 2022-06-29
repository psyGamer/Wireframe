package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.WidgetCompiler
import dev.psygamer.wireframe.gui.modifier.*
import dev.psygamer.wireframe.util.types.Observable

abstract class DeclarativeWidget(modifiers: Modifier? = null, childrenFn: () -> Unit) :
	ParentWidget(modifiers, childrenFn), Observable.Subscriber<Any> {

	abstract fun setup()

	protected fun children() = children.forEach(WidgetCompiler::newWidgetCallback)

	protected fun <T : Any> reactive(value: T) =
		Observable(value).also {
			it.subscribe(this)
		}

	final override fun render(poseStack: PoseStack) {
		TODO("Not yet implemented")
	}

	final override fun onValueChanged(oldValue: Any, newValue: Any) {
		this.children = WidgetCompiler.compileWidgets(null, this::setup)
			.onEach(Widget::applyModifiers)
	}
}