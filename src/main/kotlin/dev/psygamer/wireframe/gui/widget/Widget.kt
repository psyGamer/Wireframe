package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.WidgetCompiler
import dev.psygamer.wireframe.gui.modifier.Modifier

abstract class Widget(
	private val modifier: Modifier? = null,
	private val childrenFn: (() -> Unit)? = null,
) {

	protected lateinit var poseStack: PoseStack
		private set

	constructor(childrenFn: (() -> Unit)?) : this(null, childrenFn)

	protected var children = emptyList<Widget>()
		private set

	init {
		WidgetCompiler.newWidgetCallback(this)
	}

	abstract fun render()

	var actualWidth: Int = contentWidth
		private set
	var actualHeight: Int = contentHeight
		private set

	protected abstract val contentWidth: Int
	protected abstract val contentHeight: Int

	internal fun applyModifiers(poseStack: PoseStack, parentWidth: Int, parentHeight: Int) {
		val (newWidth, newHeight) =
			if (modifier != null && modifier != Modifier)
				this.modifier.apply(poseStack, this.contentWidth, this.contentHeight, parentWidth, parentHeight)
			else
				contentWidth to contentHeight

		this.actualWidth = newWidth
		this.actualHeight = newHeight
		this.poseStack = poseStack.clone()

		this.children.forEach {
			poseStack.push()
			it.applyModifiers(poseStack, this.actualWidth, this.actualHeight)
			poseStack.pop()
		}
	}

	internal fun compileChildren() {
		if (childrenFn != null) {
			this.children = WidgetCompiler.compileWidgets(childrenFn)
			this.children.forEach { it.compileChildren() }
		}
	}
}

fun Collection<Widget>.render() {
	this.forEach { it.render() }
}

data class BoxModel(
	val contentWidth: Int,
	val contentHeight: Int,

	val paddingTop: Int,
	val paddingRight: Int,
	val paddingBottom: Int,
	val paddingLeft: Int,

	val outlineTop: Int,
	val outlineRight: Int,
	val outlineBottom: Int,
	val outlineLeft: Int,

	val borderTop: Int,
	val borderRight: Int,
	val borderBottom: Int,
	val borderLeft: Int,

	val marginTop: Int,
	val marginRight: Int,
	val marginBottom: Int,
	val marginLeft: Int,
)

val Collection<Widget>.width: Int
	get() {
		var maxWidth = 0
		this.forEach {
			maxWidth = maxWidth.coerceAtLeast(it.actualWidth)
		}
		return maxWidth
	}

val Collection<Widget>.height: Int
	get() {
		var maxHeight = 0
		this.forEach {
			maxHeight = maxHeight.coerceAtLeast(it.actualHeight)
		}
		return maxHeight
	}

val Collection<Widget>.widthHeight: Pair<Int, Int>
	get() {
		var maxWidth = 0
		var maxHeight = 0
		this.forEach {
			maxWidth = maxWidth.coerceAtLeast(it.actualWidth)
			maxHeight = maxHeight.coerceAtLeast(it.actualHeight)
		}
		return maxWidth to maxHeight
	}