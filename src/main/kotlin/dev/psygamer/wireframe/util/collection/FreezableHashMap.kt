package dev.psygamer.wireframe.util.collection

class FreezableHashMap<K, V> : FreezableMap<K, V> {
	
	constructor() : super(HashMap<K, V>())
	constructor(initialCapacity: Int) : super(HashMap<K, V>(initialCapacity))
	constructor(initialCapacity: Int, loadFactor: Float) : super(HashMap<K, V>(initialCapacity, loadFactor))
	constructor(map: Map<out K, V>) : super(HashMap<K, V>(map))
}