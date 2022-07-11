package dev.psygamer.wireframe.test

import dev.psygamer.wireframe.api.client.screen.*
import dev.psygamer.wireframe.util.*

object TestScreen : Screen() {

	override fun render() {
		ScreenRenderHelper.drawColoredQuad(width = 100, height = 100, color = Color.DARK_RED)
		ScreenRenderHelper.drawTexturedQuad(
			texture = Identifier(path = "textures/block/stone.png"),
			x = 100, y = 0, width = 100, height = 100,
			u = 0, v = 0, uWidth = 16, vHeight = 16
		)

		ScreenRenderHelper.drawText("Text", 150, 150, Color.WHITE)
		ScreenRenderHelper.drawCenteredText("Centered Text", 150, 175, Color.BLACK)
		ScreenRenderHelper.drawTextWithShadow("Text with Shadow", 150, 200, Color.LIGHT_GRAY)
		ScreenRenderHelper.drawCenteredTextWithShadow("Centered Text with Shadow", 150, 225, Color(235, 24, 92))
	}
}