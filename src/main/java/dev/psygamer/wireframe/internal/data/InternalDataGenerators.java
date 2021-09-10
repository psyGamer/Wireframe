package dev.psygamer.wireframe.internal.data;

import dev.psygamer.wireframe.core.dependant.Namespace;
import dev.psygamer.wireframe.internal.data.client.InternalBlockStateDataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class InternalDataGenerators {
	
	@SubscribeEvent
	public void gatherData(final GatherDataEvent event) {
		final DataGenerator generator = event.getGenerator();
		final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		
		generator.addProvider(new InternalBlockStateDataProvider(generator, Namespace.getCurrent().evaluate(), existingFileHelper));
	}
}
