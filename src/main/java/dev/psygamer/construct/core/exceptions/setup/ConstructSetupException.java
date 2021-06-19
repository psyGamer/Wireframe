package dev.psygamer.construct.core.exceptions.setup;

public class ConstructSetupException extends RuntimeException {
	
	public ConstructSetupException(final String className) {
		super("Something went wrong during the setup of Construct!\n" +
				"Make sure you call Construct.setup\n" +
				"Problematic class: " + className
		);
	}
}
