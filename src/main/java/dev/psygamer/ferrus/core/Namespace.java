package dev.psygamer.ferrus.core;

import dev.psygamer.ferrus.core.exceptions.LibraryException;
import dev.psygamer.ferrus.core.exceptions.setup.FerrusSetupException;

public class Namespace {
	
	private final String namespace;
	private final String classPath;
	
	Namespace(final String namespace, final String classPath) {
		this.namespace = namespace;
		this.classPath = classPath;
	}
	
	public String get() {
		if (this.namespace.isEmpty()) {
			try {
				return FerrusCore.Util.getDependant(this.classPath).namespace;
			} catch (final LibraryException ex) {
				throw new FerrusSetupException(this.classPath);
			}
		}
		
		return this.namespace;
	}
}
