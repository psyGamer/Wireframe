package dev.psygamer.wireframe.event.api;

import dev.psygamer.wireframe.event.Event;

public interface IEventListener {
	
	void invoke(Event event);
	
	Event.Priority getPriority();
	
	IEventBus getEventBus();
}
