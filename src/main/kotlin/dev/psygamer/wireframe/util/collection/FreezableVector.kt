package dev.psygamer.wireframe.util.collection

import java.util.*

class FreezableVector<E> : FreezableList<E> {
	
	constructor() : super(Vector<E>())
	constructor(capacity: Int) : super(Vector<E>(capacity))
	constructor(capacity: Int, capacityIncrement: Int) : super(Vector<E>(capacity, capacityIncrement))
	constructor(elements: Collection<E>) : super(Vector<E>(elements))
}