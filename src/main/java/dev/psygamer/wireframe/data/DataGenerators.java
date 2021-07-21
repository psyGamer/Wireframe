package dev.psygamer.wireframe.data;

import dev.psygamer.wireframe.core.impl.ImplementationVersion;
import dev.psygamer.wireframe.core.impl.handle.Implementor;
import dev.psygamer.wireframe.core.impl.MethodCache;

import net.minecraft.data.IDataProvider;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public interface DataGenerators {
	
	@MethodCache.StartupOnly
	static void addDataProvider(final IDataProvider provider) {
		Implementor.execute(provider);
	}
	
	void gatherData(GatherDataEvent event);
}
