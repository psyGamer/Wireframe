package dev.psygamer.wireframe.gui.event

data class MouseClickedEvent(val mouseX: Double, val mouseY: Double, val mouseButton: Int) : GUIEvent()
data class MouseReleasedEvent(val mouseX: Double, val mouseY: Double, val mouseButton: Int) : GUIEvent()

data class MouseMovedEvent(val mouseX: Double, val mouseY: Double) : GUIEvent()
data class MouseDraggedEvent(val mouseX: Double, val mouseY: Double, val deltaX: Double, val deltaY: Double, val mouseButton: Int) : GUIEvent()

data class MouseScrolledEvent(val mouseX: Double, val mouseY: Double, val distance: Double) : GUIEvent()