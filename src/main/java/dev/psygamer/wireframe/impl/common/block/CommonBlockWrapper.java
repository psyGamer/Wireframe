package dev.psygamer.wireframe.impl.common.block;

import dev.psygamer.wireframe.api.block.BasicBlock;
import dev.psygamer.wireframe.api.block.BlockWrapper;
import dev.psygamer.wireframe.api.registry.BlockRegistry;

import dev.psygamer.wireframe.core.impl.MinecraftVersion;
import dev.psygamer.wireframe.core.impl.ImplementationVersion;
import dev.psygamer.wireframe.core.namespace.Namespace;
import dev.psygamer.wireframe.core.namespace.NamespaceUtil;

@ImplementationVersion(MinecraftVersion.COMMON)
public class CommonBlockWrapper extends BlockWrapper {
	
	protected final BasicBlock block;
	
	protected final Namespace namespace;
	protected final String registryName;
	
	protected CommonBlockWrapper(final BasicBlock block) {
		this.block = block;
		
		this.namespace = NamespaceUtil.getCurrentNamespace();
		this.registryName = block.getRegistryName();
		
		BlockRegistry.register(this);
	}
	
	@Override
	protected BlockWrapper createInstance(final BasicBlock block) {
		return new CommonBlockWrapper(block);
	}
	
	@Override
	public BasicBlock getBlock() {
		return this.block;
	}
	
	@Override
	public Namespace getNamespace() {
		return this.namespace;
	}
	
	@Override
	public String getRegistryName() {
		return this.registryName;
	}
}
