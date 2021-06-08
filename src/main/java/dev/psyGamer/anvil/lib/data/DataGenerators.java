package dev.psyGamer.anvil.lib.data;

import net.minecraft.data.IDataProvider;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class DataGenerators {
	
	protected static List<IDataProvider> dataProviders = new ArrayList<>();
	
	public static void addDataProvider(final IDataProvider provider) {
		dataProviders.add(provider);
	}
	
	public abstract void gatherData(GatherDataEvent event);
}
