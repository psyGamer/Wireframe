package dev.psygamer.construct.core;

import dev.psygamer.construct.core.exceptions.LibraryException;
import dev.psygamer.construct.core.exceptions.setup.ConstructSetupException;

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
				return ConstructCore.Util.getDependant(this.classPath).getNamespace();
			} catch (final LibraryException ex) {
				throw new ConstructSetupException(this.classPath);
			}
		}
		
		return this.namespace;
	}
}
