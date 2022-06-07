package dev.psygamer.wireframe.api.registry

import dev.psygamer.wireframe.util.collection.*

open class ListRegistry<E> {

	val elements: Set<E> = FreezableHashSet()

	fun register(element: E) {
		(this.elements as FreezableSet).add(element)
	}

	fun freeze() {
		(this.elements as FreezableSet).freeze()
	}
}