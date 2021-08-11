package dev.psygamer.wireframe.util.collection;

import java.util.Collection;
import java.util.LinkedList;

public class FreezableLinkedList <E> extends FreezableList<E> {
	
	public FreezableLinkedList() {
		super(new LinkedList<>());
	}
	
	public FreezableLinkedList(final Collection<? extends E> c) {
		super(new LinkedList<>(c));
	}
	
	@Override
	public FreezableList<E> copy() {
		final FreezableList<E> list = new FreezableLinkedList<>(this);
		
		if (isFrozen())
			list.freeze();
		
		return list;
	}
}
