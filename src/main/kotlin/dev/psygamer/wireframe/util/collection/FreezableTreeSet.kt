package dev.psygamer.wireframe.util.collection

import java.util.*

class FreezableTreeSet<E> : FreezableSet<E> {
	
	constructor() : super(TreeSet<E>())
	constructor(set: Collection<E>) : super(TreeSet<E>(set))
	constructor(set: SortedSet<E>) : super(TreeSet<E>(set))
	constructor(comparator: Comparator<in E>) : super(TreeSet<E>(comparator))
}