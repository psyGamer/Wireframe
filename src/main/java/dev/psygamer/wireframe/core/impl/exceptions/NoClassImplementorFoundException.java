package dev.psygamer.wireframe.core.impl.exceptions;

import dev.psygamer.wireframe.core.exceptions.FrameworkException;

public class NoClassImplementorFoundException extends FrameworkException {
	
	public NoClassImplementorFoundException(final Class<?> clazz) {
		this(clazz, null);
	}
	
	public NoClassImplementorFoundException(final Class<?> clazz, final Throwable cause) {
		super("Could not find implementation for method: " + clazz, cause);
	}
}
