package dev.psygamer.wireframe.util.collection

class FreezableLinkedHashMap<K, V> : FreezableMap<K, V> {

	constructor() : super(LinkedHashMap<K, V>())
	constructor(initialCapacity: Int) : super(LinkedHashMap<K, V>(initialCapacity))
	constructor(initialCapacity: Int, loadFactor: Float) : super(LinkedHashMap<K, V>(initialCapacity, loadFactor))
	constructor(map: Map<out K, V>) : super(LinkedHashMap<K, V>(map))
}