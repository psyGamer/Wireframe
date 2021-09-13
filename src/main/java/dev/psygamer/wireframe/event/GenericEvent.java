package dev.psygamer.wireframe.event;

import java.lang.reflect.Type;

public abstract class GenericEvent <T> extends Event {
	
	public abstract Type getGenericType();
}
