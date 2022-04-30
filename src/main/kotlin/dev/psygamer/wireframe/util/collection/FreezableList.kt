package dev.psygamer.wireframe.util.collection

import com.google.common.collect.ImmutableList
import java.util.function.UnaryOperator
import dev.psygamer.wireframe.util.IFreezable

abstract class FreezableList<E> protected constructor(private val list: MutableList<E>) : MutableList<E>, IFreezable {
	
	@Volatile
	final override var frozen = false
		private set
	
	override val size
		get() = list.size
	
	override fun set(index: Int, element: E): E {
		IFreezable.throwIfFrozen(this)
		return list.set(index, element)
	}
	
	override fun add(element: E): Boolean {
		IFreezable.throwIfFrozen(this)
		return list.add(element)
	}
	
	override fun add(index: Int, element: E) {
		IFreezable.throwIfFrozen(this)
		list.add(index, element)
	}
	
	override fun addAll(elements: Collection<E>): Boolean {
		IFreezable.throwIfFrozen(this)
		return list.addAll(elements)
	}
	
	override fun addAll(index: Int, elements: Collection<E>): Boolean {
		IFreezable.throwIfFrozen(this)
		return list.addAll(index, elements)
	}
	
	override fun remove(element: E): Boolean {
		IFreezable.throwIfFrozen(this)
		return list.remove(element)
	}
	
	override fun removeAt(index: Int): E {
		IFreezable.throwIfFrozen(this)
		return list.removeAt(index)
	}
	
	override fun removeAll(elements: Collection<E>): Boolean {
		IFreezable.throwIfFrozen(this)
		return list.removeAll(elements)
	}
	
	override fun retainAll(elements: Collection<E>): Boolean {
		IFreezable.throwIfFrozen(this)
		return list.retainAll(elements)
	}
	
	override fun replaceAll(operator: UnaryOperator<E>) {
		IFreezable.throwIfFrozen(this)
		list.replaceAll(operator)
	}
	
	override fun clear() {
		IFreezable.throwIfFrozen(this)
		list.clear()
	}
	
	override fun isEmpty() =
		list.isEmpty()
	
	override operator fun contains(element: E) =
		list.contains(element)
	
	override operator fun get(index: Int) =
		list[index]
	
	override fun containsAll(elements: Collection<E>) =
		list.containsAll(elements)
	
	override fun indexOf(element: E) =
		list.indexOf(element)
	
	override fun lastIndexOf(element: E) =
		list.lastIndexOf(element)
	
	override operator fun iterator(): MutableIterator<E> =
		list.iterator()
	
	override fun listIterator() =
		list.listIterator()
	
	override fun listIterator(index: Int) =
		list.listIterator(index)
	
	override fun subList(fromIndex: Int, toIndex: Int) =
		list.subList(fromIndex, toIndex)
	
	override fun freeze() {
		frozen = true
	}
	
	fun toImmutable(): ImmutableList<E> {
		return ImmutableList.copyOf(this)
	}
}