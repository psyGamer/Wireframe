package dev.psygamer.construct.lib.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class BlockStateDataProvider extends BlockStateProvider {
	
	/**
	 * Only call as super constructor
	 */
	public BlockStateDataProvider(final DataGenerator gen, final String modid, final ExistingFileHelper exFileHelper) {
		super(gen, modid, exFileHelper);
	}
}
