package dev.psygamer.wireframe.impl.common.data;

import dev.psygamer.wireframe.core.impl.ImplementationVersion;
import dev.psygamer.wireframe.core.impl.MinecraftVersion;
import dev.psygamer.wireframe.api.data.DataGenerators;

import net.minecraft.data.IDataProvider;

import java.util.ArrayList;
import java.util.List;

@ImplementationVersion(MinecraftVersion.COMMON)
public abstract class CommonDataGenerators implements DataGenerators {
	
	protected static List<IDataProvider> dataProviders = new ArrayList<>();
	
	public static void addDataProvider(final IDataProvider provider) {
		dataProviders.add(provider);
	}
}
