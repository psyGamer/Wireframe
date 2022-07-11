package dev.psygamer.wireframe.event.api

open class ConsumableEvent : Event() {

	fun markConsumed() {
		this.consumed = true
	}

	var consumed = false
		private set

	final override var isCanceled: Boolean
		get() = false
		set(_) {}

	final override val isCancelable: Boolean = false
}