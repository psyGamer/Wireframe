package dev.psygamer.wireframe.util.collection;

import java.util.*;

public class FreezableHashSet <E> extends FreezableSet<E> {
	
	public FreezableHashSet() {
		super(new HashSet<>());
	}
	
	public FreezableHashSet(final int initialCapacity) {
		super(new HashSet<>(initialCapacity));
	}
	
	public FreezableHashSet(final int initialCapacity, final float loadFactor) {
		super(new HashSet<>(initialCapacity, loadFactor));
	}
	
	public FreezableHashSet(final Collection<? extends E> set) {
		super(new HashSet<>(set));
	}
	
	@Override
	public FreezableSet<E> copy() {
		final FreezableSet<E> set = new FreezableHashSet<>(this);
		
		if (isFrozen())
			set.freeze();
		
		return set;
	}
}
