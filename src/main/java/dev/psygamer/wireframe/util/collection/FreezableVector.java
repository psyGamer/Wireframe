package dev.psygamer.wireframe.util.collection;

import java.util.Collection;
import java.util.Vector;

public class FreezableVector <E> extends FreezableList<E> {
	
	public FreezableVector() {
		super(new Vector<>());
	}
	
	public FreezableVector(final int capacity) {
		super(new Vector<>(capacity));
	}
	
	public FreezableVector(final int capacity, final int capacityIncrement) {
		super(new Vector<>(capacity, capacityIncrement));
	}
	
	public FreezableVector(final Collection<? extends E> c) {
		super(new Vector<>(c));
	}
	
	@Override
	public FreezableList<E> copy() {
		final FreezableList<E> list = new FreezableVector<>(this);
		
		if (isFrozen())
			list.freeze();
		
		return list;
	}
}
