package dev.psygamer.wireframe.api.block.state;

import dev.psygamer.wireframe.api.block.state.property.BlockProperty;

import dev.psygamer.wireframe.util.IFreezable;
import dev.psygamer.wireframe.util.collection.FreezableSet;
import dev.psygamer.wireframe.util.collection.FreezableHashSet;

import java.util.ArrayList;
import java.util.List;

public class BlockPropertySet implements IFreezable {
	
	private final FreezableSet<BlockProperty<?>> properties;
	private final FreezableSet<BlockPropertyContainer> propertyContainers;
	
	public BlockPropertySet() {
		this.properties = new FreezableHashSet<>();
		this.propertyContainers = new FreezableHashSet<>();
	}
	
	public void addProperty(final BlockProperty<?> property) {
		this.properties.add(property);
	}
	
	public void compilePropertyContainers() {
		compileRecursively(new BlockPropertyContainer(this), new ArrayList<>(this.properties));
		
		this.propertyContainers.forEach(BlockPropertyContainer::freeze);
		this.propertyContainers.freeze();
	}
	
	private void compileRecursively(final BlockPropertyContainer propertyContainer, final List<BlockProperty<?>> properties) {
		if (properties.isEmpty()) {
			this.propertyContainers.add(propertyContainer);
			
			return;
		}
		
		final BlockProperty<?> property = properties.get(0);
		
		properties.remove(property);
		
		property.getPossibleValues().forEach(value -> {
			final BlockPropertyContainer propertyContainerCopy = propertyContainer.copy();
			
			propertyContainerCopy.getValuePair(property).setObjectValue(value);
			compileRecursively(propertyContainerCopy, new ArrayList<>(this.properties));
		});
	}
	
	public FreezableSet<BlockProperty<?>> getProperties() {
		return this.properties.copy();
	}
	
	@Override
	public void freeze() {
		if (!this.propertyContainers.isFrozen()) {
			compilePropertyContainers();
			
			this.propertyContainers.forEach(BlockPropertyContainer::freeze);
			this.propertyContainers.freeze();
		}
		
		
		this.properties.forEach(BlockProperty::freeze);
		this.properties.freeze();
	}
	
	@Override
	public boolean isFrozen() {
		return this.properties.isFrozen();
	}
}
