package dev.psygamer.wireframe.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodEventListener implements IEventListener {
	
	private final Object instance;
	private final Method method;
	
	private final Event.Priority priority;
	private final EventBus eventBus;
	
	public MethodEventListener(final Object instance, final Method method, final Event.Priority priority, final EventBus eventBus) {
		this.instance = instance;
		this.method = method;
		
		this.priority = priority;
		this.eventBus = eventBus;
	}
	
	@Override
	public void invoke(final Event event) {
		try {
			this.method.invoke(this.instance, this.method);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Event.Priority getPriority() {
		return this.priority;
	}
	
	@Override
	public EventBus getEventBus() {
		return this.eventBus;
	}
}
