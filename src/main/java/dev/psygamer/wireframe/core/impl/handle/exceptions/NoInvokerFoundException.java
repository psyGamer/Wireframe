package dev.psygamer.wireframe.core.impl.handle.exceptions;

import dev.psygamer.wireframe.core.exceptions.FrameworkException;

public class NoInvokerFoundException extends FrameworkException {
	
	public NoInvokerFoundException(final String requiredEntryType) {
		super("The stack trace did not contain an entry that was required!\n" +
				"Required entry type: " + requiredEntryType);
	}
}
