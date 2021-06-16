package dev.psyGamer.anvil.impl.common.data;

import dev.psyGamer.anvil.lib.data.DataGenerators;
import net.minecraft.data.IDataProvider;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonDataGenerators implements DataGenerators {
	
	protected static List<IDataProvider> dataProviders = new ArrayList<>();
	
	public static void addDataProvider(final IDataProvider provider) {
		dataProviders.add(provider);
	}
}
