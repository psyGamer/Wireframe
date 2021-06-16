package dev.psygamer.ferrus.impl.v16.data;

import dev.psygamer.ferrus.core.FerrusCore;
import dev.psygamer.ferrus.impl.common.data.CommonDataGenerators;
import dev.psygamer.ferrus.impl.v16.data.client.BlockStateDataProviderImpl16;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class DataGeneratorsImpl16 extends CommonDataGenerators {
	
	@Override
	@SubscribeEvent
	public void gatherData(final GatherDataEvent event) {
		final DataGenerator generator = event.getGenerator();
		final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		
		generator.addProvider(new BlockStateDataProviderImpl16(generator, FerrusCore.Util.getCurrentNamespace().get(), existingFileHelper));
	}
}
