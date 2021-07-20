package dev.psygamer.wireframe.core.exceptions;

import dev.psygamer.wireframe.core.WireframeCore;

public class StrictModeException extends RuntimeException {
	
	private static final String ERROR_MESSAGE_SUFFIX = "" +
			"Please report this issue under: " + FrameworkException.ISSUE_TRACKER_URL;
	
	public StrictModeException(final String errorMessage) {
		super("\n\n" + errorMessage + "\nThis Exception was caused, because strict mode (WireframeCore.Debug.strictMode) was enabled.\n" + ERROR_MESSAGE_SUFFIX);
	}
	
	public static void throwException(final String errorMessage) {
		if (WireframeCore.Debug.strictMode) {
			throw new StrictModeException(errorMessage);
		} else {
			WireframeCore.LOGGER.warn(errorMessage + ERROR_MESSAGE_SUFFIX);
		}
	}
}
