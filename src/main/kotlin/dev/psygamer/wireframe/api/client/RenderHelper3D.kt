package dev.psygamer.wireframe.api.client

import dev.psygamer.wireframe.api.item.ItemStack
import dev.psygamer.wireframe.nativeapi.client.NativeRenderHelper3D
import dev.psygamer.wireframe.nativeapi.client.render.context.RenderingContext
import dev.psygamer.wireframe.util.Color

object RenderHelper3D {

	/**
	 * Renders text with a drop shadow.
	 */
	fun renderText(
		context: RenderingContext, text: String, textColor: Color = Color.WHITE, backgroundColor: Color = Color.TRANSPARENT,
		renderRightToLeft: Boolean = false,
	) = NativeRenderHelper3D.renderText(
		context, text, textColor, backgroundColor,
		dropShadow = true, centered = false, renderRightToLeft
	)

	/**
	 * Renders centered text with a drop shadow.
	 */
	fun renderCenteredText(
		context: RenderingContext, text: String, textColor: Color = Color.WHITE, backgroundColor: Color = Color.TRANSPARENT,
		renderRightToLeft: Boolean = false,
	) = NativeRenderHelper3D.renderText(
		context, text, textColor, backgroundColor,
		dropShadow = true, centered = true, renderRightToLeft
	)

	/**
	 * Renders text without a drop shadow.
	 */
	fun renderTextNoShadow(
		context: RenderingContext, text: String, textColor: Color = Color.WHITE, backgroundColor: Color = Color.TRANSPARENT,
		renderRightToLeft: Boolean = false,
	) = NativeRenderHelper3D.renderText(
		context, text, textColor, backgroundColor,
		dropShadow = false, centered = false, renderRightToLeft
	)

	/**
	 * Renders centered text without a drop shadow.
	 */
	fun renderCenteredTextNoShadow(
		context: RenderingContext, text: String, textColor: Color = Color.WHITE, backgroundColor: Color = Color.TRANSPARENT,
		renderRightToLeft: Boolean = false,
	) = NativeRenderHelper3D.renderText(
		context, text, textColor, backgroundColor,
		dropShadow = false, centered = true, renderRightToLeft
	)

	/**
	 * Renders a dropped [ItemStack]
	 */
	fun renderItemStack(context: RenderingContext, itemStack: ItemStack) =
		NativeRenderHelper3D.renderItemStack(context, itemStack)
}