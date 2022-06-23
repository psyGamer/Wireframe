package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.*
import dev.psygamer.wireframe.gui.modifier.*
import dev.psygamer.wireframe.util.math.Dimension2I

abstract class Widget(
	private val modifiers: Modifier? = null,
	private val childrenFn: (() -> Unit),
) {

	private lateinit var boxModel: BoxModelStack.Entry

	constructor(childrenFn: (() -> Unit)) : this(null, childrenFn)

	protected var children = emptyList<Widget>()
		private set

	init {
		WidgetCompiler.newWidgetCallback(this)
	}

	abstract fun render(poseStack: PoseStack)

	val width: Int
		get() = _width.value
	val height: Int
		get() = _height.value
	val size
		get() = Dimension2I(width, height)

	private val _width = lazy { children.maxOf { it.width } }
	private val _height = lazy { children.maxOf { it.height } }

	internal fun compileChildren() {
		this.children = WidgetCompiler.compileWidgets(childrenFn)
	}

	internal fun applyModifiers(boxModelStack: BoxModelStack) {
		boxModelStack.push()
		boxModel = boxModelStack.last
		boxModel.contentSize = size
		modifiers?.applyAll(boxModel)
		children.forEach { it.applyModifiers(boxModelStack) }
		boxModelStack.pop()
	}
}
