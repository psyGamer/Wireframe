package dev.psygamer.wireframe.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodEventListener implements IEventListener {
	
	private final Object instance;
	private final Method method;
	
	private final IEvent.Priority priority;
	private final IEventBus eventBus;
	
	public MethodEventListener(final Object instance, final Method method, final IEvent.Priority priority, final IEventBus eventBus) {
		this.instance = instance;
		this.method = method;
		
		this.priority = priority;
		this.eventBus = eventBus;
	}
	
	@Override
	public void invoke(final IEvent event) {
		try {
			this.method.invoke(this.instance, this.method);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public IEvent.Priority getPriority() {
		return this.priority;
	}
	
	@Override
	public IEventBus getEventBus() {
		return this.eventBus;
	}
}
