package dev.psygamer.construct.lib.block;

import dev.psygamer.construct.core.Namespace;
import dev.psygamer.construct.core.version.ImplementationHandler;
import dev.psygamer.construct.core.version.MinecraftVersion;
import dev.psygamer.construct.core.version.SupportedSince;
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