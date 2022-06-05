package dev.psygamer.wireframe.test

import dev.psygamer.wireframe.gui.GUI
import dev.psygamer.wireframe.gui.widget.*

object TestGUI : GUI() {

	override fun setup() {
		Button(padding = 2) {
			Text("Abc")
		}
	}
}