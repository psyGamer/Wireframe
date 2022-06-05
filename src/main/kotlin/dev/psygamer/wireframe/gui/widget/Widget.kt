package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.WidgetCompiler

abstract class Widget(private val childrenFn: (() -> Unit)? = null) {

	protected var children = emptyList<Widget>()
		private set

	init {
		WidgetCompiler.newWidgetCallback(this)
	}

	abstract fun render(poseStack: PoseStack)

	abstract val width: Int
	abstract val height: Int

	internal fun compileChildren() {
		if (childrenFn != null) {
			this.children = WidgetCompiler.compileWidgets(childrenFn)
			this.children.forEach { it.compileChildren() }
		}
	}
}

fun Collection<Widget>.render(poseStack: PoseStack) {
	this.forEach { it.render(poseStack) }
}

val Collection<Widget>.width: Int
	get() {
		var maxWidth = 0
		this.forEach {
			maxWidth = maxWidth.coerceAtLeast(it.width)
		}
		return maxWidth
	}

val Collection<Widget>.height: Int
	get() {
		var maxHeight = 0
		this.forEach {
			maxHeight = maxHeight.coerceAtLeast(it.height)
		}
		return maxHeight
	}

val Collection<Widget>.widthHeight: Pair<Int, Int>
	get() {
		var maxWidth = 0
		var maxHeight = 0
		this.forEach {
			maxWidth = maxWidth.coerceAtLeast(it.width)
			maxHeight = maxHeight.coerceAtLeast(it.height)
		}
		return maxWidth to maxHeight
	}