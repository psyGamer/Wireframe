package dev.psygamer.wireframe.util.collection

import com.google.common.collect.ImmutableSet
import dev.psygamer.wireframe.util.IFreezable

abstract class FreezableSet<E> protected constructor(private val set: MutableSet<E>) : MutableSet<E>, IFreezable {

	@Volatile
	final override var frozen = false
		private set

	override val size
		get() = set.size

	override fun add(element: E): Boolean {
		IFreezable.throwIfFrozen(this)
		return set.add(element)
	}

	override fun addAll(elements: Collection<E>): Boolean {
		IFreezable.throwIfFrozen(this)
		return set.addAll(elements)
	}

	override fun remove(element: E): Boolean {
		IFreezable.throwIfFrozen(this)
		return set.remove(element)
	}

	override fun removeAll(elements: Collection<E>): Boolean {
		IFreezable.throwIfFrozen(this)
		return set.removeAll(elements.toSet())
	}

	override fun retainAll(elements: Collection<E>): Boolean {
		IFreezable.throwIfFrozen(this)
		return set.retainAll(elements.toSet())
	}

	override fun clear() {
		IFreezable.throwIfFrozen(this)
		set.clear()
	}

	override fun isEmpty() =
		set.isEmpty()

	override operator fun contains(element: E) =
		set.contains(element)

	override fun containsAll(elements: Collection<E>) =
		set.containsAll(elements)

	override operator fun iterator(): MutableIterator<E> =
		set.iterator()

	override fun freeze() {
		frozen = true
	}

	fun toImmutable(): ImmutableSet<E> {
		return ImmutableSet.copyOf(this)
	}
}