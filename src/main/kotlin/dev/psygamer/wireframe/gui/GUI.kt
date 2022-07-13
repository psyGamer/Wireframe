package dev.psygamer.wireframe.gui

import java.nio.file.Path
import java.util.*
import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.api.client.screen.Screen
import dev.psygamer.wireframe.api.client.screen.ScreenManager.open
import dev.psygamer.wireframe.debug.profiling.profile
import dev.psygamer.wireframe.event.*
import dev.psygamer.wireframe.gui.event.*
import dev.psygamer.wireframe.gui.widget.*

abstract class GUI {

	internal var widgets = emptyList<Widget>()

	abstract fun setup()

	fun open() = GUIScreen(this).open()

	fun render() {
		profile("recompile", this::recompile)

		this.widgets.forEach {
			profile("render") {
				it.renderBackground()
				it.render()
				it.renderForeground()
			}
		}
	}

	internal fun recompile() {
		profile("widgets") { this.widgets = WidgetCompiler.compileWidgets(null, this::setup) }
		profile("modifiers") { this.widgets.onEach(Widget::applyModifierSettings) }
	}
}

private class GUIScreen(private val gui: GUI) : Screen() {

	override fun render() {
		profile("wireframeGUI") {
			runCatching { gui.render() }.onFailure { ex ->
				Wireframe.LOGGER.error("Failed compiling widgets!", ex)
			}
		}
	}

	override fun onMousePressed(mouseX: Double, mouseY: Double, mouseButton: Int) {
		postEventToWidgetAtPosition(MouseClickedEvent(mouseX, mouseY, mouseButton), mouseX, mouseY)
	}

	override fun onMouseMoved(mouseX: Double, mouseY: Double) {
		postEventToWidgetAtPosition(MouseMovedEvent(mouseX, mouseY), mouseX, mouseY)
	}

	override fun onMouseReleased(mouseX: Double, mouseY: Double, mouseButton: Int) {
		postEventToWidgetAtPosition(MouseReleasedEvent(mouseX, mouseY, mouseButton), mouseX, mouseY)
	}

	override fun onMouseDragged(mouseX: Double, mouseY: Double, deltaX: Double, deltaY: Double, mouseButton: Int) {
		postEventToWidgetAtPosition(MouseDraggedEvent(mouseX, mouseY, deltaX, deltaY, mouseButton), mouseX, mouseY)
	}

	override fun onMouseScrolled(mouseX: Double, mouseY: Double, amount: Double) {
		postEventToWidgetAtPosition(MouseScrolledEvent(mouseX, mouseY, amount), mouseX, mouseY)
	}

	override fun onKeyPressed(keyCode: Int, scanCode: Int, modifiers: Int) {
		postEventToAllWidgets(KeyPressedEvent(keyCode, scanCode, modifiers))
	}

	override fun onKeyReleased(keyCode: Int, scanCode: Int, modifiers: Int) {
		postEventToAllWidgets(KeyReleasedEvent(keyCode, scanCode, modifiers))
	}

	override fun onFilesDropped(filePaths: List<Path>) {
		postEventToAllWidgets(FilesDroppedEvent(filePaths))
	}

	private fun postEventToAllWidgets(event: GUIEvent) {
//		Wireframe.CHAT_LOGGER.info("Posting event to every widget: $event")
		val widgetStack = Stack<Widget>().also { it.addAll(gui.widgets) }

		while (widgetStack.isNotEmpty()) {
			val current = widgetStack.pop()
			if (current is ParentWidget) widgetStack.addAll(current.children)
			postEventToTarget(current, event)
		}
	}

	private fun postEventToWidgetAtPosition(event: GUIEvent, x: Double, y: Double) {
		fun lastMatchingWidget(widgets: List<Widget>): Widget? {
			return widgets.findLast {
				x > it.topLeft.x && y > it.topLeft.y &&
				x < it.topLeft.x + it.elementWidth && y < it.topLeft.y + it.elementHeight
			}
		}

		var current = lastMatchingWidget(this.gui.widgets) ?: return
		while (true) {
			if (current !is ParentWidget) break
			current = lastMatchingWidget(current.children) ?: return
		}

		while (!isEventApplicableToTarget(current, event.javaClass)) {
			current = current.parent ?: return
		}
		Wireframe.CHAT_LOGGER.info("Posted event to $current at $x, $y: $event")
		postEventToTarget(current, event)
	}
}