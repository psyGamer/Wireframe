package dev.psygamer.wireframe.block.state;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.block.state.property.BlockProperty;

import dev.psygamer.wireframe.util.IFreezable;
import dev.psygamer.wireframe.util.collection.FreezableLinkedList;
import dev.psygamer.wireframe.util.collection.FreezableList;
import dev.psygamer.wireframe.util.collection.FreezableSet;
import dev.psygamer.wireframe.util.collection.FreezableLinkedHashSet;

import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockPropertySet implements IFreezable {
	
	private final FreezableSet<BlockProperty<?>> properties;
	private final FreezableList<BlockState> propertyContainers;
	
	public BlockPropertySet() {
		this.properties = new FreezableLinkedHashSet<>();
		this.propertyContainers = new FreezableLinkedList<>();
	}
	
	public void addProperty(final BlockProperty<?> property) {
		this.properties.add(property);
	}
	
	public void compilePropertyContainers() {
		final int totalSize = this.properties.stream()
				.mapToInt(prop -> prop.getPossibleValues().size())
				.reduce(0, Integer::sum);
		
		final BlockState[] compiledContainers = new BlockState[totalSize];
		
		compileRecursively(
				new ArrayList<>(this.properties),
				new BlockState(this),
				
				compiledContainers
		);
		
		this.propertyContainers.clear();
		this.propertyContainers.addAll(Arrays.asList(compiledContainers));
		
		this.propertyContainers.forEach(BlockState::freeze);
		this.propertyContainers.freeze();
	}
	
	private void compileRecursively(final List<BlockProperty<?>> propertiesLeft, final BlockState propertyContainer, final BlockState[] compiledContainers) {
		if (propertiesLeft.isEmpty()) {
			compiledContainers[propertyContainer.getIndex()] = propertyContainer;
			
			return;
		}
		
		final BlockProperty<?> currentProperty = propertiesLeft.get(0);
		
		propertiesLeft.remove(currentProperty);
		
		currentProperty.getPossibleValues().forEach(value -> {
			final BlockState propertyContainerCopy = propertyContainer.copy();
			
			propertyContainerCopy.getValuePair(currentProperty).setObjectValue(value);
			compileRecursively(new ArrayList<>(this.properties), propertyContainerCopy, compiledContainers);
		});
	}
	
	public int getPropertyIndex(final BlockProperty<?> property) {
		return new ArrayList<>(this.properties).indexOf(property);
	}
	
	public ImmutableList<BlockState> getPossibleContainers() {
		return this.propertyContainers.toImmutable();
	}
	
	public ImmutableSet<BlockProperty<?>> getProperties() {
		return this.properties.toImmutable();
	}
	
	@Override
	public void freeze() {
		if (!this.propertyContainers.isFrozen()) {
			compilePropertyContainers();
			
			this.propertyContainers.forEach(BlockState::freeze);
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
