package dev.psygamer.wireframe.event;

import dev.psygamer.wireframe.Wireframe;

import dev.psygamer.wireframe.event.api.EventBusSubscriber;
import dev.psygamer.wireframe.event.api.ModEventBusSubscriber;

import dev.psygamer.wireframe.util.reflection.ClassUtil;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class EventBusRegistrator {
	
	public static void registerModLoadingEventBuses() {
		getEventClassStream()
				.filter(clazz -> clazz.isAnnotationPresent(ModEventBusSubscriber.class))
				.forEach(clazz -> {
					Wireframe.getMods().forEach(mod -> mod
							.getInternalModEventBus()
							.register(clazz));
					
					Wireframe.LOGGER.info("Added " + clazz + " to the mod event bus");
				});
	}
	
	public static void registerWireframeEventBuses() {
		getEventClassStream()
				.filter(clazz -> clazz.isAnnotationPresent(EventBusSubscriber.class))
				.forEach(clazz -> {
					Wireframe.EVENT_BUS.register(clazz);
					Wireframe.LOGGER.info("Added " + clazz + " to the Wireframe event bus");
				});
	}
	
	private static Stream<Class<?>> getEventClassStream() {
		final List<Class<?>> classes = new ArrayList<>(
				ClassUtil.getClasses(Wireframe.class.getPackage().getName())
		);
		
		Wireframe.getMods().forEach(mod -> classes.addAll(ClassUtil.getClasses(mod.getRootPackage())));
		
		return classes.stream();
	}
}
