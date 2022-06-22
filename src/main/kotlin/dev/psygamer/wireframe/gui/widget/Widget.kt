package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.gui.*
import dev.psygamer.wireframe.gui.modifier.*
import dev.psygamer.wireframe.util.math.*

abstract class Widget(
	private val modifiers: Modifier? = null,
	private val childrenFn: (() -> Unit)? = null,
) {

	protected val boxModel: BoxModelStack.Entry

	constructor(childrenFn: (() -> Unit)?) : this(null, childrenFn)

	protected var children = emptyList<Widget>()
		private set

	init {
		WidgetCompiler.newWidgetCallback(this)
	}

	abstract fun setup()

	internal fun computeDimensions(): Dimension2I {
		if (children.isEmpty() && this is CanvasWidget)
			return Dimension2I(contentWidth, contentHeight)

		val currentDimensions = MutableDimension2I(0, 0)
		children.forEach {
			val dimensions = it.computeDimensions()
			if (dimensions.width > currentDimensions.width)
				currentDimensions.width = dimensions.width
			if (dimensions.height > currentDimensions.height)
				currentDimensions.height = dimensions.height
		}
		return currentDimensions.asImmutable()
	}

	internal fun applyModifiers(boxModelStack: BoxModelStack) {
		boxModelStack.push()



		modifiers?.applyAll(boxModelStack.last)
		this.children.forEach { it.applyModifiers(boxModelStack) }
		boxModelStack.pop()
	}

	internal fun compileChildren() {
		if (childrenFn != null)
			this.children = WidgetCompiler.compileWidgets(childrenFn)
	}
}
