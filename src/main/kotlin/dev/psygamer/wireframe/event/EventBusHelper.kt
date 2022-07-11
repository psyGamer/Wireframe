package dev.psygamer.wireframe.event

import java.lang.reflect.*
import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.event.api.*

fun isEventApplicableToTarget(target: Any, eventClass: Class<out Event>): Boolean {
	return getEventMethodsFromTarget(target)
		.any { it.parameterTypes.size == 1 && it.parameterTypes[0] == eventClass }
}

fun postEventToTarget(target: Any, event: Event): Boolean {
	val isClass = target.javaClass == Class::class.java
	val eventListeners = mutableListOf<IEventListener>()

	getEventMethodsFromTarget(target)
		.filter { it.parameterTypes.size == 1 && it.parameterTypes[0] == event.javaClass }
		.forEach { method ->
			val priority = method.getAnnotation(EventSubscriber::class.java).priority
			val listener = MethodEventListener(if (isClass) null else target, method, priority, Wireframe.EVENT_BUS) // The eventbus won't be used.
			eventListeners.add(listener)
		}

	eventListeners
		.sortedWith(Comparator.comparing(IEventListener::priority))
		.forEach { it(event) }

	return event.isCancelable && event.isCanceled
}

internal fun getEventMethodsFromTarget(target: Any): List<Method> {
	val isClass = target.javaClass == Class::class.java
	return (if (isClass) (target as Class<*>).methods else target.javaClass.methods)
		.filter { if (isClass) Modifier.isStatic(it.modifiers) else !Modifier.isStatic(it.modifiers) }
		.filter { it.isAnnotationPresent(EventSubscriber::class.java) }
}