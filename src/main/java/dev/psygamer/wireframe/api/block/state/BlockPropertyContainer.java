package dev.psygamer.wireframe.api.block.state;

import dev.psygamer.wireframe.api.block.state.property.BlockProperty;
import dev.psygamer.wireframe.util.ICloneable;
import dev.psygamer.wireframe.util.IFreezable;
import dev.psygamer.wireframe.util.collection.FreezableSet;
import dev.psygamer.wireframe.util.collection.FreezableHashSet;

public class BlockPropertyContainer implements IFreezable, ICloneable<BlockPropertyContainer> {
	
	private final FreezableSet<BlockProperty.ValuePair<?>> values;
	
	public BlockPropertyContainer(final BlockPropertySet propertySet) {
		this.values = new FreezableHashSet<>();
		
		propertySet.getProperties().forEach(property -> this.values.add(new BlockProperty.ValuePair<>(property)));
	}
	
	public BlockPropertyContainer(final BlockPropertyContainer baseContainer) {
		this.values = baseContainer.values;
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
