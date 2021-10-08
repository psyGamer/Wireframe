package dev.psygamer.wireframe.data;

import dev.psygamer.wireframe.internal.data.InternalDataGenerators;
import net.minecraft.data.IDataProvider;

import java.util.ArrayList;
import java.util.List;

public class DataGenerators {
	
	protected static InternalDataGenerators internal = new InternalDataGenerators();
	protected static List<IDataProvider> dataProviders = new ArrayList<>();
	
	public static void addDataProvider(final IDataProvider provider) {
		dataProviders.add(provider);
	}
	
	public static InternalDataGenerators getInternal() {
		return internal;
	}
}
