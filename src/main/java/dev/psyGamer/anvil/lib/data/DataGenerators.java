package dev.psyGamer.anvil.lib.data;

import dev.psyGamer.anvil.core.version.ImplementationHandler;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public interface DataGenerators {
	
	static void addDataProvider(final IDataProvider provider) {
		ImplementationHandler.executeImplementation(provider);
	}
	
	void gatherData(GatherDataEvent event);
}
