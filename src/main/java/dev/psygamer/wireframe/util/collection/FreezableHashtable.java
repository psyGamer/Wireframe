package dev.psygamer.wireframe.util.collection;

import java.util.Map;
import java.util.Hashtable;

public class FreezableHashtable <K, V> extends FreezableMap<K, V> {
	
	public FreezableHashtable() {
		super(new Hashtable<>());
	}
	
	public FreezableHashtable(final int initialCapacity) {
		super(new Hashtable<>(initialCapacity));
	}
	
	public FreezableHashtable(final int initialCapacity, final float loadFactor) {
		super(new Hashtable<>(initialCapacity, loadFactor));
	}
	
	public FreezableHashtable(final Map<? extends K, ? extends V> map) {
		super(new Hashtable<>(map));
	}
	
	@Override
	public FreezableMap<K, V> copy() {
		final FreezableMap<K, V> map = new FreezableHashtable<>(this);
		
		if (isFrozen())
			map.freeze();
		
		return map;
	}
}
