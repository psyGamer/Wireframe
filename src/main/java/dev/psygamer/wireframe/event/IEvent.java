package dev.psygamer.wireframe.event;

public interface IEvent {
	
	enum Priority {
		HIGHEST, HIGH, NORMAL, LOW, LOWEST
	}
	
	boolean isCancelable();
	
	boolean isCanceled();
	
	void setCanceled(boolean canceled);
}
