package dev.psyGamer.anvil.lib.block;

import dev.psyGamer.anvil.core.version.ImplementationHandler;
import net.minecraft.block.Block;

import java.util.List;
import java.util.function.Supplier;

public abstract class BlockWrapper {
	
	protected Block block;
	protected BlockFactory factory;
	
	public static BlockWrapper create(final Block block) {
		return (BlockWrapper) ImplementationHandler.executeImplementation(block);
	}
	
	public String getRegistryName() {
		return this.factory.getRegistryName();
	}
	
	public Supplier<Block> getBlockSupplier() {
		return () -> this.block;
	}
	
	public boolean hasBlockVariants() {
		return false;
	}
	
	public List<BlockWrapper> getBlockVariants() {
		return null;
	}
}
