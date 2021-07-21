package dev.psygamer.wireframecore;

import dev.psygamer.wireframecore.dependant.DependantsHandler;
import dev.psygamer.wireframecore.exceptions.FrameworkException;
import dev.psygamer.wireframecore.namespace.NamespaceHandler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class WireframeCore {
	
	public static final Logger LOGGER = LogManager.getLogger("Wireframe");
	public static final String MODID = "wireframe";
	
	public static boolean isStartupComplete() {
//		return FMLJavaModLoadingContext.get().getModEventBus().
		return true;
	}
	
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
}
