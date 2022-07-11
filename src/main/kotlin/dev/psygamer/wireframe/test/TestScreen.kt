package dev.psygamer.wireframe.test

import dev.psygamer.wireframe.api.client.screen.*
import dev.psygamer.wireframe.util.*

object TestScreen : Screen() {

	override fun render() {
		ScreenRenderHelper.drawQuad(0, 0, 100, 100, Color.DARK_RED)
		ScreenRenderHelper.drawTexturedQuad(
			Identifier(path = "textures/block/stone.png"),
			100, 0, 100, 100,
			0, 0, 16, 16
		)

		ScreenRenderHelper.drawText("Text", 150, 150, Color.WHITE)
		ScreenRenderHelper.drawCenteredText("Centered Text", 150, 175, Color.BLACK)
		ScreenRenderHelper.drawTextWithShadow("Text with Shadow", 150, 200, Color.LIGHT_GRAY)
		ScreenRenderHelper.drawCenteredTextWithShadow("Centered Text with Shadow", 150, 225, Color(235, 24, 92))
	}
}