package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.gui.widget.ParentWidget
import dev.psygamer.wireframe.util.helper.flatten

internal class WidgetObserver(private val gui: GUI) {

	private var prevWidgetHashes = computeWidgetHashes()

	internal fun checkForChanges() {
		val stagedForRecompilation = mutableListOf<ParentWidget>()

		prevWidgetHashes.forEach { (hash, widget) ->
			if (widget.hashCode() != hash) stagedForRecompilation.add(widget)
		}

		stagedForRecompilation.forEach { it.compileChildren() }
		prevWidgetHashes = computeWidgetHashes()
	}

	internal fun computeWidgetHashes() =
		gui.widgets.filterIsInstance<ParentWidget>()
			.flatten({ it.children.filterIsInstance<ParentWidget>() }, { it.children.isNotEmpty() })
			.associateBy { it.hashCode() }
}