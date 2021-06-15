package dev.psygamer.ferrus.core.exceptions;

import dev.psygamer.ferrus.core.FerrusCore;

public class LibraryException extends RuntimeException {
	
	public LibraryException(final String errorMessage) {
		super("A fatal exception has occurred in the Ferrus library!\n" +
				"Please report this issue under: " + FerrusCore.Debug.ISSUE_TRACKER_URL +
				"\n\nError message: " + errorMessage);
	}
}
