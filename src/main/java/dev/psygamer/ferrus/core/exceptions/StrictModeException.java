package dev.psygamer.ferrus.core.exceptions;

import dev.psygamer.ferrus.core.FerrusCore;

public class StrictModeException extends RuntimeException {
	
	private static final String ERROR_MESSAGE_SUFFIX = "" +
			"Please report this issue under: " + FerrusCore.Debug.ISSUE_TRACKER_URL;
	
	public StrictModeException(final String errorMessage) {
		super("\n\n" + errorMessage + "\nThis Exception was caused, because strict mode (Ferrus.Debug.strictMode) was enabled.\n" + ERROR_MESSAGE_SUFFIX);
	}
	
	public static void throwException(final String errorMessage) {
		if (FerrusCore.Debug.strictMode) {
			throw new StrictModeException(errorMessage);
		} else {
			FerrusCore.LOGGER.warn(errorMessage + ERROR_MESSAGE_SUFFIX);
		}
	}
}
