package dev.psyGamer.anvil.core.exceptions.setup;

public class AnvilSetupException extends RuntimeException {
	
	public AnvilSetupException(final String className) {
		super("Something went wrong during the setup of Anvil!\n" +
				"Make sure you call Anvil.setup\n" +
				"Problematic class: " + className
		);
	}
}
