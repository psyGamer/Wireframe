package dev.psygamer.construct.core.exceptions;

import dev.psygamer.construct.core.ConstructCore;

public class LibraryException extends RuntimeException {
	
	public LibraryException(final String errorMessage) {
		super("A fatal exception has occurred in the Construct library!\n" +
				"Please report this issue under: " + ConstructCore.Debug.ISSUE_TRACKER_URL +
				"\n\nError message: " + errorMessage);
	}
}
