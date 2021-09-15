package dev.psygamer.wireframe.block.state;

import dev.psygamer.wireframe.block.state.property.BlockProperty;

import dev.psygamer.wireframe.util.ICloneable;

import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Set;

public class BlockState implements ICloneable<BlockState> {
	
	private final Set<BlockProperty.ValuePair<?>> values;
	
	public BlockState() {
		this.values = new HashSet<>();
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
		return ImmutableSet.copyOf(this.values);
	}
	
	@Override
	public BlockState copy() {
		return new BlockState(this);
	}
}
