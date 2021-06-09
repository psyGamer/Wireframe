package dev.psyGamer.anvil.impl.v16.data;

import dev.psyGamer.anvil.core.AnvilCore;
import dev.psyGamer.anvil.impl.v16.data.client.BlockStateDataProviderImpl16;
import dev.psyGamer.anvil.lib.data.DataGenerators;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class DataGeneratorsImpl16 extends DataGenerators {
	
	@Override
	@SubscribeEvent
	public void gatherData(final GatherDataEvent event) {
		final DataGenerator generator = event.getGenerator();
		final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		
		generator.addProvider(new BlockStateDataProviderImpl16(generator, AnvilCore.getModImplementation().MODID, existingFileHelper));
	}
}
