package dev.psygamer.wireframe.event.api

import java.lang.reflect.Method

interface IEventBus {
	
	fun register(target: Any)
	fun post(event: Event): Boolean
	
	fun <T : Event> addListener(consumer: (T) -> Unit)
	fun <T : Event> addListener(consumer: (T) -> Unit, priority: EventPriority)
	fun addListener(instance: Any?, method: Method)
	fun addListener(instance: Any?, method: Method, priority: EventPriority)
}