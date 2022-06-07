package dev.psygamer.wireframe.event

import dev.psygamer.wireframe.event.api.*

interface IEventListener {

	operator fun invoke(event: Event)

	val priority: EventPriority
	val eventBus: IEventBus
}