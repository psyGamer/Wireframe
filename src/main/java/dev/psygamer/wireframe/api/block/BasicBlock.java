package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.core.impl.Instancer;

public abstract class BasicBlock {
	
	private static final BasicBlock instance = Instancer.createInstance();
	
	protected final String registryName;
	
	public BasicBlock(final String blockName, final BlockProperties properties) {
		this.registryName = blockName;
	}
	
	public String getRegistryName() {
		return this.registryName;
	}
}
