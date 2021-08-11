package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.core.impl.Instancer;
import dev.psygamer.wireframe.core.namespace.Namespace;

public abstract class BlockWrapper {
	
	private static final BlockWrapper INSTANCE = Instancer.createInstance();
	
	public static BlockWrapper create(final BasicBlock block) {
		return INSTANCE.createInstance(block);
	}
	
	protected abstract BlockWrapper createInstance(BasicBlock block);
	
	public abstract BasicBlock getBlock();
	
	public abstract Namespace getNamespace();
	
	public abstract String getRegistryName();
}