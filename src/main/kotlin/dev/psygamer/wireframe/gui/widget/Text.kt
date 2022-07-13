package dev.psygamer.wireframe.gui.widget

import net.minecraft.client.Minecraft
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.gui.ModifierBuilder

class Text(val text: String, modifier: ModifierBuilder? = null) : Widget(modifier) {

	override fun render() = ScreenRenderHelper.drawText(this.poseStack, text)

	override val contentWidth = Minecraft.getInstance().font.width(text)
	override val contentHeight = Minecraft.getInstance().font.lineHeight
}
