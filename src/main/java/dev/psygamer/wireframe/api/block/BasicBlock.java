package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.api.block.property.BlockProperty;
import dev.psygamer.wireframe.core.impl.Instancer;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicBlock {
	
	private static final BasicBlock INSTANCE = Instancer.createInstance();
	
	protected final String registryName;
	protected final BlockProperties blockProperties;
	
	protected final List<BlockProperty<?>> blockStateProperties = new ArrayList<>();
	
	public BasicBlock(final String blockName, final BlockProperties blockProperties) {
		this.registryName = blockName;
		this.blockProperties = blockProperties;
	}
	
	public String getRegistryName() {
		return this.registryName;
	}
	
	public BlockProperties getBlockProperties() {
		return this.blockProperties;
	}
	
	protected void registerBlockStateProperty(final BlockProperty<?> property) {
		this.blockStateProperties.add(property);
	}
}
