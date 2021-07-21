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
		public static boolean verifyLibrary = false;
		public static boolean strictMode = true;
	}
	
	public static final class Packages {
		public static final String ROOT_PACKAGE = "dev.psygamer.wireframe";
		public static final String CORE_PACKAGE = ROOT_PACKAGE + ".core";
		public static final String UTIL_PACKAGE = ROOT_PACKAGE + ".util";
		
		public static final String API_PACKAGE = ROOT_PACKAGE + ".lib";
		public static final String IMPLEMENTATION_PACKAGE_ROOT = ROOT_PACKAGE + ".impl";
		public static final String COMMON_IMPLEMENTATION_PACKAGE = ROOT_PACKAGE + ".impl.common";
		
		public static boolean isAPIClass(final StackTraceElement element) {
			return isAPIClass(element.getClassName());
		}
		
		public static boolean isAPIClass(final Class<?> clazz) {
			return isAPIClass(clazz.getName());
		}
		
		public static boolean isAPIClass(final String className) {
			return className.startsWith(ROOT_PACKAGE) && !(isCoreClass(className) || isUtilClass(className));
		}
		
		public static boolean isCoreClass(final StackTraceElement element) {
			return isCoreClass(element.getClassName());
		}
		
		public static boolean isCoreClass(final Class<?> clazz) {
			return isCoreClass(clazz.getName());
		}
		
		public static boolean isCoreClass(final String className) {
			return className.startsWith(CORE_PACKAGE);
		}
		
		public static boolean isUtilClass(final StackTraceElement element) {
			return isUtilClass(element.getClassName());
		}
		
		public static boolean isUtilClass(final Class<?> clazz) {
			return isUtilClass(clazz.getName());
		}
		
		public static boolean isUtilClass(final String className) {
			return className.startsWith(UTIL_PACKAGE);
		}
	}
}
