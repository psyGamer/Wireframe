package dev.psygamer.wireframe.event

import java.lang.reflect.Method
import dev.psygamer.wireframe.event.api.*

class MethodEventListener(
	private val instance: Any?,
	private val method: Method,

	override val priority: EventPriority,
	override val eventBus: IEventBus,
) : IEventListener {

	override fun invoke(event: Event) {
		this.method(this.instance, event)
	}
}