package dev.psygamer.wireframe.nativeapi.client.screen

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.AbstractGui
import org.lwjgl.opengl.GL11.*
import javax.imageio.ImageIO
import dev.psygamer.wireframe.api.client.OpenGL
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.helper.using

object NativeScreenRenderHelper {

	val screenWidth get() = Minecraft.getInstance().window.width
	val screenHeight get() = Minecraft.getInstance().window.height

	val guiScale get() = Minecraft.getInstance().options.guiScale

	private val font = Minecraft.getInstance().font
	private val textureSizeCache: MutableMap<Identifier, Pair<Int, Int>> = mutableMapOf()

	private var batchRendering = false
	private val renderBatchStack = mutableMapOf<Int, MutableList<RenderEntry>>()

	fun startBatch() {
		renderBatchStack.clear()
		batchRendering = true
	}

	fun endBatch() {
		batchRendering = false
		using(OpenGL.enable(GL_BLEND)) {
			renderBatchStack.values.forEach { entries ->
				val (textureWidth, textureHeight) = getTextureSize(entries[0].texture)
				using(
					OpenGL.color(entries[0].color),
					OpenGL.texture(entries[0].texture)
				) {
					entries.forEach {
						AbstractGui.blit(
							it.poseStack.mcNative, it.xPos, it.yPos, it.width, it.height,
							it.uPos.toFloat(), it.vPos.toFloat(), it.uWidth, it.vHeight, textureWidth, textureHeight
						)
					}
				}
			}
		}
	}

	fun drawColoredQuad(poseStack: PoseStack, xPos: Int, yPos: Int, width: Int, height: Int, color: Color) {
		AbstractGui.fill(poseStack.mcNative, xPos, yPos, xPos + width, yPos + height, color.mcNative)
	}

	fun drawTexturedQuad(
		poseStack: PoseStack, texture: Identifier, color: Color,
		xPos: Int, yPos: Int, width: Int, height: Int,
		uPos: Int, vPos: Int, uWidth: Int, vHeight: Int,
	) {
		if (batchRendering) {
			val hashCode = 31 * texture.hashCode() + color.hashCode()
			val entry = RenderEntry(poseStack, texture, color, xPos, yPos, width, height, uPos, vPos, uWidth, vHeight)

			if (!renderBatchStack.containsKey(hashCode))
				renderBatchStack[hashCode] = mutableListOf(entry)
			else
				renderBatchStack[hashCode]!!.add(entry)
			return
		}

		using(
			OpenGL.color(color),
			OpenGL.texture(texture),
			OpenGL.enable(GL_BLEND)
		) {
			val (textureWidth, textureHeight) = getTextureSize(texture)
			AbstractGui.blit(
				poseStack.mcNative, xPos, yPos, width, height,
				uPos.toFloat(), vPos.toFloat(), uWidth, vHeight, textureWidth, textureHeight
			)
		}
	}

	fun drawText(poseStack: PoseStack, text: String, x: Float, y: Float, color: Color, shadow: Boolean) {
		using(
			OpenGL.enable(GL_TEXTURE_2D),
			OpenGL.enable(GL_BLEND)
		) {
			if (shadow)
				font.drawShadow(poseStack.mcNative, text, x, y, color.mcNative)
			else
				font.draw(poseStack.mcNative, text, x, y, color.mcNative)
		}
	}

	fun getStringWidth(text: String) = font.width(text)
	fun getLineHeight() = font.lineHeight

	private fun getTextureSize(texture: Identifier): Pair<Int, Int> {
		if (textureSizeCache.containsKey(texture))
			return textureSizeCache[texture]!!

		val inputStream = Minecraft.getInstance().resourceManager.getResource(texture.mcNative).inputStream
		val image = ImageIO.read(inputStream)

		val size = image.width to image.height
		textureSizeCache[texture] = size
		return size
	}
}

private data class RenderEntry(
	val poseStack: PoseStack,
	val texture: Identifier,
	val color: Color,
	val xPos: Int, val yPos: Int, val width: Int, val height: Int,
	val uPos: Int, val vPos: Int, val uWidth: Int, val vHeight: Int,
)