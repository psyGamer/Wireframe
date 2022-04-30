package dev.psygamer.wireframe.util.collection

import java.util.*

class FreezableLinkedList<E> : FreezableList<E> {
	
	constructor() : super(LinkedList<E>())
	constructor(elements: Collection<E>) : super(LinkedList<E>(elements))
}