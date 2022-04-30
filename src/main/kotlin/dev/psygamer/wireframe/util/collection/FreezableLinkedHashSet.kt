package dev.psygamer.wireframe.util.collection

class FreezableLinkedHashSet<E> : FreezableSet<E> {
	
	constructor() : super(LinkedHashSet<E>())
	constructor(initialCapacity: Int) : super(LinkedHashSet<E>(initialCapacity))
	constructor(initialCapacity: Int, loadFactor: Float) : super(LinkedHashSet<E>(initialCapacity, loadFactor))
	constructor(set: Set<E>) : super(LinkedHashSet<E>(set))
}