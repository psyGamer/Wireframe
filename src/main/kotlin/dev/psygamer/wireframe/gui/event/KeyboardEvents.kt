package dev.psygamer.wireframe.gui.event

data class KeyPressedEvent(val keyCode: Int, val scanCode: Int, val modifiers: Int) : GUIEvent()
data class KeyReleasedEvent(val keyCode: Int, val scanCode: Int, val modifiers: Int) : GUIEvent()