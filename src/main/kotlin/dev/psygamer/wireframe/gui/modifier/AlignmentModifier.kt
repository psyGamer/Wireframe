package dev.psygamer.wireframe.gui.modifier

import dev.psygamer.wireframe.api.client.render.PoseStack

enum class Alignment {
	CENTER,
	LEFT,
	RIGHT,
	TOP,
	BOTTOM,
	TOP_LEFT,
	TOP_RIGHT,
	BOTTOM_LEFT,
	BOTTOM_RIGHT,
}

class AlignmentModifier(private val alignment: Alignment) : Modifier {

	override fun apply(poseStack: PoseStack, width: Int, height: Int, parentWidth: Int, parentHeight: Int): Pair<Int, Int> {
		val centerX = parentWidth / 2 - width / 2
		val centerY = parentHeight / 2 - height / 2

		val left = 0
		val right = parentWidth - width
		val top = 0
		val bottom = parentHeight - height

		when (alignment) {
			Alignment.CENTER -> poseStack.translate(centerX, centerY, 0)
			Alignment.LEFT -> poseStack.translate(left, centerY, 0)
			Alignment.RIGHT -> poseStack.translate(right, centerY, 0)
			Alignment.TOP -> poseStack.translate(centerX, top, 0)
			Alignment.BOTTOM -> poseStack.translate(centerX, bottom, 0)
			Alignment.TOP_LEFT -> poseStack.translate(left, top, 0)
			Alignment.TOP_RIGHT -> poseStack.translate(right, top, 0)
			Alignment.BOTTOM_LEFT -> poseStack.translate(left, bottom, 0)
			Alignment.BOTTOM_RIGHT -> poseStack.translate(right, bottom, 0)
		}

		return width to height
	}
}

fun Modifier.Companion.align(alignment: Alignment) = AlignmentModifier(alignment)