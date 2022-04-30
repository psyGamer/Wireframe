package dev.psygamer.wireframe.util.collection

import com.google.common.collect.ImmutableMap
import dev.psygamer.wireframe.util.helper.IFreezable

abstract class FreezableMap<K, V> protected constructor(private val map: MutableMap<K, V>) : MutableMap<K, V>, IFreezable {
	
	@Volatile
	final override var frozen = false
		private set
	
	override val size
		get() = map.size
	
	override fun put(key: K, value: V): V? {
		IFreezable.throwIfFrozen(this)
		return map.put(key, value)
	}
	
	override fun putAll(from: Map<out K, V>) {
		IFreezable.throwIfFrozen(this)
		map.putAll(from)
	}
	
	override fun remove(key: K): V? {
		IFreezable.throwIfFrozen(this)
		return map.remove(key)
	}
	
	override fun clear() {
		IFreezable.throwIfFrozen(this)
		map.clear()
	}
	
	override fun isEmpty() =
		map.isEmpty()
	
	override fun containsKey(key: K) =
		map.containsKey(key)
	
	override fun containsValue(value: V) =
		map.containsValue(value)
	
	override operator fun get(key: K) =
		map[key]
	
	override val keys
		get() = map.keys
	
	override val values
		get() = map.values
	
	override val entries
		get() = map.entries
	
	override fun freeze() {
		frozen = true
	}
	
	fun toImmutable(): ImmutableMap<K, V> {
		return ImmutableMap.copyOf(this)
	}
}