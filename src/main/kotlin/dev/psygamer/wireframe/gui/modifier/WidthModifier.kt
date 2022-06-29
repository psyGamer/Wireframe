package dev.psygamer.wireframe.gui.modifier

class WidthModifier(private val width: Int) : Modifier() {

	override fun apply(context: Context) {
		context.widgetWidth = width
	}
}

fun Modifier.width(width: Int) = and(WidthModifier(width))