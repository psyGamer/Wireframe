package dev.psygamer.wireframe.core.impl;

import dev.psygamer.wireframe.core.exceptions.FrameworkException;

public class NoImplementorFoundException extends FrameworkException {
	
	public NoImplementorFoundException(final String className) {
		this(className, null);
	}
	
	public NoImplementorFoundException(final String className, final Throwable cause) {
		super("Could not find implementation for class: " + className, cause);
	}
}
