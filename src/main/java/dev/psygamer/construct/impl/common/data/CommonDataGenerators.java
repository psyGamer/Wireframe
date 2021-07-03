package dev.psygamer.construct.impl.common.data;

import dev.psygamer.construct.core.implementation.ImplementationVersion;
import dev.psygamer.construct.core.implementation.MinecraftVersion;
import dev.psygamer.construct.lib.data.DataGenerators;
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
