package dev.psyGamer.anvil.lib.block;

import dev.psyGamer.anvil.core.AnvilCore;
import dev.psyGamer.anvil.core.Namespace;
import dev.psyGamer.anvil.core.version.MinecraftVersion;
import dev.psyGamer.anvil.core.version.SupportedSince;
import dev.psyGamer.anvil.lib.registry.BlockRegistry;
import lombok.Getter;
import net.minecraft.block.Block;

import java.util.List;

@SupportedSince(MinecraftVersion.v16)
public class BlockWrapper {
	
	@Getter
	private final Namespace namespace;
	@Getter
	private final String registryName;
	
	@Getter
	private final Block block;
	private final BlockFactory factory;
	
	@Getter
	private List<BlockWrapper> blockVariants;
	@Getter
	private List<BlockProperty<?>> blockProperties;
	
	private BlockWrapper(final BlockFactory blockFactory) {
		this.namespace = AnvilCore.Util.getCurrentNamespace();
		this.registryName = blockFactory.getRegistryName();
		
		this.block = blockFactory.build();
		this.factory = blockFactory;
		
		BlockRegistry.registerBlockWrapper(this);
	}
	
	public static BlockWrapper create(final BlockFactory factory) {
		return new BlockWrapper(factory);
	}
	
	public BlockFactory getFactory() {
		return this.factory.copy();
	}
	
	public boolean hasBlockVariants() {
		return this.blockVariants.size() > 0;
	}
}
