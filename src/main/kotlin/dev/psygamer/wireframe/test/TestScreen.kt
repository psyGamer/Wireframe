package dev.psygamer.wireframe.test

import dev.psygamer.wireframe.api.client.screen.Screen
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.util.Color
import dev.psygamer.wireframe.util.Identifier

object TestScreen : Screen() {
	
	override fun render() {
		ScreenRenderHelper.drawQuad(0, 0, 100, 100, Color.DARK_RED)
		ScreenRenderHelper.drawTexturedQuad(100, 0, 200, 100, 0, 0, 16, 16, Identifier(path = "textures/block/stone.png"))
		
		ScreenRenderHelper.drawText("Text", 150, 150, Color.WHITE)
		ScreenRenderHelper.drawCenteredText("Centered Text", 150, 175, Color.BLACK)
		ScreenRenderHelper.drawTextWithShadow("Text with Shadow", 150, 200, Color.LIGHT_GRAY)
		ScreenRenderHelper.drawCenteredTextWithShadow("Centered Text with Shadow", 150, 225, Color(235, 24, 92))
	}
}