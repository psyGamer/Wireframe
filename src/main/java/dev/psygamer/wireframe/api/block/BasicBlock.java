package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.core.impl.Instancer;

public abstract class BasicBlock {
	
	private static final BasicBlock instance = Instancer.createInstance();
	
	public BasicBlock(final String blockName, final BlockProperties properties) {
	
	}
}
