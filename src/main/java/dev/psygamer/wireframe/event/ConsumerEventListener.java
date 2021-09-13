package dev.psygamer.wireframe.event;

import java.util.function.Consumer;

public class ConsumerEventListener <T extends IEvent> implements IEventListener {
	
	private final Consumer<T> consumer;
	
	private final IEvent.Priority priority;
	private final IEventBus eventBus;
	
	public ConsumerEventListener(final Consumer<T> consumer, final IEvent.Priority priority, final IEventBus eventBus) {
		this.consumer = consumer;
		
		this.eventBus = eventBus;
		this.priority = priority;
	}
	
	@Override
	public void invoke(final IEvent event) {
		final T cast = (T) event;
		
		if (!(event.isCancelable() && event.isCanceled()))
			this.consumer.accept(cast);
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
