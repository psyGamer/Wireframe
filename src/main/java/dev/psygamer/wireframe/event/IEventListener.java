package dev.psygamer.wireframe.event;

public interface IEventListener {
	
	void invoke(IEvent event);
	
	IEvent.Priority getPriority();
	
	IEventBus getEventBus();
}
