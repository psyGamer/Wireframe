package dev.psygamer.wireframe.util.collection;

import dev.psygamer.wireframe.util.helper.ICloneable;
import dev.psygamer.wireframe.util.helper.IFreezable;

import com.google.common.collect.ImmutableSet;

import java.util.Set;
import java.util.Iterator;
import java.util.Collection;

public abstract class FreezableSet <E> implements Set<E>, IFreezable, ICloneable<FreezableSet<E>> {
	
	private final Set<E> set;
	private volatile boolean frozen = false;
	
	protected FreezableSet(final Set<E> set) {
		this.set = set;
	}
	
	@Override
	public int size() {
		return this.set.size();
	}
	
	@Override
	public boolean isEmpty() {
		return this.set.isEmpty();
	}
	
	@Override
	public boolean contains(final Object o) {
		return this.set.contains(o);
	}
	
	@Override
	public Iterator<E> iterator() {
		return this.set.iterator();
	}
	
	@Override
	public Object[] toArray() {
		return this.set.toArray();
	}
	
	@Override
	@SuppressWarnings("SuspiciousToArrayCall")
	public <T> T[] toArray(final T[] a) {
		return this.set.toArray(a);
	}
	
	@Override
	public boolean add(final E e) {
		IFreezable.throwIfFrozen(this);
		return this.set.add(e);
	}
	
	@Override
	public boolean remove(final Object o) {
		IFreezable.throwIfFrozen(this);
		return this.set.remove(o);
	}
	
	@Override
	public boolean containsAll(final Collection<?> c) {
		return this.set.containsAll(c);
	}
	
	@Override
	public boolean addAll(final Collection<? extends E> c) {
		IFreezable.throwIfFrozen(this);
		return this.set.addAll(c);
	}
	
	@Override
	public boolean retainAll(final Collection<?> c) {
		IFreezable.throwIfFrozen(this);
		return this.set.retainAll(c);
	}
	
	@Override
	public boolean removeAll(final Collection<?> c) {
		IFreezable.throwIfFrozen(this);
		return this.set.removeAll(c);
	}
	
	@Override
	public void clear() {
		IFreezable.throwIfFrozen(this);
		this.set.clear();
	}
	
	@Override
	public void freeze() {
		this.frozen = true;
	}
	
	@Override
	public boolean isFrozen() {
		return this.frozen;
	}
	
	public ImmutableSet<E> toImmutable() {
		return ImmutableSet.copyOf(this);
	}
}
