package dev.psygamer.wireframe.core.dependant;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;

public final class DependantsHandler {
	
	private static final List<Dependant<?>> dependants = new ArrayList<>();
	
	public static List<Dependant<?>> getDependants() {
		return new ArrayList<>(dependants);
	}
	
	public static <T> void registerDependant(final Class<T> modClass, final FMLJavaModLoadingContext modLoadingContext) {
		dependants.add(new Dependant<>(
				modClass,
				modLoadingContext
		));
	}
}
