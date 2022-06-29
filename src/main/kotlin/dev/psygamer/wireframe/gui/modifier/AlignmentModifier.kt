package dev.psygamer.wireframe.gui.modifier

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

class AlignmentModifier(private val alignment: Alignment) : Modifier() {

	override fun apply(context: Context) {
		val centerX = context.parentWidth / 2 - context.widgetWidth / 2
		val centerY = context.parentHeight / 2 - context.widgetHeight / 2

		val left = 0
		val right = context.parentWidth - context.widgetWidth
		val top = 0
		val bottom = context.parentHeight - context.widgetHeight

		when (alignment) {
			Alignment.CENTER -> context.poseStack.translate(centerX, centerY, 0)
			Alignment.LEFT -> context.poseStack.translate(left, centerY, 0)
			Alignment.RIGHT -> context.poseStack.translate(right, centerY, 0)
			Alignment.TOP -> context.poseStack.translate(centerX, top, 0)
			Alignment.BOTTOM -> context.poseStack.translate(centerX, bottom, 0)
			Alignment.TOP_LEFT -> context.poseStack.translate(left, top, 0)
			Alignment.TOP_RIGHT -> context.poseStack.translate(right, top, 0)
			Alignment.BOTTOM_LEFT -> context.poseStack.translate(left, bottom, 0)
			Alignment.BOTTOM_RIGHT -> context.poseStack.translate(right, bottom, 0)
		}
	}
}

fun Modifier.align(alignment: Alignment) = and(AlignmentModifier(alignment))