package dev.psygamer.wireframe.block.state;

import dev.psygamer.wireframe.block.state.property.BlockProperty;

import dev.psygamer.wireframe.util.ICloneable;
import dev.psygamer.wireframe.util.IFreezable;
import dev.psygamer.wireframe.util.collection.FreezableSet;
import dev.psygamer.wireframe.util.collection.FreezableLinkedHashSet;

import com.google.common.collect.ImmutableSet;

public class BlockState implements IFreezable, ICloneable<BlockState> {
	
	private final FreezableSet<BlockProperty.ValuePair<?>> values;
	
	public BlockState() {
		this.values = new FreezableLinkedHashSet<>();
	}
	
	public BlockState(final BlockState baseContainer) {
		this.values = baseContainer.values;
	}
	
	public <T> BlockState withValue(final BlockProperty<T> blockProperty, final T value) {
		return copy().setProperty(blockProperty, value);
	}
	
	public <T> BlockState withObjectValue(final BlockProperty<?> blockProperty, final Object value) {
		return withValue((BlockProperty<? super T>) blockProperty, (T) value);
	}
	
	public <T> BlockState setProperty(final BlockProperty<T> property, final T value) {
		this.values.add(new BlockProperty.ValuePair<>(property, value));
		
		return this;
	}
	
	public <T> BlockProperty.ValuePair<T> getValuePair(final BlockProperty<T> property) {
		for (final BlockProperty.ValuePair<?> valuePair : this.values) {
			if (valuePair.getProperty() == property)
				return (BlockProperty.ValuePair<T>) valuePair;
		}
		
		return null;
	}
	
	public boolean containsProperty(final BlockProperty<?> property) {
		return getValuePair(property) != null;
	}
	
	public ImmutableSet<BlockProperty.ValuePair<?>> getValuePairs() {
		return this.values.toImmutable();
	}
	
	@Override
	public void freeze() {
		this.values.forEach(BlockProperty.ValuePair::freeze);
		this.values.freeze();
	}
	
	@Override
	public boolean isFrozen() {
		return this.values.isFrozen();
	}
	
	@Override
	public BlockState copy() {
		return new BlockState(this);
	}
}
