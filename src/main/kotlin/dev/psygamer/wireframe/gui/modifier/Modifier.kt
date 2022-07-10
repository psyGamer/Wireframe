package dev.psygamer.wireframe.gui.modifier

import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.gui.util.Alignment
import dev.psygamer.wireframe.gui.widget.*

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

	if (this.modifierSettings.alignment != Alignment.TOP_LEFT) {
		val parentWidth = this.parent?.lazyContentWidth?.value ?: ScreenRenderHelper.screenWidth
		val parentHeight = this.parent?.lazyContentHeight?.value ?: ScreenRenderHelper.screenHeight

		val centerX = parentWidth / 2 - this.lazyContentWidth.value / 2
		val centerY = parentHeight / 2 - this.lazyContentHeight.value / 2

		val right = parentWidth - this.lazyContentWidth.value
		val bottom = parentHeight - this.lazyContentHeight.value

		when (this.modifierSettings.alignment) {
			Alignment.CENTER -> this.poseStack.translate(centerX, centerY, 0)
			Alignment.LEFT -> this.poseStack.translate(0, centerY, 0)
			Alignment.RIGHT -> this.poseStack.translate(right, centerY, 0)
			Alignment.TOP -> this.poseStack.translate(centerX, 0, 0)
			Alignment.BOTTOM -> this.poseStack.translate(centerX, bottom, 0)
			Alignment.TOP_RIGHT -> this.poseStack.translate(right, 0, 0)
			Alignment.BOTTOM_LEFT -> this.poseStack.translate(0, bottom, 0)
			Alignment.BOTTOM_RIGHT -> this.poseStack.translate(right, bottom, 0)
			else -> {} // Unreachable
		}
	}

	this.poseStack.translate(this.modifierSettings.margin, this.modifierSettings.margin, 0)
	this.elementWidth += 2 * this.modifierSettings.margin
	this.elementHeight += 2 * this.modifierSettings.margin

	if (this !is ParentWidget) return

	this.childrenPoseStacks.forEach { it.translate(this.modifierSettings.padding, this.modifierSettings.padding, 0) }
	this.elementWidth += 2 * this.modifierSettings.padding
	this.elementHeight += 2 * this.modifierSettings.padding
}