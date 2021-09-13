package dev.psygamer.wireframe.event;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public interface IEventBus {
	
	void register(final Object target);
	
	<T extends IEvent> void addListener(final Consumer<T> consumer);
	
	<T extends IEvent> void addListener(final Consumer<T> consumer, final IEvent.Priority priority);
	
	<T extends IEvent> void addListener(final Object instance, final Method method);
	
	<T extends IEvent> void addListener(final Object instance, final Method method, final IEvent.Priority priority);
	
	boolean post(final IEvent event);
}
