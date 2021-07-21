package dev.psygamer.wireframecore.impl.handle.exceptions;

import dev.psygamer.wireframecore.exceptions.FrameworkException;

public class NoInvokerFoundException extends FrameworkException {
	
	public NoInvokerFoundException(final String requiredEntryType) {
		super("The stack trace did not contain an entry that was required!\n" +
				"Required entry type: " + requiredEntryType);
	}
}
