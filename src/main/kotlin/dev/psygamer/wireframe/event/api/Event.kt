package dev.psygamer.wireframe.event.api

abstract class Event {

	var isCanceled = false
		set(canceled) {
			if (!isCancelable) {
				throw UnsupportedOperationException(
					"Attempted to call Event#setCanceled() on a non-cancelable event of type: ${this.javaClass.canonicalName}!"
				)
			}

			field = canceled
		}

	abstract val isCancelable: Boolean
}