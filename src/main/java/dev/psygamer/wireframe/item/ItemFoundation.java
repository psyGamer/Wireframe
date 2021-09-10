package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.core.dependant.Namespace;
import dev.psygamer.wireframe.internal.item.InternalItemFoundation;
import dev.psygamer.wireframe.item.util.IItemEvents;

public class ItemFoundation implements IItemEvents {
	
	protected final InternalItemFoundation internal;
	
	protected final String registryName;
	protected final Namespace namespace;
	protected final ItemAttributes attributes;
	
	public ItemFoundation(final String registryName, final ItemAttributes attributes) {
		this.registryName = registryName;
		this.namespace = Namespace.getCurrent();
		this.attributes = attributes;
		
		this.internal = new InternalItemFoundation(this, attributes);
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
	
	public InternalItemFoundation getInternal() {
		return this.internal;
	}
}
