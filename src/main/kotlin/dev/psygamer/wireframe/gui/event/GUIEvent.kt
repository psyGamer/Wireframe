package dev.psygamer.wireframe.gui.event

import dev.psygamer.wireframe.event.api.Event

abstract class GUIEvent : Event() {

	override val isCancelable = false
}