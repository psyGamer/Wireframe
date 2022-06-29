package dev.psygamer.wireframe.util.types

class Reactive<T> {

	interface Subscriber<T> {

		fun onValueChanged(oldValue: T, newValue: T)
	}

	var value: T
		set(newValue) {
			subscribers.forEach { it.onValueChanged(this.value, newValue) }
			field = newValue
		}

	val subscribers = mutableListOf<Subscriber<T>>()

	constructor(value: T) {
		this.value = value
	}

	fun subscribe(subscriber: Subscriber<T>) {
		subscribers.add(subscriber)
	}

	fun unsubscribe(subscriber: Subscriber<T>) {
		subscribers.remove(subscriber)
	}
}

val <T> T.ref: Reactive<T>
	get() = Reactive(this)