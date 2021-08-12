package dev.psygamer.wireframe.util.collection;

import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Collection;
import java.util.Comparator;

public class FreezableTreeSet <E> extends FreezableSet<E> {
	
	public FreezableTreeSet() {
		super(new TreeSet<>());
	}
	
	public FreezableTreeSet(final Collection<? extends E> set) {
		super(new TreeSet<>(set));
	}
	
	public FreezableTreeSet(final SortedSet<E> set) {
		super(new TreeSet<>(set));
	}
	
	public FreezableTreeSet(final Comparator<? super E> comparator) {
		super(new TreeSet<>(comparator));
	}
	
	
	@Override
	public FreezableSet<E> copy() {
		final FreezableSet<E> set = new FreezableTreeSet<>(this);
		
		if (isFrozen())
			set.freeze();
		
		return set;
	}
}
