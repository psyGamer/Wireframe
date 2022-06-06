package dev.psygamer.wireframe.nativeapi.client.screen

import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.AbstractGui
import org.lwjgl.opengl.GL11.*
import javax.imageio.ImageIO
import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.api.client.OpenGL
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.debug.debug
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.helper.using

object NativeScreenRenderHelper {

	internal val IDENTITY_MATRIX = MatrixStack()

	val screenWidth: Int = Minecraft.getInstance().screen!!.width
	val screenHeight: Int = Minecraft.getInstance().screen!!.height

	fun getStringWidth(text: String): Int {
		return font.width(text)
	}

	private val textureSizeCache: MutableMap<Identifier, Pair<Int, Int>> = mutableMapOf()

	private val font = Minecraft.getInstance().font

	fun drawRect(minX: Int, minY: Int, maxX: Int, maxY: Int, color: Color) {
		debug {
			if (maxX < minX)
				Wireframe.LOGGER.warn("Calling ScreenRenderHelper.drawRect with maxY($maxY) > minY($minY)")

			if (maxY < minY)
				Wireframe.LOGGER.warn("Calling ScreenRenderHelper.drawRect with maxY($maxY) > minY($minY)")

			if (maxX < minX || maxY < minY) {
				val stackTrace = Throwable().stackTrace
				val stackTraceLength = 5

				for (i in 0 until stackTraceLength)
					Wireframe.LOGGER.warn("\t${stackTrace[i]}")
			}
		}

		AbstractGui.fill(IDENTITY_MATRIX, minX, minY, maxX, maxY, color.mcNative)
	}

	fun drawRect(poseStack: PoseStack, width: Int, height: Int, color: Color) {
		debug {
			if (width < 0)
				Wireframe.LOGGER.warn("Calling ScreenRenderHelper.drawRect with width($width) < 0")
			if (height < 0)
				Wireframe.LOGGER.warn("Calling ScreenRenderHelper.drawRect with height($height) < 0")

			if (width < 0 || height < 0) {
				val stackTrace = Throwable().stackTrace
				val stackTraceLength = 5

				for (i in 0 until stackTraceLength)
					Wireframe.LOGGER.warn("\t${stackTrace[i]}")
			}
		}

		AbstractGui.fill(poseStack.mcNative, 0, 0, width, height, color.mcNative)
	}

	fun drawTexturedRect(
		minX: Int, minY: Int, maxX: Int, maxY: Int,
		minU: Int, minV: Int, maxU: Int, maxV: Int,
		texture: Identifier, color: Color,
	) {
		debug {
			if (maxX < minX)
				Wireframe.LOGGER.warn("Calling ScreenRenderHelper.drawTexturedRect with maxY($maxY) > minY($minY)")

			if (maxY < minY)
				Wireframe.LOGGER.warn("Calling ScreenRenderHelper.drawTexturedRect with maxY($maxY) > minY($minY)")

			if (maxU < minU)
				Wireframe.LOGGER.warn("Calling ScreenRenderHelper.drawTexturedRect with maxU($maxU) > minY($minU)")

			if (maxV < minV)
				Wireframe.LOGGER.warn("Calling ScreenRenderHelper.drawTexturedRect with maxV($maxV) > minV($minV)")

			if (maxX < minX || maxY < minY || maxU < minU || maxV < minV) {
				val stackTrace = Throwable().stackTrace
				val stackTraceLength = 5

				for (i in 0 until stackTraceLength)
					Wireframe.LOGGER.warn("\t$stackTrace[i]")
			}
		}

		using(
			OpenGL.color(color),
			OpenGL.texture(texture),
			OpenGL.enable(GL_BLEND)
		) {
			fun getTextureSize(): Pair<Int, Int> {
				val input = Minecraft.getInstance().resourceManager.getResource(texture.mcNative).inputStream
				val image = ImageIO.read(input)

				val pair = image.width to image.height

				textureSizeCache[texture] = pair
				return pair
			}

			val (textureWidth: Int, textureHeight: Int) =
				if (textureSizeCache.containsKey(texture))
					textureSizeCache[texture]!!
				else
					getTextureSize()

			AbstractGui.blit(
				MatrixStack(), minX, minY, maxX - minX, maxY - minY,
				minU.toFloat(), minV.toFloat(), maxU - minU, maxV - minV, textureWidth, textureHeight
			)
		}
	}

	fun drawText(text: String, x: Int, y: Int, color: Int) {
		drawText(text, x.toFloat(), y.toFloat(), 1.0f, 1.0f, color, false)
	}

	fun drawCenteredText(text: String, x: Int, y: Int, color: Int) {
		drawText(text, x - getStringWidth(text).toFloat() / 2, y.toFloat(), 1.0f, 1.0f, color, false)
	}

	fun drawTextWithShadow(text: String, x: Int, y: Int, color: Int) {
		drawText(text, x.toFloat(), y.toFloat(), 1.0f, 1.0f, color, true)
	}

	fun drawCenteredTextWithShadow(text: String, x: Int, y: Int, color: Int) {
		drawText(text, x - getStringWidth(text).toFloat() / 2, y.toFloat(), 1.0f, 1.0f, color, true)
	}

	private fun drawText(text: String, x: Float, y: Float, scaleX: Float, scaleY: Float, color: Int, shadow: Boolean) {
		using(
			OpenGL.enable(GL_TEXTURE_2D),
			OpenGL.enable(GL_BLEND)
		) {
			val matrix = MatrixStack()
			matrix.scale(scaleX, scaleY, 1.0f)

			if (shadow)
				font.drawShadow(matrix, text, x, y, color)
			else
				font.draw(matrix, text, x, y, color)
		}
	}
}