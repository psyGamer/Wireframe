package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.api.client.screen.ScreenRenderHelper
import dev.psygamer.wireframe.gui.modifier.ModifierBuilder
import dev.psygamer.wireframe.util.Identifier

private val WIDGET_TEXTURE_LOCATION = Identifier("textures/gui/widgets.png")

private const val CORNER_SIZE = 4
private const val HORIZONTAL_EDGE_LENGTH = 192
private const val VERTICAL_EDGE_LENGTH = 12

// UV locations on the texture
private const val TL_CORNER_U = 0
private const val TL_CORNER_V = 46
private const val T_EDGE_U = TL_CORNER_U + CORNER_SIZE
private const val T_EDGE_V = TL_CORNER_V
private const val TR_CORNER_U = T_EDGE_U + HORIZONTAL_EDGE_LENGTH
private const val TR_CORNER_V = T_EDGE_V

private const val L_EDGE_U = TL_CORNER_U
private const val L_EDGE_V = TL_CORNER_V + CORNER_SIZE
private const val M_AREA_U = L_EDGE_U + CORNER_SIZE
private const val M_AREA_V = L_EDGE_V
private const val R_EDGE_U = M_AREA_U + HORIZONTAL_EDGE_LENGTH
private const val R_EDGE_V = M_AREA_V

private const val BL_CORNER_U = L_EDGE_U
private const val BL_CORNER_V = L_EDGE_V + CORNER_SIZE
private const val B_EDGE_U = BL_CORNER_U + CORNER_SIZE
private const val B_EDGE_V = BL_CORNER_V
private const val BR_CORNER_U = B_EDGE_U + HORIZONTAL_EDGE_LENGTH
private const val BR_CORNER_V = B_EDGE_V

