package dev.psygamer.wireframe.event;

import dev.psygamer.wireframe.event.api.Event;
import dev.psygamer.wireframe.event.api.IEventBus;

import java.util.function.Consumer;

public class ConsumerEventListener <T extends Event> implements IEventListener {
	
	private final Consumer<T> consumer;
	
	private final Event.Priority priority;
	private final IEventBus eventBus;
	
	public ConsumerEventListener(final Consumer<T> consumer, final Event.Priority priority, final IEventBus eventBus) {
		this.consumer = consumer;
		
		this.eventBus = eventBus;
		this.priority = priority;
	}
	
	@Override
	public void invoke(final Event event) {
		final T cast = (T) event;
		
		if (!(event.isCancelable() && event.isCanceled()))
			this.consumer.accept(cast);
	}
	
	@Override
	public Event.Priority getPriority() {
		return this.priority;
	}
	
	@Override
	public IEventBus getEventBus() {
		return this.eventBus;
	}
}
