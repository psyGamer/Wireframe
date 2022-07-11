package dev.psygamer.wireframe.gui.modifier

import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.gui.util.Alignment
import dev.psygamer.wireframe.gui.widget.*
import dev.psygamer.wireframe.util.math.vector.*

object Modifier {

	fun width(width: Int) = ModifierBuilder().width(width)
	fun height(height: Int) = ModifierBuilder().height(height)
	fun margin(margin: Int) = ModifierBuilder().margin(margin)
	fun padding(padding: Int) = ModifierBuilder().padding(padding)
	fun align(alignment: Alignment) = ModifierBuilder().align(alignment)
}

class ModifierBuilder internal constructor() {

	private var width: Int? = null
	private var height: Int? = null

	private var margin = 0
	private var padding = 0

	private var alignment = Alignment.TOP_LEFT

	fun width(width: Int): ModifierBuilder {
		this.width = width
		return this
	}

	fun height(height: Int): ModifierBuilder {
		this.height = height
		return this
	}

	fun margin(margin: Int): ModifierBuilder {
		this.margin = margin
		return this
	}

	fun padding(padding: Int): ModifierBuilder {
		this.padding = padding
		return this
	}

	fun align(alignment: Alignment): ModifierBuilder {
		this.alignment = alignment
		return this
	}

	internal fun build() = ModifierSettings(
		width, height,
		margin, padding,
		alignment
	)
}

internal val IDENTITY_MODIFIER_SETTINGS = ModifierSettings(null, null, 0, 0, Alignment.TOP_LEFT)

internal data class ModifierSettings(
	val width: Int?, val height: Int?,
	val margin: Int, val padding: Int,
	val alignment: Alignment,
)

internal fun Widget.applyModifierSettings() {
	this.elementWidth = this.modifierSettings.width ?: this.lazyContentWidth.value
	this.elementHeight = this.modifierSettings.height ?: this.lazyContentHeight.value
	this.renderedWidth = this.elementWidth
	this.renderedHeight = this.elementHeight
	this.childContainerWidth = this.elementWidth
	this.childContainerHeight = this.elementHeight

	var xTranslation = 0
	var yTranslation = 0

	fun translate(x: Int, y: Int) {
		this.poseStack.translate(x, y, 0)
		xTranslation += x
		yTranslation += y
	}

	translate(this.modifierSettings.margin, this.modifierSettings.margin)
	this.elementWidth += 2 * this.modifierSettings.margin
	this.elementHeight += 2 * this.modifierSettings.margin

	if (this is ParentWidget) {
		this.childrenPoseStacks.forEach { it.translate(this.modifierSettings.padding, this.modifierSettings.padding, 0) }
		this.elementWidth += 2 * this.modifierSettings.padding
		this.elementHeight += 2 * this.modifierSettings.padding
		this.renderedWidth += 2 * this.modifierSettings.padding
		this.renderedHeight += 2 * this.modifierSettings.padding
	}

	val vector = Vector3i.ZERO.transform(poseStack.mcNative.last().pose())
	this.topLeft = Vector2i(vector.x, vector.y)

	if (this is ParentWidget)
		this.children.forEach { it.applyModifierSettings() }

	if (this.modifierSettings.alignment != Alignment.TOP_LEFT) {
		val parentWidth = this.parent?.childContainerWidth ?: (ScreenRenderHelper.screenWidth / ScreenRenderHelper.guiScale)
		val parentHeight = this.parent?.childContainerHeight ?: (ScreenRenderHelper.screenHeight / ScreenRenderHelper.guiScale)

		val centerX = parentWidth / 2 - this.elementWidth / 2
		val centerY = parentHeight / 2 - this.elementHeight / 2

		val right = parentWidth - this.elementWidth
		val bottom = parentHeight - this.elementHeight

		when (this.modifierSettings.alignment) {
			Alignment.CENTER -> translate(centerX, centerY)
			Alignment.LEFT -> translate(0, centerY)
			Alignment.RIGHT -> translate(right, centerY)
			Alignment.TOP -> translate(centerX, 0)
			Alignment.BOTTOM -> translate(centerX, bottom)
			Alignment.TOP_RIGHT -> translate(right, 0)
			Alignment.BOTTOM_LEFT -> translate(0, bottom)
			Alignment.BOTTOM_RIGHT -> translate(right, bottom)
			else -> {} // Unreachable
		}
	}

	if (this is ParentWidget)
		this.childrenPoseStacks.forEach { it.translate(xTranslation, yTranslation, 0) }
}