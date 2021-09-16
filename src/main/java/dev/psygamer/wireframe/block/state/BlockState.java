package dev.psygamer.wireframe.block.state;

import dev.psygamer.wireframe.block.BlockFoundation;
import dev.psygamer.wireframe.block.state.property.BlockProperty;

import dev.psygamer.wireframe.internal.block.InternalBlockState;
import dev.psygamer.wireframe.util.helper.ICloneable;

import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Set;

public class BlockState implements ICloneable<BlockState> {
	
	private final InternalBlockState internal;
	
	private final BlockFoundation block;
	private final Set<BlockProperty.ValuePair<?>> values;
	
	public BlockState(final BlockFoundation block) {
		this.block = block;
		this.values = new HashSet<>();
		
		this.internal = new InternalBlockState(this);
	}
	
	public BlockState(final BlockState other) {
		this.block = other.block;
		this.values = other.values;
		
		this.internal = other.internal;
	}
	
	public <T extends Comparable<T>> BlockState withValue(final BlockProperty<T> blockProperty, final T value) {
		return copy().setProperty(blockProperty, value);
	}
	
	public <T extends Comparable<T>> BlockState withObjectValue(final BlockProperty<?> blockProperty, final Object value) {
		return withValue((BlockProperty<T>) blockProperty, (T) value);
	}
	
	public <T extends Comparable<T>> BlockState setProperty(final BlockProperty<T> property, final T value) {
		this.values.add(new BlockProperty.ValuePair<>(property, value));
		
		return this;
	}
	
	public <T extends Comparable<T>> BlockProperty.ValuePair<T> getValuePair(final BlockProperty<T> property) {
		for (final BlockProperty.ValuePair<?> valuePair : this.values) {
			if (valuePair.getProperty() == property)
				return (BlockProperty.ValuePair<T>) valuePair;
		}
		
		return null;
	}
	
	public boolean containsProperty(final BlockProperty<?> property) {
		return getValuePair(property) != null;
	}
	
	public BlockFoundation getBlock() {
		return this.block;
	}
	
	public ImmutableSet<BlockProperty.ValuePair<?>> getValuePairs() {
		return ImmutableSet.copyOf(this.values);
	}
	
	public net.minecraft.block.BlockState getInternal() {
		return this.internal.any();
	}
	
	@Override
	public BlockState copy() {
		return new BlockState(this);
	}
}
