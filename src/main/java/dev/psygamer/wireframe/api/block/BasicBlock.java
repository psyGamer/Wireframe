package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.api.block.state.BlockPropertySet;
import dev.psygamer.wireframe.api.block.state.property.BlockProperty;

import dev.psygamer.wireframe.api.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.core.impl.Instancer;
import dev.psygamer.wireframe.util.IFreezable;

public class BasicBlock extends BlockEvents implements IFreezable {
	
	private static final BasicBlock INSTANCE = Instancer.createInstance();
	
	protected final String registryName;
	protected final BlockAttributes attributes;
	
	protected final BlockPropertySet propertySet;
	
	protected BlockPropertyContainer defaultPropertyContainer;
	
	public BasicBlock(final String registryName, final BlockAttributes attributes) {
		this.registryName = registryName;
		this.attributes = attributes;
		
		this.propertySet = new BlockPropertySet();
	}
	
	public String getRegistryName() {
		return this.registryName;
	}
	
	public BlockAttributes getAttributes() {
		return this.attributes;
	}
	
	public BlockPropertyContainer getDefaultBlockPropertyContainer() {
		return this.defaultPropertyContainer.copy();
	}
	
	protected <T> void registerBlockStateProperty(final BlockProperty<T> property) {
		if (this.defaultPropertyContainer.containsProperty(property))
			return;
		
		this.defaultPropertyContainer.setProperty(property, property.getDefaultValue());
	}
	
	@Override
	public void freeze() {
		this.defaultPropertyContainer.freeze();
	}
	
	@Override
	public boolean isFrozen() {
		return this.defaultPropertyContainer.isFrozen();
	}
}
