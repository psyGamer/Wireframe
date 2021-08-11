package dev.psygamer.wireframe.api.data;

import dev.psygamer.wireframe.core.impl.Implementor;

import net.minecraft.data.IDataProvider;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public interface DataGenerators {
	
	static void addDataProvider(final IDataProvider provider) {
		Implementor.execute(provider);
	}
	
	void gatherData(GatherDataEvent event);
}
