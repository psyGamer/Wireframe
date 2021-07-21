package dev.psygamer.wireframecore.exceptions;

public class FrameworkException extends RuntimeException {
	
	public static final String ISSUE_TRACKER_URL = "https://github.com/psyGamer/Wireframe/issues";
	
	public FrameworkException(final String errorMessage) {
		this(errorMessage, null);
	}
	
	public FrameworkException(final String errorMessage, final Throwable cause) {
		super(errorMessage + "\nPlease report this issue under: " + ISSUE_TRACKER_URL, cause);
	}
}
