package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.WidgetCompiler
import dev.psygamer.wireframe.gui.modifier.ModifierBuilder

abstract class ParentWidget(
	modifier: ModifierBuilder? = null,
	private val childrenFn: (() -> Unit),
) : Widget(modifier) {

	internal lateinit var childrenPoseStacks: List<PoseStack>

	constructor(childrenFn: (() -> Unit)) : this(null, childrenFn)

	var children = emptyList<Widget>()
		protected set

	override fun render() = children.forEach { it.render() }

	override val contentWidth get() = if (children.isEmpty()) 0 else children.maxOf { it.lazyContentWidth.value }
	override val contentHeight get() = if (children.isEmpty()) 0 else children.maxOf { it.lazyContentHeight.value }

	internal fun compileChildren() {
		this.children = WidgetCompiler.compileWidgets(this, childrenFn)
		this.childrenPoseStacks = this.children.map { it.poseStack }
	}
}