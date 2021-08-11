package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.core.impl.Implementor;
import dev.psygamer.wireframe.core.namespace.Namespace;

import net.minecraft.block.Block;

import java.util.List;

public interface BlockWrapper {
	
	static BlockWrapper create(final BlockProperties factory) {
		return Implementor.execute(factory);
	}
	
	Namespace getNamespace();
	
	String getRegistryName();
	
	Block getBlock();
	
	BlockProperties getFactory();
	
	List<BlockWrapper> getBlockVariants();
}