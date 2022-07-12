package dev.psygamer.wireframe.util.collection

class FreezableHashSet<E> : FreezableSet<E> {

	constructor() : super(HashSet<E>())
	constructor(initialCapacity: Int) : super(HashSet<E>(initialCapacity))
	constructor(initialCapacity: Int, loadFactor: Float) : super(HashSet<E>(initialCapacity, loadFactor))
	constructor(set: Set<E>) : super(HashSet<E>(set))
}