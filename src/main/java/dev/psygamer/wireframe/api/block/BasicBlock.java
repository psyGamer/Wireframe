package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.api.block.property.BlockProperty;
import dev.psygamer.wireframe.core.impl.Instancer;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicBlock {
	
	private static final BasicBlock INSTANCE = Instancer.createInstance();
	
	protected final String registryName;
	protected final BlockAttributes attributes;
	
	protected final List<BlockProperty<?>> blockStateProperties = new ArrayList<>();
	
	public BasicBlock(final String blockName, final BlockAttributes attributes) {
		this.registryName = blockName;
		this.attributes = attributes;
	}
	
	public String getRegistryName() {
		return this.registryName;
	}
	
	public BlockAttributes getAttributes() {
		return this.attributes;
	}
	
	protected void registerBlockStateProperty(final BlockProperty<?> property) {
		this.blockStateProperties.add(property);
	}
}
