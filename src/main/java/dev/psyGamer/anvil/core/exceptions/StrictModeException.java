package dev.psyGamer.anvil.core.exceptions;

import dev.psyGamer.anvil.core.AnvilCore;

public class StrictModeException extends RuntimeException {
	
	private static final String ERROR_MESSAGE_SUFFIX = "" +
			"Please report this issue under: " + AnvilCore.Debug.ISSUE_TRACKER_URL;
	
	public StrictModeException(final String errorMessage) {
		super("\n\n" + errorMessage + "\nThis Exception was caused, because strict mode (Anvil.Debug.strictMode) was enabled.\n" + ERROR_MESSAGE_SUFFIX);
	}
	
	public static void throwException(final String errorMessage) {
		if (AnvilCore.Debug.strictMode) {
			throw new StrictModeException(errorMessage);
		} else {
			AnvilCore.LOGGER.warn(errorMessage + ERROR_MESSAGE_SUFFIX);
		}
	}
}
