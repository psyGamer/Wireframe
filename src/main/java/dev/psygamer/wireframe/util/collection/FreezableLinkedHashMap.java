package dev.psygamer.wireframe.util.collection;

import java.util.LinkedHashMap;
import java.util.Map;

public class FreezableLinkedHashMap <K, V> extends FreezableMap<K, V> {
	
	public FreezableLinkedHashMap() {
		super(new LinkedHashMap<>());
	}
	
	public FreezableLinkedHashMap(final int initialCapacity) {
		super(new LinkedHashMap<>(initialCapacity));
	}
	
	public FreezableLinkedHashMap(final int initialCapacity, final float loadFactor) {
		super(new LinkedHashMap<>(initialCapacity, loadFactor));
	}
	
	public FreezableLinkedHashMap(final Map<? extends K, ? extends V> map) {
		super(new LinkedHashMap<>(map));
	}
	
	@Override
	public FreezableMap<K, V> copy() {
		final FreezableMap<K, V> map = new FreezableLinkedHashMap<>(this);
		
		if (isFrozen())
			map.freeze();
		
		return map;
	}
}
