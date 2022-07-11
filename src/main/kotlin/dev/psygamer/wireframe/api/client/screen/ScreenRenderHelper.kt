package dev.psygamer.wireframe.api.client.screen

import java.io.Closeable
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.nativeapi.client.screen.NativeScreenRenderHelper
import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.math.vector.Vector2i

object ScreenRenderHelper {

	val screenWidth get() = NativeScreenRenderHelper.screenWidth
	val screenHeight get() = NativeScreenRenderHelper.screenHeight

	val guiScale get() = NativeScreenRenderHelper.guiScale

	fun drawText(text: String, x: Number = 0, y: Number = 0, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat(), y.toFloat(), color, shadow = false)

	fun drawText(poseStack: PoseStack = PoseStack(), text: String, x: Number = 0, y: Number = 0, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(poseStack, text, x.toFloat(), y.toFloat(), color, shadow = false)

	fun drawCenteredText(text: String, x: Number = 0, y: Number = 0, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat() - getStringWidth(text) / 2.0f, y.toFloat(), color, shadow = false)

	fun drawCenteredText(poseStack: PoseStack = PoseStack(), text: String, x: Number = 0, y: Number = 0, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(poseStack, text, x.toFloat() - getStringWidth(text) / 2.0f, y.toFloat(), color, shadow = false)

	fun drawTextWithShadow(text: String, x: Number = 0, y: Number = 0, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat(), y.toFloat(), color, shadow = true)

	fun drawTextWithShadow(poseStack: PoseStack = PoseStack(), text: String, x: Number = 0, y: Number = 0, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(poseStack, text, x.toFloat(), y.toFloat(), color, shadow = true)

	fun drawCenteredTextWithShadow(text: String, x: Number = 0, y: Number = 0, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(PoseStack(), text, x.toFloat() - getStringWidth(text) / 2.0f, y.toFloat(), color, shadow = true)

	fun drawCenteredTextWithShadow(poseStack: PoseStack = PoseStack(), text: String, x: Number = 0, y: Number = 0, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawText(poseStack, text, x.toFloat() - getStringWidth(text) / 2.0f, y.toFloat(), color, shadow = true)

	fun getStringWidth(text: String) = NativeScreenRenderHelper.getStringWidth(text)
	fun getStringHeight() = NativeScreenRenderHelper.getLineHeight()

	fun drawColoredQuad(poseStack: PoseStack = PoseStack(), x: Int = 0, y: Int = 0, width: Int, height: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawColoredQuad(poseStack, x, y, width, height, color)

	fun drawColoredQuad(poseStack: PoseStack = PoseStack(), position: Vector2i = Vector2i.ZERO, width: Int, height: Int, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawColoredQuad(poseStack, position.x, position.y, width, height, color)

	fun drawColoredQuad(poseStack: PoseStack = PoseStack(), x: Int = 0, y: Int = 0, size: Vector2i, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawColoredQuad(poseStack, x, y, size.x, size.y, color)

	fun drawColoredQuad(poseStack: PoseStack = PoseStack(), position: Vector2i = Vector2i.ZERO, size: Vector2i, color: Color = Color.WHITE) =
		NativeScreenRenderHelper.drawColoredQuad(poseStack, position.x, position.y, size.x, size.y, color)

	fun startRenderingBatch(): Closeable {
		NativeScreenRenderHelper.startBatch()
		return Closeable { NativeScreenRenderHelper.endBatch() }
	}

	fun endRenderingBatch() = NativeScreenRenderHelper.endBatch()

	// The following 16 methods are all variations with two Int parameters / [Vector2i]

	fun drawTexturedQuad(
		x: Int = 0, y: Int = 0, width: Int, height: Int,
		u: Int, v: Int, uWidth: Int, vHeight: Int,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, x, y, width, height, u, v, uWidth, vHeight)

	fun drawTexturedQuad(
		position: Vector2i = Vector2i.ZERO, width: Int, height: Int,
		u: Int, v: Int, uWidth: Int, vHeight: Int,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, position.x, position.y, width, height, u, v, uWidth, vHeight)

	fun drawTexturedQuad(
		x: Int = 0, y: Int = 0, size: Vector2i,
		u: Int, v: Int, uWidth: Int, vHeight: Int,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, x, y, size.x, size.y, u, v, uWidth, vHeight)

	fun drawTexturedQuad(
		position: Vector2i = Vector2i.ZERO, size: Vector2i,
		u: Int, v: Int, uWidth: Int, vHeight: Int,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, position.x, position.y, size.x, size.y, u, v, uWidth, vHeight)

	fun drawTexturedQuad(
		x: Int = 0, y: Int = 0, width: Int, height: Int,
		uvPosition: Vector2i, uWidth: Int, vHeight: Int,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, x, y, width, height, uvPosition.x, uvPosition.y, uWidth, vHeight)

	fun drawTexturedQuad(
		position: Vector2i = Vector2i.ZERO, width: Int, height: Int,
		uvPosition: Vector2i, uWidth: Int, vHeight: Int,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, width, height, uvPosition.x, uvPosition.y, uWidth, vHeight
	)

	fun drawTexturedQuad(
		x: Int = 0, y: Int = 0, size: Vector2i,
		uvPosition: Vector2i, uWidth: Int, vHeight: Int,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(poseStack, texture, color, x, y, size.x, size.y, uvPosition.x, uvPosition.y, uWidth, vHeight)

	fun drawTexturedQuad(
		position: Vector2i = Vector2i.ZERO, size: Vector2i,
		uvPosition: Vector2i, uWidth: Int, vHeight: Int,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, size.x, size.y, uvPosition.x, uvPosition.y, uWidth, vHeight
	)

	fun drawTexturedQuad(
		x: Int = 0, y: Int = 0, width: Int, height: Int,
		u: Int, v: Int, uvSize: Vector2i,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, x, y, width, height, u, v, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		position: Vector2i = Vector2i.ZERO, width: Int, height: Int,
		u: Int, v: Int, uvSize: Vector2i,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, width, height, u, v, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		x: Int = 0, y: Int = 0, size: Vector2i,
		u: Int, v: Int, uvSize: Vector2i,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, x, y, size.x, size.y, u, v, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		position: Vector2i = Vector2i.ZERO, size: Vector2i,
		u: Int, v: Int, uvSize: Vector2i,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, size.x, size.y, u, v, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		x: Int = 0, y: Int = 0, width: Int, height: Int,
		uvPosition: Vector2i, uvSize: Vector2i,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, x, y, width, height, uvPosition.x, uvPosition.y, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		position: Vector2i = Vector2i.ZERO, width: Int, height: Int,
		uvPosition: Vector2i, uvSize: Vector2i,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, width, height, uvPosition.x, uvPosition.y, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		x: Int = 0, y: Int = 0, size: Vector2i,
		uvPosition: Vector2i, uvSize: Vector2i,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, x, y, size.x, size.y, uvPosition.x, uvPosition.y, uvSize.x, uvSize.y
	)

	fun drawTexturedQuad(
		position: Vector2i = Vector2i.ZERO, size: Vector2i,
		uvPosition: Vector2i, uvSize: Vector2i,
		texture: Identifier, poseStack: PoseStack = PoseStack(), color: Color = Color.WHITE,
	) = NativeScreenRenderHelper.drawTexturedQuad(
		poseStack, texture, color, position.x, position.y, size.x, size.y, uvPosition.x, uvPosition.y, uvSize.x, uvSize.y
	)
}