class Button(
	modifier: ModifierBuilder? = null, children: () -> Unit,
) : ParentWidget(modifier, children) {

	init {
		minModifier.padding(5)
	}

	override fun renderBackground() {
		// The minimum expected size is 8x8 (CORNER_SIZE * 2)
		if (this.renderedWidth < CORNER_SIZE * 2 || this.renderedHeight < CORNER_SIZE * 2) {
			Wireframe.LOGGER.warn("Button widget was rendered with width($renderedWidth)/height($renderedHeight) < 0")
			return
		}

		ScreenRenderHelper.startRenderingBatch()

		val vOffset = 20

		// Top left corner
		ScreenRenderHelper.drawTexturedQuad(
			poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
			x = 0, y = 0, width = CORNER_SIZE, height = CORNER_SIZE,
			u = TL_CORNER_U, v = TL_CORNER_V + vOffset, uWidth = CORNER_SIZE, vHeight = CORNER_SIZE,
		)
//		// Bottom left corner
		ScreenRenderHelper.drawTexturedQuad(
			poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
			x = 0, y = this.renderedHeight - CORNER_SIZE, width = CORNER_SIZE, height = CORNER_SIZE,
			u = 0, v = L_EDGE_V + VERTICAL_EDGE_LENGTH + vOffset, uWidth = CORNER_SIZE, vHeight = CORNER_SIZE,
		)
		// Top right corner
		ScreenRenderHelper.drawTexturedQuad(
			poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
			x = this.renderedWidth - CORNER_SIZE, y = 0,
			width = CORNER_SIZE, height = CORNER_SIZE,
			u = TR_CORNER_U, v = TR_CORNER_V + vOffset, uWidth = CORNER_SIZE, vHeight = CORNER_SIZE,
		)
		// Bottom right corner
		ScreenRenderHelper.drawTexturedQuad(
			poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
			x = this.renderedWidth - CORNER_SIZE, y = this.renderedHeight - CORNER_SIZE,
			width = CORNER_SIZE, height = CORNER_SIZE,
			u = BR_CORNER_U, v = L_EDGE_V + VERTICAL_EDGE_LENGTH + vOffset, uWidth = CORNER_SIZE, vHeight = CORNER_SIZE,
		)

		// Top/Bottom edge repeated/cropped to fit
		val fullWidthsCount = (this.renderedWidth - CORNER_SIZE * 2).floorDiv(HORIZONTAL_EDGE_LENGTH)
		repeat(fullWidthsCount) { index ->
			// Top edge
			ScreenRenderHelper.drawTexturedQuad(
				poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
				x = CORNER_SIZE + index * HORIZONTAL_EDGE_LENGTH, y = 0,
				width = HORIZONTAL_EDGE_LENGTH, height = CORNER_SIZE,
				u = T_EDGE_U, v = T_EDGE_V + vOffset, uWidth = HORIZONTAL_EDGE_LENGTH, vHeight = CORNER_SIZE
			)
			// Bottom edge
			ScreenRenderHelper.drawTexturedQuad(
				poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
				x = CORNER_SIZE + index * HORIZONTAL_EDGE_LENGTH, y = this.renderedHeight - CORNER_SIZE,
				width = HORIZONTAL_EDGE_LENGTH, height = CORNER_SIZE,
				u = B_EDGE_U, v = L_EDGE_V + VERTICAL_EDGE_LENGTH + vOffset, uWidth = HORIZONTAL_EDGE_LENGTH, vHeight = CORNER_SIZE
			)
		}
		val remainingWidth = (this.renderedWidth - CORNER_SIZE * 2) % HORIZONTAL_EDGE_LENGTH
		// Top edge
		ScreenRenderHelper.drawTexturedQuad(
			poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
			x = CORNER_SIZE + fullWidthsCount * HORIZONTAL_EDGE_LENGTH, y = 0,
			width = remainingWidth, height = CORNER_SIZE,
			u = T_EDGE_U, v = T_EDGE_V + vOffset, uWidth = remainingWidth, vHeight = CORNER_SIZE
		)
		// Bottom edge
		ScreenRenderHelper.drawTexturedQuad(
			poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
			x = CORNER_SIZE + fullWidthsCount * HORIZONTAL_EDGE_LENGTH, y = this.renderedHeight - CORNER_SIZE,
			width = remainingWidth, height = CORNER_SIZE,
			u = B_EDGE_U, v = L_EDGE_V + VERTICAL_EDGE_LENGTH + vOffset, uWidth = remainingWidth, vHeight = CORNER_SIZE
		)

		// Left/Right edge repeated/cropped to fit
		val fullHeightsCount = (this.renderedHeight - CORNER_SIZE * 2).floorDiv(VERTICAL_EDGE_LENGTH)
		repeat(fullHeightsCount) { index ->
			// Left edge
			ScreenRenderHelper.drawTexturedQuad(
				poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
				x = 0, y = CORNER_SIZE + index * VERTICAL_EDGE_LENGTH,
				width = CORNER_SIZE, height = VERTICAL_EDGE_LENGTH,
				u = L_EDGE_U, v = L_EDGE_V + vOffset, uWidth = CORNER_SIZE, vHeight = VERTICAL_EDGE_LENGTH
			)
			// Right edge
			ScreenRenderHelper.drawTexturedQuad(
				poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
				x = this.renderedWidth - CORNER_SIZE, y = CORNER_SIZE + index * VERTICAL_EDGE_LENGTH,
				width = CORNER_SIZE, height = VERTICAL_EDGE_LENGTH,
				u = R_EDGE_U, v = R_EDGE_V + vOffset, uWidth = CORNER_SIZE, vHeight = VERTICAL_EDGE_LENGTH
			)
		}
		val remainingHeight = (this.renderedHeight - CORNER_SIZE * 2) % VERTICAL_EDGE_LENGTH
		// Left edge
		ScreenRenderHelper.drawTexturedQuad(
			poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
			x = 0, y = CORNER_SIZE + fullHeightsCount * VERTICAL_EDGE_LENGTH,
			width = CORNER_SIZE, height = remainingHeight,
			u = L_EDGE_U, v = L_EDGE_V + vOffset, uWidth = CORNER_SIZE, vHeight = remainingHeight
		)
		// Right edge
		ScreenRenderHelper.drawTexturedQuad(
			poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
			x = this.renderedWidth - CORNER_SIZE, y = CORNER_SIZE + fullHeightsCount * VERTICAL_EDGE_LENGTH,
			width = CORNER_SIZE, height = remainingHeight,
			u = R_EDGE_U, v = R_EDGE_V + vOffset, uWidth = CORNER_SIZE, vHeight = remainingHeight
		)

		// Middle area repeated/cropped to fit
		repeat(fullWidthsCount) { xIndex ->
			repeat(fullHeightsCount) { yIndex ->
				ScreenRenderHelper.drawTexturedQuad(
					poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
					x = CORNER_SIZE + xIndex * HORIZONTAL_EDGE_LENGTH, y = CORNER_SIZE + yIndex * VERTICAL_EDGE_LENGTH,
					width = HORIZONTAL_EDGE_LENGTH, height = VERTICAL_EDGE_LENGTH,
					u = M_AREA_U, v = M_AREA_V + vOffset, uWidth = HORIZONTAL_EDGE_LENGTH, vHeight = VERTICAL_EDGE_LENGTH
				)
			}
			ScreenRenderHelper.drawTexturedQuad(
				poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
				x = CORNER_SIZE + xIndex * HORIZONTAL_EDGE_LENGTH, y = CORNER_SIZE + fullHeightsCount * VERTICAL_EDGE_LENGTH,
				width = HORIZONTAL_EDGE_LENGTH, height = remainingHeight,
				u = M_AREA_U, v = M_AREA_V + vOffset, uWidth = HORIZONTAL_EDGE_LENGTH, vHeight = remainingHeight
			)
		}
		repeat(fullHeightsCount) { yIndex ->
			ScreenRenderHelper.drawTexturedQuad(
				poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
				x = CORNER_SIZE + fullWidthsCount * HORIZONTAL_EDGE_LENGTH, y = CORNER_SIZE + yIndex * VERTICAL_EDGE_LENGTH,
				width = remainingWidth, height = VERTICAL_EDGE_LENGTH,
				u = M_AREA_U, v = M_AREA_V + vOffset, uWidth = remainingWidth, vHeight = VERTICAL_EDGE_LENGTH
			)
		}
		ScreenRenderHelper.drawTexturedQuad(
			poseStack = this.poseStack, texture = WIDGET_TEXTURE_LOCATION,
			x = CORNER_SIZE + fullWidthsCount * HORIZONTAL_EDGE_LENGTH, y = CORNER_SIZE + fullHeightsCount * VERTICAL_EDGE_LENGTH,
			width = remainingWidth, height = remainingHeight,
			u = M_AREA_U, v = M_AREA_V + vOffset, uWidth = remainingWidth, vHeight = remainingHeight
		)

		ScreenRenderHelper.endRenderingBatch()
	}
}