package dev.psygamer.wireframe.gui.modifier

class MarginModifier(private val margin: Int) : Modifier() {

	override fun apply(context: Context) {
		context.poseStack.translate(margin, margin, 0)
		context.widgetWidth += 2 * margin
		context.widgetHeight += 2 * margin
	}
}

fun Modifier.margin(size: Int) = and(MarginModifier(size))