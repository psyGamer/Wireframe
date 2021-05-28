package dev.psyGamer.anvil.core.exceptions;

import dev.psyGamer.anvil.core.Anvil;

public class LibraryException extends RuntimeException {
	
	public LibraryException(final String errorMessage) {
		super("A fatal exception has occurred in the Anvil library!\n" +
				"Please report this issue under: " + Anvil.Debug.ISSUE_TRACKER_URL +
				"\n\nError message: " + errorMessage);
	}
}
