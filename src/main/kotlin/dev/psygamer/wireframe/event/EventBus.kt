package dev.psygamer.wireframe.event

import io.netty.util.internal.ConcurrentSet
import net.jodah.typetools.TypeResolver
import java.lang.reflect.*
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Consumer
import dev.psygamer.wireframe.event.api.*

class EventBus @JvmOverloads constructor(
	private val eventMarkerClass: Class<*> = Event::class.java,
) : IEventBus {

	private val registeredListeners = ConcurrentSet<Any>()
	private val listeners = ConcurrentHashMap<Class<out Event>, MutableList<IEventListener>>()

	override fun register(target: Any) {
		if (registeredListeners.contains(target))
			return

		registeredListeners.add(target)
		registerTarget(target)
	}

	override fun post(event: Event): Boolean {
		val eventListeners: List<IEventListener> = listeners[event.javaClass]!!

		eventListeners
			.filter { it.eventBus == this }
			.sortedWith(Comparator.comparing(IEventListener::priority))
			.forEach { it(event) }

		return event.isCancelable && event.isCanceled
	}

	override fun <T : Event> addListener(consumer: (T) -> Unit, priority: EventPriority) {
		val eventClass = getEventClass(consumer)
		require(eventClass.isAssignableFrom(eventMarkerClass)) { "Event must inherit: $eventMarkerClass!" }

		addListener(eventClass, ConsumerEventListener(consumer, priority, this))
	}

	override fun addListener(instance: Any?, method: Method, priority: EventPriority) {
		require(method.parameterTypes.size == 1) { "Event method must have exactly 1 argument!" }
		require(method.parameterTypes[0].isAssignableFrom(Event::class.java)) { "Event method's argument must inherit Event!" }
		require(!(instance != null && !Modifier.isStatic(method.modifiers))) { "Class event listeners can only register static methods" }
		require(!(instance == null && Modifier.isStatic(method.modifiers))) { "Instance event listeners can only register member methods" }

		@Suppress("UNCHECKED_CAST") // It's checked...
		val eventClass = method.parameterTypes[0] as Class<out Event>
		require(eventClass.isAssignableFrom(eventMarkerClass)) { "Event must inherit: $eventMarkerClass!" }

		addListener(eventClass, MethodEventListener(instance, method, priority, this))
	}

	private fun addListener(eventClass: Class<out Event>, listener: IEventListener) {
		if (listeners.containsKey(eventClass) && listener in listeners[eventClass]!!)
			return

		if (!listeners.containsKey(eventClass))
			listeners[eventClass] = ArrayList()
		listeners[eventClass]!!.add(listener)
	}

	private fun registerTarget(target: Any) {
		getEventMethodsFromTarget(target).forEach {
			addListener(if (target.javaClass == Class::class.java) null else target, it, it.getAnnotation(EventSubscriber::class.java).priority)
		}
	}

	private fun <T : Event> getEventClass(consumer: (T) -> Unit): Class<T> {
		val eventClass = TypeResolver.resolveRawArgument(Consumer::class.java, consumer.javaClass) as Class<*>
		check(eventClass != TypeResolver.Unknown::class.java) { "Failed to resolve consumer event type: $consumer!" }
		require(eventClass.isAssignableFrom(Event::class.java)) { "Event method's argument must inherit Event!" }

		@Suppress("UNCHECKED_CAST") // It's checked...
		return eventClass as Class<T>
	}
}