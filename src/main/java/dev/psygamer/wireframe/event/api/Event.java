package dev.psygamer.wireframe.event.api;

public abstract class Event {
	
	public enum Priority {
		HIGHEST, HIGH, NORMAL, LOW, LOWEST
	}
	
	private boolean canceled = false;
	
	public abstract boolean isCancelable();
	
	public boolean isCanceled() {
		return this.canceled;
	}
	
	public void setCanceled(final boolean canceled) {
		if (!isCancelable()) {
			throw new UnsupportedOperationException(
					"Attempted to call Event#setCanceled() on a non-cancelable event of type: "
							+ this.getClass()
								  .getCanonicalName()
			);
		}
		
		this.canceled = canceled;
	}
}
