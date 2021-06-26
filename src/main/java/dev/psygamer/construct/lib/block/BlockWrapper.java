package dev.psygamer.construct.lib.block;

import dev.psygamer.construct.core.dependant.namespace.Namespace;
import dev.psygamer.construct.core.version.ImplementationHandler;
import net.minecraft.block.Block;

import java.util.List;


public interface BlockWrapper {
	
	static BlockWrapper create(final BlockFactory factory) {
		return ImplementationHandler.executeImplementation(factory);
	}
	
	Namespace getNamespace();
	
	String getRegistryName();
	
	Block getBlock();
	
	BlockFactory getFactory();
	
	boolean hasBlockVariants();
	
	List<BlockWrapper> getBlockVariants();
	
	List<BlockProperty<?>> getBlockProperties();
	
}