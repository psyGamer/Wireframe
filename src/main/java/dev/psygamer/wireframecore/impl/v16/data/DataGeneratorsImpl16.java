package dev.psygamer.wireframecore.impl.v16.data;

import dev.psygamer.wireframecore.namespace.NamespaceUtil;
import dev.psygamer.wireframecore.impl.ImplementationVersion;
import dev.psygamer.wireframecore.impl.MinecraftVersion;
import dev.psygamer.wireframecore.impl.common.data.CommonDataGenerators;
import dev.psygamer.wireframecore.impl.v16.data.client.BlockStateDataProviderImpl16;
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
