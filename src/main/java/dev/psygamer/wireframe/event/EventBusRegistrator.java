package dev.psygamer.wireframe.event;

import dev.psygamer.wireframe.Wireframe;
import dev.psygamer.wireframe.event.api.EventBusSubscriber;
import dev.psygamer.wireframe.event.api.ModEventBusSubscriber;
import dev.psygamer.wireframe.util.reflection.ClassUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EventBusRegistrator {
	
	public static void registerModLoadingEventBuses() {
		getEventClassStream()
				.filter(clazz -> clazz.isAnnotationPresent(ModEventBusSubscriber.class)
				)
				.forEach(clazz -> {
					try {
						Wireframe.getMods()
								 .forEach(mod -> {
									 final net.minecraftforge.eventbus.api.IEventBus modEventBus = mod.getInternalModEventBus();
							
									 try {
										 final Method creatorMethod = clazz.getDeclaredMethod("createInstance", String.class);
										 final Object classInstance = creatorMethod.invoke(null, mod.getModID());
								
										 modEventBus.register(classInstance);
								
									 } catch (final NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
										 throw new IllegalStateException(); // Break out of the forEach loop
									 }
								 });
					} catch (final IllegalStateException ignored) {
					} // A hacky way of breaking from Stream#forEach()
					
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
		final List<Class<?>> classes = new ArrayList<>();
		
		Wireframe.getMods()
				 .forEach(mod -> classes.addAll(ClassUtil.getClasses(mod.getRootPackage())));
		
		return classes.stream();
	}
}
