package dev.psygamer.wireframe.event

import dev.psygamer.wireframe.event.api.Event
import dev.psygamer.wireframe.event.api.EventPriority
import dev.psygamer.wireframe.event.api.IEventBus

interface IEventListener {
	
	operator fun invoke(event: Event)
	
	val priority: EventPriority
	val eventBus: IEventBus
}