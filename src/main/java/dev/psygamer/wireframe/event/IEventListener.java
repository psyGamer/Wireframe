package dev.psygamer.wireframe.event;

import dev.psygamer.wireframe.event.api.Event;
import dev.psygamer.wireframe.event.api.IEventBus;

public interface IEventListener {
	
	void invoke(Event event);
	
	Event.Priority getPriority();
	
	IEventBus getEventBus();
}
