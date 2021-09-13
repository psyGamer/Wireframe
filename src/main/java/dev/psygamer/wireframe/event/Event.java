package dev.psygamer.wireframe.event;

import java.util.Objects;

public abstract class Event {
	
	private boolean canceled = false;
	private Priority priorityPhase;
	
	public enum Priority {
		HIGHEST, HIGH, NORMAL, LOW, LOWEST
	}
	
	/**
	 * Determine if this function is cancelable at all.
	 *
	 * @return If access to setCanceled should be allowed
	 */
	public abstract boolean isCancelable();
	
	/**
	 * Determine if this event is canceled and should stop executing.
	 *
	 * @return The current canceled state
	 */
	public boolean isCanceled() {
		return this.canceled;
	}
	
	/**
	 * Sets the cancel state of this event. Note, not all events are cancelable, and any attempt to
	 * invoke this method on an event that is not cancelable (as determined by {@link #isCancelable}
	 * will result in an {@link UnsupportedOperationException}.
	 * <p>
	 * The functionality of setting the canceled state is defined on a per-event basis.
	 *
	 * @param cancel The new canceled value
	 */
	public void setCanceled(final boolean cancel) {
		if (!isCancelable()) {
			throw new UnsupportedOperationException(
					"Attempted to call Event#setCanceled() on a non-cancelable event of type: "
							+ this.getClass().getCanonicalName()
			);
		}
		
		this.canceled = cancel;
	}
	
	public Priority getCurrentPriorityPhase() {
		return this.priorityPhase;
	}
	
	public void setCurrentPriorityPhase(final Priority priority) {
		Objects.requireNonNull(priority, "setPhase argument must not be null");
		
		if (getCurrentPriorityPhase().ordinal() >= priority.ordinal())
			throw new IllegalArgumentException("Attempted to set event phase to " + priority + " when already " + getCurrentPriorityPhase());
		
		this.priorityPhase = priority;
	}
}
