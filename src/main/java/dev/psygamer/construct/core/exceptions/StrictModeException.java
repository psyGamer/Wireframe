package dev.psygamer.construct.core.exceptions;

import dev.psygamer.construct.core.ConstructCore;

public class StrictModeException extends RuntimeException {
	
	private static final String ERROR_MESSAGE_SUFFIX = "" +
			"Please report this issue under: " + ConstructCore.Debug.ISSUE_TRACKER_URL;
	
	public StrictModeException(final String errorMessage) {
		super("\n\n" + errorMessage + "\nThis Exception was caused, because strict mode (Construct.Debug.strictMode) was enabled.\n" + ERROR_MESSAGE_SUFFIX);
	}
	
	public static void throwException(final String errorMessage) {
		if (ConstructCore.Debug.strictMode) {
			throw new StrictModeException(errorMessage);
		} else {
			ConstructCore.LOGGER.warn(errorMessage + ERROR_MESSAGE_SUFFIX);
		}
	}
}
