package dev.psyGamer.anvil.lib.block;

import dev.psyGamer.anvil.core.Namespace;
import dev.psyGamer.anvil.core.version.ImplementationHandler;
import dev.psyGamer.anvil.core.version.MinecraftVersion;
import dev.psyGamer.anvil.core.version.SupportedSince;
import net.minecraft.block.Block;

import java.util.List;

@SupportedSince(MinecraftVersion.v16)
public interface BlockWrapper {
	
	static BlockWrapper create(final BlockFactory factory) {
		return (BlockWrapper) ImplementationHandler.executeImplementation(factory);
	}
	
	Namespace getNamespace();
	
	String getRegistryName();
	
	Block getBlock();
	
	BlockFactory getFactory();
	
	boolean hasBlockVariants();
	
	List<BlockWrapper> getBlockVariants();
	
	List<BlockProperty<?>> getBlockProperties();
	
}
