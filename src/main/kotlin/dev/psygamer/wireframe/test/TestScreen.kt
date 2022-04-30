package dev.psygamer.wireframe.test

import dev.psygamer.wireframe.api.client.RenderHelper2D
import dev.psygamer.wireframe.api.client.screen.Screen
import dev.psygamer.wireframe.util.Color
import dev.psygamer.wireframe.util.Identifier

object TestScreen : Screen() {
	
	override fun render() {
		RenderHelper2D.drawQuad(0, 0, 100, 100, Color.DARK_RED)
		RenderHelper2D.drawTexturedQuad(100, 0, 200, 100, 0, 0, 16, 16, Identifier(path = "textures/block/stone.png"))
		
		RenderHelper2D.drawText("Text", 150, 150, Color.WHITE)
		RenderHelper2D.drawCenteredText("Centered Text", 150, 175, Color.BLACK)
		RenderHelper2D.drawTextWithShadow("Text with Shadow", 150, 200, Color.LIGHT_GRAY)
		RenderHelper2D.drawCenteredTextWithShadow("Centered Text with Shadow", 150, 225, Color(235, 24, 92))
	}
}