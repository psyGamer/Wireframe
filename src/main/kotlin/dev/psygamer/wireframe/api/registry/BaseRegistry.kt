package dev.psygamer.wireframe.api.registry

import dev.psygamer.wireframe.util.collection.FreezableHashSet
import dev.psygamer.wireframe.util.collection.FreezableSet

open class BaseRegistry<T> {
	
	val elements: Set<T> = FreezableHashSet()
	
	fun register(element: T) {
		(this.elements as FreezableSet).add(element)
	}
	
	fun freeze() {
		(this.elements as FreezableSet).freeze()
	}
}