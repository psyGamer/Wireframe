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

	fun drawColoredQuad(poseStack: PoseStack, xPos: Int, yPos: Int, width: Int, height: Int, color: Color) {
		AbstractGui.fill(poseStack.mcNative, xPos, yPos, xPos + width, yPos + height, color.mcNative)
	}

	fun drawTexturedQuad(
		poseStack: PoseStack, texture: Identifier, color: Color,
		xPos: Int, yPos: Int, width: Int, height: Int,
		uPos: Int, vPos: Int, uWidth: Int, vHeight: Int,
	) {
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