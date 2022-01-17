package dev.psygamer.wireframe.api.registry

import dev.psygamer.wireframe.util.collection.FreezableHashSet
import dev.psygamer.wireframe.util.collection.FreezableSet

open class ListRegistry<E> {
	
	val elements: Set<E> = FreezableHashSet()
	
	fun register(element: E) {
		(this.elements as FreezableSet).add(element)
	}
	
	fun freeze() {
		(this.elements as FreezableSet).freeze()
	}
}