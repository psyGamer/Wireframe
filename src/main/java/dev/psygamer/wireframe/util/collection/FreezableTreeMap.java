package dev.psygamer.wireframe.util.collection;

import java.util.*;

public class FreezableTreeMap <K, V> extends FreezableMap<K, V> {
	
	public FreezableTreeMap() {
		super(new TreeMap<>());
	}
	
	public FreezableTreeMap(final Map<? extends K, ? extends V> map) {
		super(new TreeMap<>(map));
	}
	
	public FreezableTreeMap(final SortedMap<? extends K, ? extends V> sortedMap) {
		super(new TreeMap<>((SortedMap<K, ? extends V>) sortedMap));
	}
	
	public FreezableTreeMap(final Comparator<? super K> comparator) {
		super(new TreeMap<>(comparator));
	}
	
	@Override
	public FreezableMap<K, V> copy() {
		final FreezableMap<K, V> map = new FreezableTreeMap<>(this);
		
		if (isFrozen())
			map.freeze();
		
		return map;
	}
}
