package dev.psygamer.wireframe.core;

import dev.psygamer.wireframe.core.dependant.DependantsHandler;
import dev.psygamer.wireframe.core.exceptions.FrameworkException;
import dev.psygamer.wireframe.core.namespace.NamespaceHandler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class WireframeCore {
	
	public static final Logger LOGGER = LogManager.getLogger("Wireframe");
	public static final String MODID = "wireframe";
	
	public static void register(final Class<?> modClass, final FMLJavaModLoadingContext modLoadingContext) {
		if (!modClass.isAnnotationPresent(Mod.class)) {
			throw new FrameworkException("Mod class is not annotated with @Mod");
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
		public static final String ISSUE_TRACKER_URL = "https://github.com/psyGamer/Wireframe/issues";
		
		public static boolean verifyLibrary = false;
		public static boolean strictMode = true;
	}
	
	public static final class Constants {
		public static final String WIREFRAME_PACKAGE = "dev.psygamer.wireframe";
		public static final String CORE_PACKAGE = WIREFRAME_PACKAGE + ".core";
		public static final String LIBRARY_PACKAGE = WIREFRAME_PACKAGE + ".lib";
		public static final String IMPLEMENTATION_PACKAGE_ROOT = WIREFRAME_PACKAGE + ".impl";
		public static final String COMMON_IMPLEMENTATION_PACKAGE = WIREFRAME_PACKAGE + ".impl.common";
	}
}
