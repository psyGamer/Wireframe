package dev.psygamer.wireframe.api.block.state;

import dev.psygamer.wireframe.api.block.state.property.BlockProperty;

import dev.psygamer.wireframe.util.ICloneable;
import dev.psygamer.wireframe.util.IFreezable;
import dev.psygamer.wireframe.util.collection.FreezableSet;
import dev.psygamer.wireframe.util.collection.FreezableLinkedHashSet;

import com.google.common.collect.ImmutableSet;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.ArrayList;
import java.util.List;

public class BlockPropertyContainer implements IFreezable, ICloneable<BlockPropertyContainer> {
	
	private final BlockPropertySet propertySet;
	private final FreezableSet<BlockProperty.ValuePair<?>> values;
	
	public BlockPropertyContainer(final BlockPropertySet propertySet) {
		this.values = new FreezableLinkedHashSet<>();
		this.propertySet = propertySet;
		
		propertySet.getProperties().forEach(property -> this.values.add(new BlockProperty.ValuePair<>(property)));
	}
	
	public BlockPropertyContainer(final BlockPropertyContainer baseContainer) {
		this.values = baseContainer.values;
		this.propertySet = baseContainer.propertySet;
	}
	
	public <T> BlockPropertyContainer withValue(final BlockProperty<T> blockProperty, final T value) {
		return this.propertySet.getPossibleContainers().get(this.propertySet.getProperties().stream()
				.map(property -> {
					if (property == blockProperty)
						return new BlockProperty.ValuePair<>(blockProperty, value);
					
					return getValuePair(property);
				})
				.mapToInt(BlockProperty.ValuePair::getValueIndex)
				.reduce(0, (subtotal, current) -> subtotal * (current + 1)));
	}
	
	public <T> void setProperty(final BlockProperty<T> property, final T value) {
		this.values.add(new BlockProperty.ValuePair<>(property, value));
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
	
	public BlockPropertySet getPropertySet() {
		return this.propertySet;
	}
	
	public int getIndex() {
		return this.propertySet.getProperties().stream()
				.map(this::getValuePair)
				.mapToInt(BlockProperty.ValuePair::getValueIndex)
				.reduce(0, (subtotal, current) -> subtotal * (current + 1));
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
	public BlockPropertyContainer copy() {
		return new BlockPropertyContainer(this);
	}
}
