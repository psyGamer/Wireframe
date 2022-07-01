package dev.psygamer.wireframe.gui.modifier

class HeightModifier(private val height: Int) : Modifier() {

	override fun apply(context: Context) {
		context.widgetHeight = height
	}
}

fun Modifier.height(height: Int) = and(HeightModifier(height))