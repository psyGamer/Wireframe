package dev.psygamer.wireframe.api.client.screen

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.nativeapi.client.screen.NativeScreenRenderHelper
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.*

object ScreenRenderHelper {

	val screenWidth = NativeScreenRenderHelper.screenWidth
	val screenHeight = NativeScreenRenderHelper.screenHeight

	fun drawQuad(minX: Int, minY: Int, maxX: Int, maxY: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawRect(minX, minY, maxX, maxY, color)

	fun drawQuad(poseStack: PoseStack, width: Int, height: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawRect(poseStack, width, height, color)

	fun drawTexturedQuad(
		minX: Int, minY: Int, maxX: Int, maxY: Int,
		minU: Int, minV: Int, maxU: Int, maxV: Int,
		texture: Identifier, color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedRect(minX, minY, maxX, maxY, minU, minV, maxU, maxV, texture, color)

	fun drawText(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(text, x, y, color.mcNative)

	fun drawCenteredText(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawCenteredText(text, x, y, color.mcNative)

	fun drawTextWithShadow(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawTextWithShadow(text, x, y, color.mcNative)

	fun drawCenteredTextWithShadow(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawCenteredTextWithShadow(text, x, y, color.mcNative)
}