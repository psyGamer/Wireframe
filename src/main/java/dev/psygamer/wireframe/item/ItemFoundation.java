package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.internal.item.InternalItemFoundation;
import dev.psygamer.wireframe.item.util.IItemEvents;
import dev.psygamer.wireframe.registry.ItemRegistry;
import dev.psygamer.wireframe.util.Identifier;

public class ItemFoundation implements IItemEvents {
	
	protected final InternalItemFoundation internal;
	
	protected final Identifier identifier;
	
	protected final ItemAttributes attributes;
	
	public ItemFoundation(final Identifier identifier, final ItemAttributes attributes) {
		this.identifier = identifier;
		this.attributes = attributes;
		
		this.internal = new InternalItemFoundation(this, attributes);
		
		ItemRegistry.register(this);
	}
	
	public Identifier getIdentifier() {
		return this.identifier;
	}
	
	public ItemAttributes getAttributes() {
		return this.attributes;
	}
	
	public InternalItemFoundation getInternal() {
		return this.internal;
	}
}
