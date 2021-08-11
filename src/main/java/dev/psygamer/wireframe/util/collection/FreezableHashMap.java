package dev.psygamer.wireframe.util.collection;

import dev.psygamer.wireframe.util.IFreezable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FreezableHashMap <K, V> extends HashMap<K, V> implements IFreezable {
	
	private boolean frozen = false;
	
	@Override
	public V put(final K key, final V value) {
		throwIfFrozen();
		return super.put(key, value);
	}
	
	@Override
	public void putAll(final Map<? extends K, ? extends V> m) {
		throwIfFrozen();
		super.putAll(m);
	}
	
	@Override
	public V remove(final Object key) {
		throwIfFrozen();
		return super.remove(key);
	}
	
	@Override
	public void clear() {
		throwIfFrozen();
		super.clear();
	}
	
	@Override
	public V putIfAbsent(final K key, final V value) {
		throwIfFrozen();
		return super.putIfAbsent(key, value);
	}
	
	@Override
	public boolean remove(final Object key, final Object value) {
		throwIfFrozen();
		return super.remove(key, value);
	}
	
	@Override
	public boolean replace(final K key, final V oldValue, final V newValue) {
		throwIfFrozen();
		return super.replace(key, oldValue, newValue);
	}
	
	@Override
	public V replace(final K key, final V value) {
		throwIfFrozen();
		return super.replace(key, value);
	}
	
	@Override
	public V computeIfAbsent(final K key, final Function<? super K, ? extends V> mappingFunction) {
		return super.computeIfAbsent(key, mappingFunction);
	}
	
	@Override
	public V computeIfPresent(final K key, final BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		throwIfFrozen();
		return super.computeIfPresent(key, remappingFunction);
	}
	
	@Override
	public V compute(final K key, final BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		throwIfFrozen();
		return super.compute(key, remappingFunction);
	}
	
	@Override
	public void replaceAll(final BiFunction<? super K, ? super V, ? extends V> function) {
		throwIfFrozen();
		super.replaceAll(function);
	}
	
	@Override
	public void freeze() {
		this.frozen = true;
	}
	
	@Override
	public boolean isFrozen() {
		return this.frozen;
	}
	
	private void throwIfFrozen() {
		if (this.frozen)
			throw new IFreezable.ObjectFrozenException(this);
	}
}
