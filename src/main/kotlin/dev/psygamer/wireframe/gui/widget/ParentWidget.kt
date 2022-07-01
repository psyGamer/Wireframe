package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.WidgetCompiler
import dev.psygamer.wireframe.gui.modifier.Modifier

abstract class ParentWidget(
	modifiers: Modifier? = null,
	private val childrenFn: (() -> Unit),
) : Widget(modifiers) {

	constructor(childrenFn: (() -> Unit)) : this(null, childrenFn)

	var children = emptyList<Widget>()
		protected set

	override fun render(poseStack: PoseStack) = children.forEach { it.render(poseStack) }

	override val contentWidth get() = if (children.isEmpty()) 0 else children.maxOf { it.width }
	override val contentHeight get() = if (children.isEmpty()) 0 else children.maxOf { it.height }

	// This is the only easy way to do this with our current architecture
	fun markChanged() = compileChildren()

	internal fun compileChildren() {
		this.children = WidgetCompiler.compileWidgets(this, childrenFn)
	}
}