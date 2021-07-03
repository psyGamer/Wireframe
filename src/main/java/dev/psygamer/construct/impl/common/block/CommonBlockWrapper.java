package dev.psygamer.construct.impl.common.block;

import dev.psygamer.construct.core.namespace.Namespace;
import dev.psygamer.construct.core.namespace.NamespaceUtil;
import dev.psygamer.construct.core.implementation.ImplementationVersion;
import dev.psygamer.construct.core.implementation.MinecraftVersion;

import dev.psygamer.construct.lib.block.BlockFactory;
import dev.psygamer.construct.lib.block.BlockProperty;
import dev.psygamer.construct.lib.block.BlockWrapper;
import dev.psygamer.construct.lib.registry.BlockRegistry;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

@ImplementationVersion(MinecraftVersion.COMMON)
public class CommonBlockWrapper implements BlockWrapper {
	
	protected final Namespace namespace;
	protected final String registryName;
	protected final Block block;
	protected final BlockFactory factory;
	protected List<BlockWrapper> blockVariants = new ArrayList<>();
	protected List<BlockProperty<?>> blockProperties = new ArrayList<>();
	
	protected CommonBlockWrapper(final BlockFactory blockFactory) {
		this.namespace = NamespaceUtil.getCurrentNamespace();
		this.registryName = blockFactory.getRegistryName();
		
		this.block = blockFactory.createBlock();
		this.factory = blockFactory;
		
		BlockRegistry.register(this);
	}
	
	public static BlockWrapper create(final BlockFactory factory) {
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
	public BlockFactory getFactory() {
		return this.factory.copy();
	}
	
	@Override
	public boolean hasBlockVariants() {
		return !this.blockProperties.isEmpty();
	}
}
