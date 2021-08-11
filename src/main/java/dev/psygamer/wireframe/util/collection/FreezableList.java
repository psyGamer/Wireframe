package dev.psygamer.wireframe.util.collection;

import dev.psygamer.wireframe.util.ICloneable;
import dev.psygamer.wireframe.util.IFreezable;

import com.google.common.collect.ImmutableList;

import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.ListIterator;
import java.util.function.UnaryOperator;

public abstract class FreezableList <E> implements List<E>, IFreezable, ICloneable<FreezableList<E>> {
	
	private final List<E> list;
	private volatile boolean frozen = false;
	
	protected FreezableList(final List<E> list) {
		this.list = list;
	}
	
	@Override
	public E set(final int index, final E element) {
		IFreezable.throwIfFrozen(this);
		return this.list.set(index, element);
	}
	
	@Override
	public void freeze() {
		this.frozen = true;
	}
	
	@Override
	public boolean isFrozen() {
		return this.frozen;
	}
	
	public ImmutableList<E> toImmutable() {
		return ImmutableList.copyOf(this);
	}
	
	@Override
	public int size() {
		return this.list.size();
	}
	
	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	
	@Override
	public boolean contains(final Object o) {
		return this.list.contains(o);
	}
	
	@Override
	public Iterator<E> iterator() {
		return this.list.iterator();
	}
	
	@Override
	public Object[] toArray() {
		return this.list.toArray();
	}
	
	@Override
	@SuppressWarnings("SuspiciousToArrayCall")
	public <T> T[] toArray(final T[] a) {
		return this.list.toArray(a);
	}
	
	@Override
	public boolean add(final E e) {
		IFreezable.throwIfFrozen(this);
		return this.list.add(e);
	}
	
	@Override
	public void add(final int index, final E element) {
		IFreezable.throwIfFrozen(this);
		this.list.add(index, element);
	}
	
	@Override
	public boolean addAll(final Collection<? extends E> c) {
		IFreezable.throwIfFrozen(this);
		return this.list.addAll(c);
	}
	
	@Override
	public boolean addAll(final int index, final Collection<? extends E> c) {
		IFreezable.throwIfFrozen(this);
		return this.list.addAll(index, c);
	}
	
	@Override
	public boolean remove(final Object o) {
		IFreezable.throwIfFrozen(this);
		return this.list.remove(o);
	}
	
	@Override
	public boolean containsAll(final Collection<?> c) {
		return false;
	}
	
	@Override
	public E remove(final int index) {
		IFreezable.throwIfFrozen(this);
		return this.list.remove(index);
	}
	
	@Override
	public int indexOf(final Object o) {
		return this.list.indexOf(o);
	}
	
	@Override
	public int lastIndexOf(final Object o) {
		return this.list.lastIndexOf(o);
	}
	
	@Override
	public ListIterator<E> listIterator() {
		return this.list.listIterator();
	}
	
	@Override
	public ListIterator<E> listIterator(final int index) {
		return this.list.listIterator(index);
	}
	
	@Override
	public List<E> subList(final int fromIndex, final int toIndex) {
		return this.list.subList(fromIndex, toIndex);
	}
	
	@Override
	public boolean removeAll(final Collection<?> c) {
		IFreezable.throwIfFrozen(this);
		return this.list.removeAll(c);
	}
	
	@Override
	public boolean retainAll(final Collection<?> c) {
		return false;
	}
	
	@Override
	public void replaceAll(final UnaryOperator<E> operator) {
		IFreezable.throwIfFrozen(this);
		this.list.replaceAll(operator);
	}
	
	@Override
	public void clear() {
		IFreezable.throwIfFrozen(this);
		this.list.clear();
	}
	
	@Override
	public E get(final int index) {
		return this.list.get(index);
	}
}
