package dev.psygamer.construct.impl.common.block;

import dev.psygamer.construct.core.dependant.namespace.Namespace;
import dev.psygamer.construct.core.dependant.namespace.NamespaceUtil;
import dev.psygamer.construct.lib.block.BlockFactory;
import dev.psygamer.construct.lib.block.BlockProperty;
import dev.psygamer.construct.lib.block.BlockWrapper;
import dev.psygamer.construct.lib.registry.BlockRegistry;
import lombok.Getter;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CommonBlockWrapper implements BlockWrapper {
	
	@Getter
	protected final Namespace namespace;
	@Getter
	protected final String registryName;
	
	@Getter
	protected final Block block;
	protected final BlockFactory factory;
	
	@Getter
	protected List<BlockWrapper> blockVariants = new ArrayList<>();
	@Getter
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
	public BlockFactory getFactory() {
		return this.factory.copy();
	}
	
	@Override
	public boolean hasBlockVariants() {
		return !this.blockProperties.isEmpty();
	}
}
