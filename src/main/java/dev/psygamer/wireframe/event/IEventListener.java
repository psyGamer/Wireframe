package dev.psygamer.wireframe.event;

public interface IEventListener {
	
	void invoke(Event event);
	
	Event.Priority getPriority();
	
	EventBus getEventBus();
}
