package dev.psygamer.wireframe.core.exceptions;

import dev.psygamer.wireframe.core.WireframeCore;

public class LibraryException extends RuntimeException {
	
	public LibraryException(final String errorMessage) {
		super("A fatal exception has occurred in the Wireframe Framework!\n" +
				"Please report this issue under: " + WireframeCore.Debug.ISSUE_TRACKER_URL +
				"\n\nError message: " + errorMessage);
	}
}
