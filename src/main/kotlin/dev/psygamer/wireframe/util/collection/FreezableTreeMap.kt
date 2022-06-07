package dev.psygamer.wireframe.util.collection

import java.util.*

class FreezableTreeMap<K, V> : FreezableMap<K, V> {

	constructor() : super(TreeMap<K, V>())
	constructor(map: Map<out K, V>) : super(TreeMap<K, V>(map))
	constructor(sortedMap: SortedMap<out K, out V>) : super(TreeMap<K, V>(sortedMap))
	constructor(comparator: Comparator<in K>) : super(TreeMap<K, V>(comparator))
}