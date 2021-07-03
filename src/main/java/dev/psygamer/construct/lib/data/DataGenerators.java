package dev.psygamer.construct.lib.data;

import dev.psygamer.construct.core.implementation.ImplementationHandler;
import dev.psygamer.construct.lib.Construct;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public interface DataGenerators {
	
	static void addDataProvider(final IDataProvider provider) {
		Construct.Core.executeImplementation(provider);
	}
	
	void gatherData(GatherDataEvent event);
}
