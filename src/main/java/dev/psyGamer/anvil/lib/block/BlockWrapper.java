package dev.psyGamer.anvil.lib.block;

import dev.psyGamer.anvil.core.version.ImplementationHandler;
import lombok.Getter;
import net.minecraft.block.Block;

import java.util.List;

public abstract class BlockWrapper {
	
	@Getter
	protected String namespace;
	@Getter
	protected String registryName;
	
	@Getter
	protected Block block;
	protected BlockFactory factory;
	
	@Getter
	protected List<BlockWrapper> blockVariants;
	@Getter
	protected List<BlockProperty<?>> blockProperties;
	
	public static BlockWrapper create(final Block block, final BlockFactory factory) {
		return (BlockWrapper) ImplementationHandler.executeImplementation(block, factory);
	}
	
	public BlockFactory getFactory() {
		return this.factory.copy();
	}
	
	public boolean hasBlockVariants() {
		return this.blockVariants.size() > 0;
	}
}
