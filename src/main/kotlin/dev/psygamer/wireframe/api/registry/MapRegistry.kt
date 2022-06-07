package dev.psygamer.wireframe.api.registry

import dev.psygamer.wireframe.util.collection.*

open class MapRegistry<K, V> {

	val elements: Map<K, V> = FreezableHashMap()

	fun getValue(key: K): V? {
		return elements[key]
	}

	fun freeze() {
		(elements as FreezableMap).freeze()
	}
}