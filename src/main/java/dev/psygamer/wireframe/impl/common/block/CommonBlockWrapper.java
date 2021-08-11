package dev.psygamer.wireframe.impl.common.block;

import dev.psygamer.wireframe.api.block.BlockProperties;
import dev.psygamer.wireframe.core.namespace.Namespace;
import dev.psygamer.wireframe.core.namespace.NamespaceUtil;
import dev.psygamer.wireframe.core.impl.ImplementationVersion;
import dev.psygamer.wireframe.core.impl.MinecraftVersion;

import dev.psygamer.wireframe.api.block.BlockProperty;
import dev.psygamer.wireframe.api.block.BlockWrapper;
import dev.psygamer.wireframe.api.registry.BlockRegistry;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

@ImplementationVersion(MinecraftVersion.COMMON)
public class CommonBlockWrapper implements BlockWrapper {
	
	protected final Namespace namespace;
	protected final String registryName;
	protected final Block block;
	protected final BlockProperties factory;
	protected List<BlockWrapper> blockVariants = new ArrayList<>();
	protected List<BlockProperty<?>> blockProperties = new ArrayList<>();
	
	protected CommonBlockWrapper(final BlockProperties blockFactory) {
		this.namespace = NamespaceUtil.getCurrentNamespace();
		this.registryName = blockFactory.getRegistryName();
		
		this.block = blockFactory.createBlock();
		this.factory = blockFactory;
		
		BlockRegistry.register(this);
	}
	
	public static BlockWrapper create(final BlockProperties factory) {
		return new CommonBlockWrapper(factory);
	}
	
	@Override
	public Namespace getNamespace() {
		return this.namespace;
	}
	
	@Override
	public String getRegistryName() {
		return this.registryName;
	}
	
	@Override
	public Block getBlock() {
		return this.block;
	}
	
	@Override
	public List<BlockWrapper> getBlockVariants() {
		return this.blockVariants;
	}
	
	@Override
	public List<BlockProperty<?>> getBlockProperties() {
		return this.blockProperties;
	}
	
	@Override
	public BlockProperties getFactory() {
		return this.factory.copy();
	}
	
	@Override
	public boolean hasBlockVariants() {
		return !this.blockProperties.isEmpty();
	}
}
