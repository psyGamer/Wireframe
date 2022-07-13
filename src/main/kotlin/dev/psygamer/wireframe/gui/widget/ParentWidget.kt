package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.*

abstract class ParentWidget(
	modifier: ModifierBuilder? = null,
	private val childrenFn: (() -> Unit),
) : Widget(modifier) {

	internal lateinit var childrenPoseStacks: List<PoseStack>

	constructor(childrenFn: (() -> Unit)) : this(null, childrenFn)

	var children = emptyList<Widget>()
		protected set

	override fun render() = children.forEach { it.render() }

	override val contentWidth get() = if (children.isEmpty()) 0 else children.maxOf { it.contentWidth }
	override val contentHeight get() = if (children.isEmpty()) 0 else children.maxOf { it.contentHeight }

	internal fun compileChildren() {
		this.children = WidgetCompiler.compileWidgets(this, childrenFn)
		this.childrenPoseStacks = this.children.map { it.poseStack }
	}
}