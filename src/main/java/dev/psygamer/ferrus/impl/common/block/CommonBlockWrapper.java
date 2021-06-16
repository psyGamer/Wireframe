package dev.psygamer.ferrus.impl.common.block;

import dev.psygamer.ferrus.core.FerrusCore;
import dev.psygamer.ferrus.core.Namespace;
import dev.psygamer.ferrus.lib.block.BlockFactory;
import dev.psygamer.ferrus.lib.block.BlockProperty;
import dev.psygamer.ferrus.lib.block.BlockWrapper;
import dev.psygamer.ferrus.lib.registry.BlockRegistry;
import lombok.Getter;
import net.minecraft.block.Block;

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
	protected List<BlockWrapper> blockVariants;
	@Getter
	protected List<BlockProperty<?>> blockProperties;
	
	protected CommonBlockWrapper(final BlockFactory blockFactory) {
		this.namespace = FerrusCore.Util.getCurrentNamespace();
		this.registryName = blockFactory.getRegistryName();
		
		this.block = blockFactory.build();
		this.factory = blockFactory;
		
		BlockRegistry.addBlockWrapper(this);
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
