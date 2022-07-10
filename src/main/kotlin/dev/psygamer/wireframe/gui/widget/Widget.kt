package dev.psygamer.wireframe.gui.widget

import java.util.*
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.WidgetCompiler
import dev.psygamer.wireframe.gui.modifier.Modifier
import dev.psygamer.wireframe.util.math.Dimension2I

abstract class Widget(internal val modifiers: Modifier? = null) {

	var parent: Widget? = WidgetCompiler.currentParent
		private set

	val poseStack: PoseStack = this.parent?.poseStack ?: PoseStack()

	init {
		WidgetCompiler.newWidgetCallback(this)
	}

	abstract fun render()

	open fun renderForeground() {}
	open fun renderBackground() {}

	val width: Int
		get() = modifiedWidth.orElseGet { lazyWidth.value }
	val height: Int
		get() = modifiedHeight.orElseGet { lazyHeight.value }
	val size
		get() = Dimension2I(width, height)

	protected abstract val contentWidth: Int
	protected abstract val contentHeight: Int

	internal var modifiedWidth = Optional.empty<Int>()
	internal var modifiedHeight = Optional.empty<Int>()

	private val lazyWidth = lazy { contentWidth }
	private val lazyHeight = lazy { contentHeight }
}
