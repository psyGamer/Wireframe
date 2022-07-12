package dev.psygamer.wireframe.util.collection

class FreezableArrayList<E> : FreezableList<E> {

	constructor() : super(ArrayList<E>())
	constructor(initialCapacity: Int) : super(ArrayList<E>(initialCapacity))
	constructor(elements: Collection<E>) : super(ArrayList<E>(elements))
}