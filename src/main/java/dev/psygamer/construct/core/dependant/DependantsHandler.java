package dev.psygamer.construct.core.dependant;

import dev.psygamer.construct.core.exceptions.LibraryException;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;

public final class DependantsHandler {
	
	private static final List<Dependant<?>> dependants = new ArrayList<>();
	
	public static List<Dependant<?>> getDependants() {
		return new ArrayList<>(dependants);
	}
	
	public static <T> void registerMod(final Class<T> modClass, final FMLJavaModLoadingContext modLoadingContext) {
		if (!modClass.isAnnotationPresent(Mod.class)) {
			throw new LibraryException("Mod class is not annotated with @Mod");
		}
		
		dependants.add(new Dependant<>(
				modClass,
				modLoadingContext
		));
	}
}
