package dev.psyGamer.anvil.lib.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class ItemModelDataProvider extends ItemModelProvider {
	
	/**
	 * Only call as super constructor
	 */
	public ItemModelDataProvider(final DataGenerator generator, final String modid, final ExistingFileHelper existingFileHelper) {
		super(generator, modid, existingFileHelper);
	}
}
