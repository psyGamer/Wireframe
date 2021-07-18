package dev.psygamer.wireframe.core.exceptions.setup;

public class WireframeSetupException extends RuntimeException {
	
	public WireframeSetupException(final String className) {
		super("Something went wrong during the setup of the Wireframe Framework!\n" +
				"Make sure you call WireframeCore.register\n" +
				"Problematic class: " + className
		);
	}
}
