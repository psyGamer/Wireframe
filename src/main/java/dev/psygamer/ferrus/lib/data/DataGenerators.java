package dev.psygamer.ferrus.lib.data;

import dev.psygamer.ferrus.core.version.ImplementationHandler;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public interface DataGenerators {
	
	static void addDataProvider(final IDataProvider provider) {
		ImplementationHandler.executeImplementation(provider);
	}
	
	void gatherData(GatherDataEvent event);
}
