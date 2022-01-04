package dev.psygamer.wireframe.event

import dev.psygamer.wireframe.event.api.Event
import dev.psygamer.wireframe.event.api.EventPriority
import dev.psygamer.wireframe.event.api.IEventBus
import java.lang.reflect.Method

class MethodEventListener(
	private val instance: Any?,
	private val method: Method,
	
	override val priority: EventPriority,
	override val eventBus: IEventBus
) : IEventListener {
	
	override fun invoke(event: Event) {
		this.method(this.instance, event)
	}
}