package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.api.client.input.Mouse
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.gui.*
import dev.psygamer.wireframe.util.math.vector.Vector2i

abstract class Widget(modifier: ModifierBuilder? = null) {

	internal val modifierSettings = modifier?.build() ?: IDENTITY_MODIFIER_SETTINGS
	internal val minModifierSettings = minModifier.build()
	internal val parent: Widget? = WidgetCompiler.currentParent
	internal val poseStack: PoseStack = parent?.poseStack?.clone() ?: PoseStack()

	init {
		WidgetCompiler.newWidgetCallback(this)
	}

	abstract fun render()

	open fun renderForeground() {}
	open fun renderBackground() {}

	protected val minModifier: ModifierBuilder
		get() = ModifierBuilder()

	var topLeft = Vector2i.ZERO
		internal set

	var renderedWidth = 0
		internal set
	var renderedHeight = 0
		internal set

	var elementWidth = 0
		internal set
	var elementHeight = 0
		internal set

	var childContainerWidth = 0
		internal set
	var childContainerHeight = 0
		internal set

	val isMouseOver: Boolean
		get() = Mouse.isInArea(this.topLeft.x, this.topLeft.y, this.renderedWidth, this.renderedHeight)
	val isMousePressed: Boolean
		get() = Mouse.isLeftButtonPressed && this.isMouseOver

	abstract val contentWidth: Int
	abstract val contentHeight: Int
}
