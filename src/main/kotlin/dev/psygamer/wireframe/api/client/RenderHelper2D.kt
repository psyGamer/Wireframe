package dev.psygamer.wireframe.api.client

import dev.psygamer.wireframe.nativeapi.client.NativeRenderHelper2D
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.Color
import dev.psygamer.wireframe.util.Identifier

object RenderHelper2D {
	
	fun drawQuad(minX: Int, minY: Int, maxX: Int, maxY: Int, color: Color = Color.WHITE) =
		NativeRenderHelper2D.drawRect(minX, minY, maxX, maxY, color)
	
	fun drawTexturedQuad(
		minX: Int, minY: Int, maxX: Int, maxY: Int,
		minU: Int, minV: Int, maxU: Int, maxV: Int,
		texture: Identifier, color: Color = Color.WHITE
	) = NativeRenderHelper2D.drawTexturedRect(minX, minY, maxX, maxY, minU, minV, maxU, maxV, texture, color)
	
	fun drawText(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeRenderHelper2D.drawText(text, x, y, color.mcNative)
	
	fun drawCenteredText(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeRenderHelper2D.drawCenteredText(text, x, y, color.mcNative)
	
	fun drawTextWithShadow(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeRenderHelper2D.drawTextWithShadow(text, x, y, color.mcNative)
	
	fun drawCenteredTextWithShadow(text: String, x: Int, y: Int, color: Color = Color.WHITE) =
		NativeRenderHelper2D.drawCenteredTextWithShadow(text, x, y, color.mcNative)
}