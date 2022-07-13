package dev.psygamer.wireframe.util.types

class Observable<T : Any> {

	interface Subscriber<T : Any> {

		fun onValueChanged(oldValue: T, newValue: T)

	}

	var value: T
		set(newValue) {
			subscribers.forEach { it.onValueChanged(this.value, newValue) }
			field = newValue
		}

	val subscribers = mutableListOf<Subscriber<in T>>()

	constructor(value: T) {
		this.value = value
	}

	fun subscribe(subscriber: Subscriber<in T>) {
		subscribers.add(subscriber)
	}

	fun unsubscribe(subscriber: Subscriber<in T>) {
		subscribers.remove(subscriber)
	}
}
