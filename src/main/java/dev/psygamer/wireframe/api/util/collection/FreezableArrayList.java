package dev.psygamer.wireframe.api.util.collection;

import dev.psygamer.wireframe.api.util.IFreezable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class FreezableArrayList <E> extends ArrayList<E> implements IFreezable {
	
	private boolean frozen = false;
	
	@Override
	public E set(final int index, final E element) {
		throwIfFrozen();
		return super.set(index, element);
	}
	
	@Override
	public boolean add(final E e) {
		throwIfFrozen();
		return super.add(e);
	}
	
	@Override
	public void add(final int index, final E element) {
		throwIfFrozen();
		super.add(index, element);
	}
	
	@Override
	public boolean addAll(final Collection<? extends E> c) {
		throwIfFrozen();
		return super.addAll(c);
	}
	
	@Override
	public boolean addAll(final int index, final Collection<? extends E> c) {
		throwIfFrozen();
		return super.addAll(index, c);
	}
	
	@Override
	public boolean remove(final Object o) {
		throwIfFrozen();
		return super.remove(o);
	}
	
	@Override
	public E remove(final int index) {
		throwIfFrozen();
		return super.remove(index);
	}
	
	@Override
	protected void removeRange(final int fromIndex, final int toIndex) {
		throwIfFrozen();
		super.removeRange(fromIndex, toIndex);
	}
	
	@Override
	public boolean removeAll(final Collection<?> c) {
		throwIfFrozen();
		return super.removeAll(c);
	}
	
	@Override
	public boolean removeIf(final Predicate<? super E> filter) {
		throwIfFrozen();
		return super.removeIf(filter);
	}
	
	@Override
	public void trimToSize() {
		throwIfFrozen();
		super.trimToSize();
	}
	
	@Override
	public void replaceAll(final UnaryOperator<E> operator) {
		throwIfFrozen();
		super.replaceAll(operator);
	}
	
	@Override
	public void sort(final Comparator<? super E> c) {
		throwIfFrozen();
		super.sort(c);
	}
	
	@Override
	public void freeze() {
		this.frozen = true;
	}
	
	@Override
	public boolean isFrozen() {
		return this.frozen;
	}
	
	private void throwIfFrozen() {
		if (this.frozen)
			throw new IFreezable.ObjectFrozenException(this);
	}
}
