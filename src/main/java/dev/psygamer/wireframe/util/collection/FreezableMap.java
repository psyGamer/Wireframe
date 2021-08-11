package dev.psygamer.wireframe.util.collection;

import com.google.common.collect.ImmutableMap;
import dev.psygamer.wireframe.util.ICloneable;
import dev.psygamer.wireframe.util.IFreezable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class FreezableMap <K, V> implements Map<K, V>, IFreezable, ICloneable<FreezableMap<K, V>> {
	
	private final Map<K, V> map;
	private volatile boolean frozen = false;
	
	protected FreezableMap(final Map<K, V> map) {
		this.map = map;
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
	
	@Override
	public abstract FreezableMap<K, V> copy();
	
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
		throwIfFrozen();
		return this.map.put(key, value);
	}
	
	@Override
	public V remove(final Object key) {
		throwIfFrozen();
		return this.map.remove(key);
	}
	
	@Override
	public void putAll(final Map<? extends K, ? extends V> m) {
		throwIfFrozen();
		this.map.putAll(m);
	}
	
	@Override
	public void clear() {
		throwIfFrozen();
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
	
	private void throwIfFrozen() {
		if (this.frozen)
			throw new IFreezable.ObjectFrozenException(this);
	}
}
