package dev.psygamer.wireframe.internal.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class InternalItemModelDataProvider extends ItemModelProvider {
	
	protected InternalItemModelDataProvider(final DataGenerator generator, final String modid, final ExistingFileHelper existingFileHelper) {
		super(generator, modid, existingFileHelper);
	}
	
	@Override
	protected void registerModels() {
	
	}
}
