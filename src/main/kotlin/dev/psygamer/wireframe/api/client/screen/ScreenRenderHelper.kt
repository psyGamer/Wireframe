package dev.psygamer.wireframe.api.client.screen

import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.nativeapi.client.screen.NativeScreenRenderHelper
import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.math.vector.Vector2i

object ScreenRenderHelper {

	val screenWidth get() = NativeScreenRenderHelper.screenWidth
	val screenHeight get() = NativeScreenRenderHelper.screenHeight

	val guiScale get() = NativeScreenRenderHelper.guiScale

	fun drawText(text: String, x: Number, y: Number, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat(), y.toFloat(), color, shadow = false)

	fun drawCenteredText(text: String, x: Number, y: Number, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat() - getStringWidth(text) / 2.0f, y.toFloat(), color, shadow = false)

	fun drawTextWithShadow(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat(), y.toFloat(), color, shadow = true)

	fun drawCenteredTextWithShadow(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat() - getStringWidth(text) / 2.0f, y.toFloat(), color, shadow = true)

	fun getStringWidth(text: String) = NativeScreenRenderHelper.getStringWidth(text)
	fun getStringHeight() = NativeScreenRenderHelper.getLineHeight()

	fun drawColoredQuad(poseStack: PoseStack = PoseStack(), xPos: Int = 0, yPos: Int = 0, width: Int, height: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawColoredQuad(poseStack, xPos, yPos, width, height, color)

	fun drawColoredQuad(poseStack: PoseStack = PoseStack(), position: Vector2i = Vector2i.ZERO, width: Int, height: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawColoredQuad(poseStack, position.x, position.y, width, height, color)

	fun drawColoredQuad(poseStack: PoseStack = PoseStack(), xPos: Int = 0, yPos: Int = 0, size: Vector2i, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawColoredQuad(poseStack, xPos, yPos, size.x, size.y, color)

	fun drawColoredQuad(poseStack: PoseStack = PoseStack(), position: Vector2i = Vector2i.ZERO, size: Vector2i, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawColoredQuad(poseStack, position.x, position.y, size.x, size.y, color)

	// The following 16 methods are all variations with two Int parameters / [Vector2i]

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		xPos: Int = 0, yPos: Int = 0, width: Int, height: Int,
		uPos: Int, vPos: Int, uWidth: Int, vHeight: Int,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, xPos, yPos, width, height, uPos, vPos, uWidth, vHeight)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		position: Vector2i = Vector2i.ZERO, width: Int, height: Int,
		uPos: Int, vPos: Int, uWidth: Int, vHeight: Int,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, position.x, position.y, width, height, uPos, vPos, uWidth, vHeight)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		xPos: Int = 0, yPos: Int = 0, size: Vector2i,
		uPos: Int, vPos: Int, uWidth: Int, vHeight: Int,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, xPos, yPos, size.x, size.y, uPos, vPos, uWidth, vHeight)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		position: Vector2i = Vector2i.ZERO, size: Vector2i,
		uPos: Int, vPos: Int, uWidth: Int, vHeight: Int,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, position.x, position.y, size.x, size.y, uPos, vPos, uWidth, vHeight)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		xPos: Int = 0, yPos: Int = 0, width: Int, height: Int,
		uvPosition: Vector2i, uWidth: Int, vHeight: Int,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, xPos, yPos, width, height, uvPosition.x, uvPosition.y, uWidth, vHeight)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		position: Vector2i = Vector2i.ZERO, width: Int, height: Int,
		uvPosition: Vector2i, uWidth: Int, vHeight: Int,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, width, height, uvPosition.x, uvPosition.y, uWidth, vHeight
	)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		xPos: Int = 0, yPos: Int = 0, size: Vector2i,
		uvPosition: Vector2i, uWidth: Int, vHeight: Int,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, xPos, yPos, size.x, size.y, uvPosition.x, uvPosition.y, uWidth, vHeight)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		position: Vector2i = Vector2i.ZERO, size: Vector2i,
		uvPosition: Vector2i, uWidth: Int, vHeight: Int,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, size.x, size.y, uvPosition.x, uvPosition.y, uWidth, vHeight
	)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		xPos: Int = 0, yPos: Int = 0, width: Int, height: Int,
		uPos: Int, vPos: Int, uvSize: Vector2i,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, xPos, yPos, width, height, uPos, vPos, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		position: Vector2i = Vector2i.ZERO, width: Int, height: Int,
		uPos: Int, vPos: Int, uvSize: Vector2i,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, width, height, uPos, vPos, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		xPos: Int = 0, yPos: Int = 0, size: Vector2i,
		uPos: Int, vPos: Int, uvSize: Vector2i,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, xPos, yPos, size.x, size.y, uPos, vPos, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		position: Vector2i = Vector2i.ZERO, size: Vector2i,
		uPos: Int, vPos: Int, uvSize: Vector2i,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, size.x, size.y, uPos, vPos, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		xPos: Int = 0, yPos: Int = 0, width: Int, height: Int,
		uvPosition: Vector2i, uvSize: Vector2i,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, xPos, yPos, width, height, uvPosition.x, uvPosition.y, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		position: Vector2i = Vector2i.ZERO, width: Int, height: Int,
		uvPosition: Vector2i, uvSize: Vector2i,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, width, height, uvPosition.x, uvPosition.y, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		xPos: Int = 0, yPos: Int = 0, size: Vector2i,
		uvPosition: Vector2i, uvSize: Vector2i,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, xPos, yPos, size.x, size.y, uvPosition.x, uvPosition.y, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		poseStack: PoseStack = PoseStack(), texture: Identifier,
		position: Vector2i = Vector2i.ZERO, size: Vector2i,
		uvPosition: Vector2i, uvSize: Vector2i,
		color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, size.x, size.y, uvPosition.x, uvPosition.y, uvSize.x, uvSize.y
	)
}