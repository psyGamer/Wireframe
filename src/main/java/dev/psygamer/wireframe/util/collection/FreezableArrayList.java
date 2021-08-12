package dev.psygamer.wireframe.util.collection;

import java.util.Collection;
import java.util.ArrayList;

public class FreezableArrayList <E> extends FreezableList<E> {
	
	public FreezableArrayList() {
		super(new ArrayList<>());
	}
	
	public FreezableArrayList(final int initialCapacity) {
		super(new ArrayList<>(initialCapacity));
	}
	
	public FreezableArrayList(final Collection<? extends E> c) {
		super(new ArrayList<>(c));
	}
	
	@Override
	public FreezableList<E> copy() {
		final FreezableList<E> list = new FreezableArrayList<>(this);
		
		if (isFrozen())
			list.freeze();
		
		return list;
	}
}
