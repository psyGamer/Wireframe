package dev.psygamer.wireframe.event;

public abstract class Event implements IEvent {
	
	private boolean canceled = false;
	
	@Override
	public boolean isCanceled() {
		return this.canceled;
	}
	
	@Override
	public void setCanceled(final boolean canceled) {
		if (!isCancelable()) {
			throw new UnsupportedOperationException(
					"Attempted to call Event#setCanceled() on a non-cancelable event of type: "
							+ this.getClass().getCanonicalName()
			);
		}
		
		this.canceled = canceled;
	}
}
