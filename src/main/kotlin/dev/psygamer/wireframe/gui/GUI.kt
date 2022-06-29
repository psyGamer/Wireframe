package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.gui.widget.Widget
import dev.psygamer.wireframe.util.types.Reactive

abstract class GUI : Reactive.Subscriber<Any> {

	private var widgets: List<Widget>

	init {
		this.widgets = WidgetCompiler.compileWidgets(this::setup)
		this.widgets.forEach {
			it.applyModifiers(PoseStack(), ScreenRenderHelper.screenWidth, ScreenRenderHelper.screenHeight)
		}
	}

	abstract fun setup()

	fun render() {
		this.widgets.forEach {
			it.render()
		}
	}

	protected fun <T : Any> ref(value: T): Reactive<T> {
		val ref = Reactive(value)
		ref.subscribe(this)
	}

	override fun onValueChanged(oldValue: Any, newValue: Any) = recompile()

	internal fun recompile() {
		this.widgets = WidgetCompiler.compileWidgets(this::setup)
		this.widgets.forEach {
			it.applyModifiers(PoseStack(), ScreenRenderHelper.screenWidth, ScreenRenderHelper.screenHeight)
		}
	}
}