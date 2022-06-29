package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.modifier.applyModifiers
import dev.psygamer.wireframe.gui.widget.Widget
import dev.psygamer.wireframe.util.types.Observable

abstract class GUI : Observable.Subscriber<Any> {

	private var widgets =
		WidgetCompiler.compileWidgets(null, this::setup)
			.onEach(Widget::applyModifiers)

	abstract fun setup()

	fun render() {
		this.widgets.forEach {
			val poseStack = PoseStack()
			it.renderBackground(poseStack)
			it.render(poseStack)
			it.renderForeground(poseStack)
		}
	}

	protected fun <T : Any> reactive(value: T) =
		Observable(value).also {
			it.subscribe(this)
		}

	final override fun onValueChanged(oldValue: Any, newValue: Any) {
		this.widgets = WidgetCompiler.compileWidgets(null, this::setup)
			.onEach(Widget::applyModifiers)
	}
}