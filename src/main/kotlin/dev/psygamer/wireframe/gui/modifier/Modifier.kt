package dev.psygamer.wireframe.gui.modifier

import java.util.*
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.gui.widget.Widget

abstract class Modifier {

	companion object : Modifier() {

		override fun apply(context: Context) = Unit
	}

	data class Context(
		val parentWidth: Int, val parentHeight: Int,
		var widgetWidth: Int, var widgetHeight: Int,
		val poseStack: PoseStack,
	)

	internal var parent: Modifier = Modifier
		private set

	abstract fun apply(context: Context)

	fun and(other: Modifier): Modifier {
		other.parent = this
		return other
	}
}

internal fun Widget.applyModifiers() {
	val context = Modifier.Context(
		this.parent?.width ?: (ScreenRenderHelper.screenWidth / ScreenRenderHelper.guiScale),
		this.parent?.height ?: (ScreenRenderHelper.screenHeight / ScreenRenderHelper.guiScale),
		this.width, this.height,
		this.poseStack
	)

	var current = this.modifiers ?: return
	while (current != Modifier) {
		current.apply(context)
		current = current.parent
	}

	this.modifiedWidth = Optional.of(context.widgetWidth)
	this.modifiedHeight = Optional.of(context.widgetHeight)
}