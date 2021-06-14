package dev.psyGamer.anvil.impl.common.registry;

import dev.psyGamer.anvil.lib.block.BlockWrapper;
import dev.psyGamer.anvil.lib.registry.BlockRegistry;
import lombok.Getter;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonBlockRegistry implements BlockRegistry {
	
	@Getter
	protected static List<Block> blocks = new ArrayList<>();
	@Getter
	protected static List<BlockWrapper> blockWrappers = new ArrayList<>();
	
	public static void addBlockWrapper(final BlockWrapper blockWrapper) {
		blockWrappers.add(blockWrapper);
	}
}
