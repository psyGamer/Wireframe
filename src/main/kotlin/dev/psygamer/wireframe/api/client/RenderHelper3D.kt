package dev.psygamer.wireframe.api.client

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.api.item.ItemStack
import dev.psygamer.wireframe.nativeapi.client.NativeRenderHelper3D
import dev.psygamer.wireframe.util.Color

object RenderHelper3D {

	/**
	 * Renders text with a drop shadow.
	 */
	fun renderText(
		poseStack: PoseStack, text: String, color: Color = Color.WHITE, backgroundColor: Color = Color.TRANSPARENT,
		renderRightToLeft: Boolean = false,
	) = NativeRenderHelper3D.renderText(
		poseStack, text, color, backgroundColor,
		dropShadow = true, centered = false, renderRightToLeft
	)

	/**
	 * Renders centered text with a drop shadow.
	 */
	fun renderCenteredText(
		poseStack: PoseStack, text: String, color: Color = Color.WHITE, backgroundColor: Color = Color.TRANSPARENT,
		renderRightToLeft: Boolean = false,
	) = NativeRenderHelper3D.renderText(
		poseStack, text, color, backgroundColor,
		dropShadow = true, centered = true, renderRightToLeft
	)

	/**
	 * Renders text without a drop shadow.
	 */
	fun renderTextNoShadow(
		poseStack: PoseStack, text: String, color: Color = Color.WHITE, backgroundColor: Color = Color.TRANSPARENT,
		renderRightToLeft: Boolean = false,
	) = NativeRenderHelper3D.renderText(
		poseStack, text, color, backgroundColor,
		dropShadow = false, centered = false, renderRightToLeft
	)

	/**
	 * Renders centered text without a drop shadow.
	 */
	fun renderCenteredTextNoShadow(
		poseStack: PoseStack, text: String, color: Color = Color.WHITE, backgroundColor: Color = Color.TRANSPARENT,
		renderRightToLeft: Boolean = false,
	) = NativeRenderHelper3D.renderText(
		poseStack, text, color, backgroundColor,
		dropShadow = false, centered = true, renderRightToLeft
	)

	/**
	 * Renders a dropped [ItemStack]
	 */
	fun renderItemStack(poseStack: PoseStack, itemStack: ItemStack) =
		NativeRenderHelper3D.renderItemStack(poseStack, itemStack)
}