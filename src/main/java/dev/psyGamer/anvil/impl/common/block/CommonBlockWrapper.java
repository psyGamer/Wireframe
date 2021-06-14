package dev.psyGamer.anvil.impl.common.block;

import dev.psyGamer.anvil.core.AnvilCore;
import dev.psyGamer.anvil.core.Namespace;
import dev.psyGamer.anvil.lib.block.BlockFactory;
import dev.psyGamer.anvil.lib.block.BlockProperty;
import dev.psyGamer.anvil.lib.block.BlockWrapper;
import dev.psyGamer.anvil.lib.registry.BlockRegistry;
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
		this.namespace = AnvilCore.Util.getCurrentNamespace();
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
