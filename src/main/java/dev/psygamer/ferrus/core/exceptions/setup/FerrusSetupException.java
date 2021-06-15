package dev.psygamer.ferrus.core.exceptions.setup;

public class FerrusSetupException extends RuntimeException {
	
	public FerrusSetupException(final String className) {
		super("Something went wrong during the setup of Ferrus!\n" +
				"Make sure you call Ferrus.setup\n" +
				"Problematic class: " + className
		);
	}
}
