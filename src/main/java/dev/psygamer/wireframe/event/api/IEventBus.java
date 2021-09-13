package dev.psygamer.wireframe.event.api;

import dev.psygamer.wireframe.event.Event;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public interface IEventBus {
	
	void register(final Object target);
	
	<T extends Event> void addListener(final Consumer<T> consumer);
	
	<T extends Event> void addListener(final Consumer<T> consumer, final Event.Priority priority);
	
	<T extends Event> void addListener(final Object instance, final Method method);
	
	<T extends Event> void addListener(final Object instance, final Method method, final Event.Priority priority);
	
	boolean post(final Event event);
}
