package dev.psygamer.construct.impl.common.registry;

import dev.psygamer.construct.core.implementation.ImplementationVersion;
import dev.psygamer.construct.core.implementation.MinecraftVersion;
import dev.psygamer.construct.lib.block.BlockWrapper;
import dev.psygamer.construct.lib.registry.BlockRegistry;
import lombok.Getter;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

@ImplementationVersion(MinecraftVersion.COMMON)
public abstract class CommonBlockRegistry implements BlockRegistry {
	
	@Getter
	protected static List<Block> blocks = new ArrayList<>();
	@Getter
	protected static List<BlockWrapper> blockWrappers = new ArrayList<>();
	
	public static void register(final BlockWrapper blockWrapper) {
		blockWrappers.add(blockWrapper);
	}
}
