package dev.psygamer.wireframecore.impl.handle.exceptions;

import dev.psygamer.wireframecore.exceptions.FrameworkException;

import java.lang.reflect.Method;

public class NoMethodImplementorFoundException extends FrameworkException {
	
	public NoMethodImplementorFoundException(final Method method) {
		this(method, null);
	}
	
	public NoMethodImplementorFoundException(final Method method, final Throwable cause) {
		super("Could not find implementation for method: " + method + " in " + method.getDeclaringClass(), cause);
	}
}
