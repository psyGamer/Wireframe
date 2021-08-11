package dev.psygamer.wireframe.util.collection;

import dev.psygamer.wireframe.util.IFreezable;

import java.util.HashMap;
import java.util.Map;

public class FreezableHashMap <K, V> extends FreezableMap<K, V> {
	
	public FreezableHashMap() {
		super(new HashMap<>());
	}
	
	public FreezableHashMap(final int initialCapacity) {
		super(new HashMap<>(initialCapacity));
	}
	
	public FreezableHashMap(final int initialCapacity, final float loadFactor) {
		super(new HashMap<>(initialCapacity, loadFactor));
	}
	
	public FreezableHashMap(final Map<? extends K, ? extends V> map) {
		super(new HashMap<>(map));
	}
	
	@Override
	public FreezableMap<K, V> copy() {
		final FreezableMap<K, V> map = new FreezableHashMap<>(this);
		
		if (isFrozen())
			map.freeze();
		
		return map;
	}
}
