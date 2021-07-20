package dev.psygamer.wireframe.core.exceptions;

public class FrameworkException extends RuntimeException {
	
	public static final String ISSUE_TRACKER_URL = "https://github.com/psyGamer/Wireframe/issues";
	
	public FrameworkException(final String errorMessage) {
		super(errorMessage + "\nPlease report this issue under: " + ISSUE_TRACKER_URL);
	}
}
