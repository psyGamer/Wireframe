package dev.psygamer.wireframe.data;

import dev.psygamer.wireframe.Wireframe;

import net.minecraft.data.IDataProvider;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public interface DataGenerators {
	
	static void addDataProvider(final IDataProvider provider) {
		Wireframe.Core.executeImplementation(provider);
	}
	
	void gatherData(GatherDataEvent event);
}
