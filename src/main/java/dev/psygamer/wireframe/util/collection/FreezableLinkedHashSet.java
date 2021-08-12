package dev.psygamer.wireframe.util.collection;

import java.util.Collection;
import java.util.LinkedHashSet;

public class FreezableLinkedHashSet <E> extends FreezableSet<E> {
	
	public FreezableLinkedHashSet() {
		super(new LinkedHashSet<>());
	}
	
	public FreezableLinkedHashSet(final int initialCapacity) {
		super(new LinkedHashSet<>(initialCapacity));
	}
	
	public FreezableLinkedHashSet(final int initialCapacity, final float loadFactor) {
		super(new LinkedHashSet<>(initialCapacity, loadFactor));
	}
	
	public FreezableLinkedHashSet(final Collection<? extends E> set) {
		super(new LinkedHashSet<>(set));
	}
	
	@Override
	public FreezableSet<E> copy() {
		final FreezableSet<E> set = new FreezableLinkedHashSet<>(this);
		
		if (isFrozen())
			set.freeze();
		
		return set;
	}
}
