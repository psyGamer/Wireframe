package dev.psygamer.construct.core;

import dev.psygamer.construct.core.dependant.DependantsHandler;
import dev.psygamer.construct.core.namespace.NamespaceHandler;
import dev.psygamer.construct.core.exceptions.LibraryException;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConstructCore {
	
	public static final Logger LOGGER = LogManager.getLogger("Construct");
	public static final String MODID = "construct";
	
	public static void register(final Class<?> modClass, final FMLJavaModLoadingContext modLoadingContext) {
		if (!modClass.isAnnotationPresent(Mod.class)) {
			throw new LibraryException("Mod class is not annotated with @Mod");
		}
		
		DependantsHandler.registerDependant(
				modClass,
				modLoadingContext
		);
		
		NamespaceHandler.registerNamespace(
				modClass.getAnnotation(Mod.class).value(),
				modClass.getPackage().getName()
		);
	}
	
	public static final class Debug {
		public static final String ISSUE_TRACKER_URL = "https://github.com/psyGamer/Construct/issues";
		
		public static boolean verifyLibrary = false;
		public static boolean strictMode = true;
	}
	
	public static final class Constants {
		public static final String CONSTRUCT_PACKAGE = "dev.psygamer.construct";
		public static final String CORE_PACKAGE = "dev.psygamer.construct.core";
		public static final String LIBRARY_PACKAGE = CONSTRUCT_PACKAGE + ".lib";
		public static final String IMPLEMENTATION_PACKAGE_ROOT = CONSTRUCT_PACKAGE + ".impl";
		public static final String COMMON_IMPLEMENTATION_PACKAGE = CONSTRUCT_PACKAGE + ".impl.common";
	}
}
