package dev.psygamer.wireframe.api.client.screen

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.nativeapi.client.screen.NativeScreenRenderHelper
import dev.psygamer.wireframe.util.*

object ScreenRenderHelper {

	val screenWidth get() = NativeScreenRenderHelper.screenWidth
	val screenHeight get() = NativeScreenRenderHelper.screenHeight

	val guiScale get() = NativeScreenRenderHelper.guiScale

	fun drawQuad(xPos: Int, yPos: Int, width: Int, height: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawColoredQuad(PoseStack(), xPos, yPos, width, height, color)

	fun drawQuad(poseStack: PoseStack, width: Int, height: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawColoredQuad(poseStack, 0, 0, width, height, color)

	fun drawTexturedQuad(
		texture: Identifier,
		xPos: Int, yPos: Int, width: Int, height: Int,
		uPos: Int, vPos: Int, uWidth: Int, vHeight: Int,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(PoseStack(), texture, color, xPos, yPos, width, height, uPos, vPos, uWidth, vHeight)

	fun drawTexturedQuad(
		poseStack: PoseStack, texture: Identifier,
		width: Int, height: Int,
		uPos: Int, vPos: Int, uWidth: Int, vHeight: Int,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, 0, 0, width, height, uPos, vPos, uWidth, vHeight)

	fun drawText(text: String, x: Number, y: Number, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat(), y.toFloat(), color, shadow = false)

	fun drawCenteredText(text: String, x: Number, y: Number, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat() - getStringWidth(text) / 2.0f, y.toFloat(), color, shadow = false)

	fun drawTextWithShadow(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat(), y.toFloat(), color, shadow = true)

	fun drawCenteredTextWithShadow(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat() - getStringWidth(text) / 2.0f, y.toFloat(), color, shadow = true)

	fun getStringWidth(text: String) = NativeScreenRenderHelper.getStringWidth(text)
}