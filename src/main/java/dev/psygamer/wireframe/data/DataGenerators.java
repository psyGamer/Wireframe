package dev.psygamer.wireframe.data;

import dev.psygamer.wireframecore.impl.handle.Implementor;

import net.minecraft.data.IDataProvider;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public interface DataGenerators {
	
	static void addDataProvider(final IDataProvider provider) {
		Implementor.execute(provider);
	}
	
	void gatherData(GatherDataEvent event);
}
