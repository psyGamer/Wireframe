package dev.psygamer.wireframe.gui.widget

import java.util.*
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.WidgetCompiler
import dev.psygamer.wireframe.gui.modifier.Modifier
import dev.psygamer.wireframe.util.math.Dimension2I

abstract class Widget(
	internal val modifiers: Modifier? = null,
	private val childrenFn: (() -> Unit),
) {

	constructor(childrenFn: (() -> Unit)) : this(null, childrenFn)

	var parent: Widget? = WidgetCompiler.currentParent
		private set
	var children = emptyList<Widget>()
		private set

	val poseStack: PoseStack = this.parent?.poseStack ?: PoseStack()

	init {
		WidgetCompiler.newWidgetCallback(this)
	}

	abstract fun render(poseStack: PoseStack)

	val width: Int
		get() = modifiedWidth.orElseGet { _width.value }
	val height: Int
		get() = modifiedHeight.orElseGet { _height.value }
	val size
		get() = Dimension2I(width, height)

	internal var modifiedWidth = Optional.empty<Int>()
	internal var modifiedHeight = Optional.empty<Int>()

	private val _width = lazy { children.maxOf { it.width } }
	private val _height = lazy { children.maxOf { it.height } }

	internal fun compileChildren() {
		this.children = WidgetCompiler.compileWidgets(this, childrenFn)
	}
}
