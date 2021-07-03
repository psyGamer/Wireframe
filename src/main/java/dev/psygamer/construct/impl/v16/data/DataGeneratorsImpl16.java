package dev.psygamer.construct.impl.v16.data;

import dev.psygamer.construct.core.dependant.namespace.NamespaceUtil;
import dev.psygamer.construct.core.implementation.ImplementationVersion;
import dev.psygamer.construct.core.implementation.MinecraftVersion;
import dev.psygamer.construct.impl.common.data.CommonDataGenerators;
import dev.psygamer.construct.impl.v16.data.client.BlockStateDataProviderImpl16;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@ImplementationVersion(MinecraftVersion.v16)
public class DataGeneratorsImpl16 extends CommonDataGenerators {
	
	@Override
	@SubscribeEvent
	public void gatherData(final GatherDataEvent event) {
		final DataGenerator generator = event.getGenerator();
		final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		
		generator.addProvider(new BlockStateDataProviderImpl16(generator, NamespaceUtil.getCurrentNamespace().evaluate(), existingFileHelper));
	}
}
