package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.WidgetCompiler
import dev.psygamer.wireframe.gui.modifier.*

abstract class Widget(modifier: ModifierBuilder? = null) {

	internal val modifierSettings = modifier?.build() ?: IDENTITY_MODIFIER_SETTINGS
	internal val parent: Widget? = WidgetCompiler.currentParent
	internal val poseStack: PoseStack = parent?.poseStack?.clone() ?: PoseStack()

	init {
		WidgetCompiler.newWidgetCallback(this)
	}

	abstract fun render()

	open fun renderForeground() {}
	open fun renderBackground() {}

	var renderedWidth: Int = 0
		internal set
	var renderedHeight: Int = 0
		internal set

	var elementWidth: Int = 0
		internal set
	var elementHeight: Int = 0
		internal set

	var childContainerWidth: Int = 0
		internal set
	var childContainerHeight: Int = 0
		internal set

	protected abstract val contentWidth: Int
	protected abstract val contentHeight: Int

	internal val lazyContentWidth = lazy { contentWidth }
	internal val lazyContentHeight = lazy { contentHeight }
}
