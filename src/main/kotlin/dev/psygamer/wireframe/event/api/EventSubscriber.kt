package dev.psygamer.wireframe.event.api

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventSubscriber(val priority: EventPriority = EventPriority.NORMAL)