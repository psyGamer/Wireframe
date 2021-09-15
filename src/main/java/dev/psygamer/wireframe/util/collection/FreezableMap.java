package dev.psygamer.wireframe.util.collection;

import dev.psygamer.wireframe.util.helper.ICloneable;
import dev.psygamer.wireframe.util.helper.IFreezable;

import com.google.common.collect.ImmutableMap;

import java.util.Set;
import java.util.Map;
import java.util.Collection;

public abstract class FreezableMap <K, V> implements Map<K, V>, IFreezable, ICloneable<FreezableMap<K, V>> {
	
	private final Map<K, V> map;
	private volatile boolean frozen = false;
	
	protected FreezableMap(final Map<K, V> map) {
		this.map = map;
	}
	
	@Override
	public int size() {
		return this.map.size();
	}
	
	@Override
	public boolean isEmpty() {
		return this.map.isEmpty();
	}
	
	@Override
	public boolean containsKey(final Object key) {
		return this.map.containsKey(key);
	}
	
	@Override
	public boolean containsValue(final Object value) {
		return this.map.containsValue(value);
	}
	
	@Override
	public V get(final Object key) {
		return this.map.get(key);
	}
	
	@Override
	public V put(final K key, final V value) {
		IFreezable.throwIfFrozen(this);
		return this.map.put(key, value);
	}
	
	@Override
	public V remove(final Object key) {
		IFreezable.throwIfFrozen(this);
		return this.map.remove(key);
	}
	
	@Override
	public void putAll(final Map<? extends K, ? extends V> m) {
		IFreezable.throwIfFrozen(this);
		this.map.putAll(m);
	}
	
	@Override
	public void clear() {
		IFreezable.throwIfFrozen(this);
		this.map.clear();
	}
	
	@Override
	public Set<K> keySet() {
		return this.map.keySet();
	}
	
	@Override
	public Collection<V> values() {
		return this.map.values();
	}
	
	@Override
	public Set<Entry<K, V>> entrySet() {
		return this.map.entrySet();
	}
	
	@Override
	public void freeze() {
		this.frozen = true;
	}
	
	@Override
	public boolean isFrozen() {
		return this.frozen;
	}
	
	public ImmutableMap<K, V> toImmutable() {
		return ImmutableMap.copyOf(this);
	}
}
