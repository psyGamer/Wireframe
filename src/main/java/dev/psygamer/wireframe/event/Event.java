package dev.psygamer.wireframe.event;

public class Event {
	
	public @interface Cancellable {
	}
	
	public enum Priority {
		HIGHEST, HIGH, NORMAL, LOW, LOWEST
	}
}
