package dev.psygamer.wireframe.block;

import dev.psygamer.wireframe.Wireframe;
import dev.psygamer.wireframe.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.block.state.BlockPropertySet;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.block.util.BlockUtilityMethods;
import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.dependant.Namespace;
import dev.psygamer.wireframe.core.event.RegistryEvents;
import dev.psygamer.wireframe.internal.block.InternalBlockFoundation;
import dev.psygamer.wireframe.registry.BlockRegistry;
import dev.psygamer.wireframe.util.IFreezable;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockFoundation extends BlockUtilityMethods implements IFreezable {
	
	protected final InternalBlockFoundation internal;
	
	protected final String registryName;
	protected final Namespace namespace;
	protected final BlockAttributes attributes;
	protected final BlockPropertySet propertySet;
	protected final BlockPropertyContainer defaultPropertyContainer;
	
	public BlockFoundation(final String registryName, final BlockAttributes attributes) {
		this.registryName = registryName;
		this.namespace = Namespace.getCurrent();
		this.attributes = attributes;
		
		this.propertySet = new BlockPropertySet();
		this.defaultPropertyContainer = new BlockPropertyContainer(this.propertySet);
		
		this.internal = new InternalBlockFoundation(this, attributes);
		
		Wireframe.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onRegistryPreparation(final RegistryEvents.Prepare event) {
		BlockRegistry.register(this);
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
	
	public InternalBlockFoundation getInternal() {
		return this.internal;
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
