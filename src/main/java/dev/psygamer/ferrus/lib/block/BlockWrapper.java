package dev.psygamer.ferrus.lib.block;

import dev.psygamer.ferrus.core.Namespace;
import dev.psygamer.ferrus.core.version.ImplementationHandler;
import dev.psygamer.ferrus.core.version.MinecraftVersion;
import dev.psygamer.ferrus.core.version.SupportedSince;
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