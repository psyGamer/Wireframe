package dev.psygamer.wireframecore.event;

import dev.psygamer.wireframe.util.reflection.ClassUtil;

import dev.psygamer.wireframe.util.reflection.FieldUtil;
import dev.psygamer.wireframecore.WireframePackages;

import dev.psygamer.wireframecore.impl.handle.Implementor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class EventRegistrator {
	
	private static final List<FMLJavaModLoadingContext> modLoadingContexts = new ArrayList<>();
	
	public static void addModLoadingContext(final FMLJavaModLoadingContext modLoadingContext) {
		modLoadingContexts.add(modLoadingContext);
	}
	
	public static void registerModLoadingEventBuses() {
		ClassUtil.getClasses(WireframePackages.API_PACKAGE).stream()
				.filter(clazz -> clazz.isAnnotationPresent(ModEventBusSubscriber.class))
				.forEach(clazz -> modLoadingContexts.forEach(modLoadingContext -> {
					try {
						modLoadingContext.getModEventBus().register(Implementor.findClass(clazz)
								.getMethod("create", String.class)
								.invoke(null, getModID(modLoadingContext)));
					} catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
						ex.printStackTrace();
					}
				}));
	}
	
	private static String getModID(final FMLJavaModLoadingContext modLoadingContext) {
		return ((FMLModContainer) FieldUtil.getField(FMLJavaModLoadingContext.class, modLoadingContext, "container"))
				.getModId();
	}
}
