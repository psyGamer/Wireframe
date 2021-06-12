package dev.psyGamer.anvil.core;

import dev.psyGamer.anvil.core.exceptions.LibraryException;
import dev.psyGamer.anvil.core.exceptions.setup.AnvilSetupException;

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
				return AnvilCore.Util.getDependant(this.classPath).namespace;
			} catch (final LibraryException ex) {
				throw new AnvilSetupException(this.classPath);
			}
		}
		
		return this.namespace;
	}
}
