package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.item.util.IItemEvents;
import dev.psygamer.wireframe.core.dependant.Namespace;

public class ItemFoundation implements IItemEvents {
	
	protected final String registryName;
	protected final Namespace namespace;
	protected final ItemAttributes attributes;
	
	public ItemFoundation(final String registryName, final ItemAttributes attributes) {
		this.registryName = registryName;
		this.namespace = Namespace.getCurrent();
		this.attributes = attributes;
	}
	
	public String getRegistryName() {
		return this.registryName;
	}
	
	public Namespace getNamespace() {
		return this.namespace;
	}
	
	public ItemAttributes getAttributes() {
		return this.attributes;
	}
}
