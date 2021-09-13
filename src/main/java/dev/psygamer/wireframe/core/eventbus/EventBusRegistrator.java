package dev.psygamer.wireframe.core.eventbus;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.WireframePackages;
import dev.psygamer.wireframe.core.event.ModEventBusSubscriber;
import dev.psygamer.wireframe.core.event.WireframeEventBusSubscriber;

import dev.psygamer.wireframe.core.dependant.Dependant;

import dev.psygamer.wireframe.util.reflection.ClassUtil;
import dev.psygamer.wireframe.util.reflection.FieldUtil;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EventBusRegistrator {
	
	public static void registerModLoadingEventBuses() {
		getEventClassStream()
				.filter(clazz -> clazz.isAnnotationPresent(ModEventBusSubscriber.class))
				.forEach(clazz -> {
					Dependant.getDependants().forEach(dependant -> dependant
							.getModLoadingContext()
							.getModEventBus()
							.register(clazz));
					
					WireframeCore.LOGGER.info("Added " + clazz + " to the mod event bus");
				});
	}
	
	public static void registerWireframeEventBuses() {
		getEventClassStream()
				.filter(clazz -> clazz.isAnnotationPresent(WireframeEventBusSubscriber.class))
				.forEach(clazz -> {
					WireframeCore.EVENT_BUS.register(clazz);
					WireframeCore.LOGGER.info("Added " + clazz + " to the Wireframe event bus");
				});
	}
	
	private static String getModID(final FMLJavaModLoadingContext modLoadingContext) {
		return ((FMLModContainer) FieldUtil.getField(FMLJavaModLoadingContext.class, modLoadingContext, "container"))
				.getModId();
	}
	
	private static Stream<Class<?>> getEventClassStream() {
		
		final List<Class<?>> classes = new ArrayList<>(ClassUtil.getClasses(WireframePackages.WIREFRAME_PACKAGE));
		
		for (final Dependant<?> dependant : Dependant.getDependants()) {
			classes.addAll(ClassUtil.getClasses(dependant.getRootPackage()));
		}
		
		return classes.stream();
	}
}
