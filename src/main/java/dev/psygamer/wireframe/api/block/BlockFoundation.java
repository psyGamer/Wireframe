package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.api.block.util.BlockUtilityMethods;
import dev.psygamer.wireframe.api.block.state.BlockPropertySet;
import dev.psygamer.wireframe.api.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.api.block.state.property.BlockProperty;

import dev.psygamer.wireframe.core.namespace.Namespace;
import dev.psygamer.wireframe.core.namespace.NamespaceUtil;

import dev.psygamer.wireframe.util.IFreezable;

public class BlockFoundation extends BlockUtilityMethods implements IFreezable {
	
	protected final String registryName;
	protected final Namespace namespace;
	protected final BlockAttributes attributes;
	
	protected final BlockPropertySet propertySet;
	protected final BlockPropertyContainer defaultPropertyContainer;
	
	public BlockFoundation(final String registryName, final BlockAttributes attributes) {
		this.registryName = registryName;
		this.namespace = NamespaceUtil.getCurrentNamespace();
		this.attributes = attributes;
		
		this.propertySet = new BlockPropertySet();
		this.defaultPropertyContainer = new BlockPropertyContainer(this.propertySet);
	}
	
	public String getRegistryName() {
		return this.registryName;
	}
	
	public Namespace getNamespace() {
		return this.namespace;
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
		this.propertySet.freeze();
		this.defaultPropertyContainer.freeze();
	}
	
	@Override
	public boolean isFrozen() {
		return this.propertySet.isFrozen() || this.defaultPropertyContainer.isFrozen();
	}
}