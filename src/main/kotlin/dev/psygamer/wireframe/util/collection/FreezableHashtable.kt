package dev.psygamer.wireframe.util.collection

import java.util.*

class FreezableHashtable<K, V> : FreezableMap<K, V> {

	constructor() : super(Hashtable<K, V>())
	constructor(initialCapacity: Int) : super(Hashtable<K, V>(initialCapacity))
	constructor(initialCapacity: Int, loadFactor: Float) : super(Hashtable<K, V>(initialCapacity, loadFactor))
	constructor(map: Map<out K, V>) : super(Hashtable<K, V>(map))
}