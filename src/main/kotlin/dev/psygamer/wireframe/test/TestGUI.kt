package dev.psygamer.wireframe.test

import dev.psygamer.wireframe.gui.*
import dev.psygamer.wireframe.gui.util.Alignment
import dev.psygamer.wireframe.gui.widget.*

object TestGUI : GUI() {

	override fun setup() {
		Text(modifier = Modifier.align(Alignment.TOP), text = "T")
		Text(modifier = Modifier.align(Alignment.BOTTOM), text = "B")
		Text(modifier = Modifier.align(Alignment.RIGHT), text = "R")
		Text(modifier = Modifier.align(Alignment.LEFT), text = "L")

		Text(modifier = Modifier.align(Alignment.TOP_RIGHT), text = "TR")
		Text(modifier = Modifier.align(Alignment.TOP_LEFT), text = "TL")
		Text(modifier = Modifier.align(Alignment.BOTTOM_RIGHT), text = "BR")
		Text(modifier = Modifier.align(Alignment.BOTTOM_LEFT), text = "BL")

		Button(
			modifier = Modifier.align(Alignment.CENTER)
		) {
			Text(text = "Hello, World")
		}
	}
}