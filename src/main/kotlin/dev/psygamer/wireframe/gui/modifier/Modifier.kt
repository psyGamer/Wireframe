package dev.psygamer.wireframe.gui.modifier

import dev.psygamer.wireframe.gui.BoxModelStack

abstract class Modifier {

	companion object : Modifier() {

		private val modifierStack = mutableListOf<Modifier>()

		override fun apply(boxModel: BoxModelStack.Entry) = Unit
	}

	internal var parent: Modifier = Modifier
		private set

	abstract fun apply(boxModel: BoxModelStack.Entry)

	fun and(other: Modifier): Modifier {
		other.parent = this
		return other
	}
}

fun Modifier.applyAll(boxModel: BoxModelStack.Entry) {
	var current = this
	while (current != Modifier) {
		current.apply(boxModel)
		current = current.parent
	}
}