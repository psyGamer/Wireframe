package dev.psygamer.wireframe.core.event;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.dependant.Dependant;
import dev.psygamer.wireframe.core.dependant.DependantsHandler;
import dev.psygamer.wireframe.util.reflection.ClassUtil;

import dev.psygamer.wireframe.util.reflection.FieldUtil;
import dev.psygamer.wireframe.core.WireframePackages;

import dev.psygamer.wireframe.core.impl.Implementor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

import java.lang.reflect.InvocationTargetException;

public class WireframeEventRegistrator {
	
	public static void registerModLoadingEventBuses() {
		ClassUtil.getClasses(WireframePackages.API_PACKAGE).stream()
				.filter(clazz -> clazz.isAnnotationPresent(ModEventBusSubscriber.class))
				.forEach(clazz -> {
					DependantsHandler.getDependants().forEach(dependant -> {
						final FMLJavaModLoadingContext modLoadingContext = dependant.getModLoadingContext();
						
						try {
							modLoadingContext.getModEventBus().register(
									Implementor.findClass(clazz)
											.getMethod("create", String.class)
											.invoke(null, getModID(modLoadingContext))
							);
							
						} catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
							ex.printStackTrace();
						}
					});
					
					WireframeCore.LOGGER.info("Added " + clazz + " to the mod event bus");
				});
	}
	
	public static void registerWireframeEventBuses() {
		registerClassesToWireframeEventBus(WireframePackages.WIREFRAME_PACKAGE);
		
		for (final Dependant<?> dependant : DependantsHandler.getDependants()) {
			registerClassesToWireframeEventBus(dependant.getRootPackage());
		}
	}
	
	private static String getModID(final FMLJavaModLoadingContext modLoadingContext) {
		return ((FMLModContainer) FieldUtil.getField(FMLJavaModLoadingContext.class, modLoadingContext, "container"))
				.getModId();
	}
	
	private static void registerClassesToWireframeEventBus(final String packageName) {
		ClassUtil.getClasses(packageName).stream()
				.filter(clazz -> clazz.isAnnotationPresent(WireframeEventBusSubscriber.class))
				.forEach(clazz -> {
					WireframeCore.EVENT_BUS.register(clazz);
					WireframeCore.LOGGER.info("Added " + clazz + " to the Wireframe event bus");
				});
	}
}
