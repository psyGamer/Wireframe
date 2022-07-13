package dev.psygamer.wireframe.util.helper

import java.util.*

inline fun <T> Iterable<T>.flatten(transform: (T) -> Iterable<T>, shouldStop: (T) -> Boolean): List<T> =
	flattenTo(mutableListOf(), transform, shouldStop)

inline fun <T, R : MutableCollection<in T>> Iterable<T>.flattenTo(destination: R, transform: (T) -> Iterable<T>, shouldStop: (T) -> Boolean): R {
	val waiting = Stack<T>().apply { addAll(this@flattenTo) }
	var current: T

	while (waiting.isNotEmpty()) {
		current = waiting.pop()
		destination.add(current)
		if (shouldStop(current)) continue
		waiting.addAll(transform(current))
	}

	return destination
}