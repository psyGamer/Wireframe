package dev.psygamer.wireframe.gui.widget

import dev.psygamer.wireframe.gui.modifier.Modifier

abstract class CanvasWidget(modifier: Modifier? = null, childrenFn: () -> Unit = { }) : Widget(modifier, childrenFn) {

	abstract val contentWidth: Int
	abstract val contentHeight: Int
}