package dev.psygamer.wireframe.event

import dev.psygamer.wireframe.event.api.*

class ConsumerEventListener<T : Event>(
	private val consumer: (T) -> Unit,

	override val priority: EventPriority,
	override val eventBus: IEventBus,
) : IEventListener {

	override fun invoke(event: Event) {
		@Suppress("UNCHECKED_CAST") // It's safe
		event as T

		if (!(event.isCancelable && event.isCanceled))
			consumer(event)
	}
}