package dev.psygamer.construct.core.dependant.namespace;

import dev.psygamer.construct.core.dependant.Dependant;
import dev.psygamer.construct.core.dependant.DependantsUtil;
import dev.psygamer.construct.core.exceptions.setup.ConstructSetupException;

public final class Namespace {
	
	final String namespace;
	final String packagePath;
	
	Namespace(final String packagePath) {
		this("", packagePath);
	}
	
	Namespace(final String namespace, final String packagePath) {
		this.namespace = namespace;
		this.packagePath = packagePath;
	}
	
	public String get() {
		if (this.namespace.isEmpty()) {
			try {
				return DependantsUtil.getDependant(this.packagePath).getNamespace();
			} catch (final Dependant.NotFoundException ex) {
				throw new ConstructSetupException(this.packagePath);
			}
		}
		
		return this.namespace;
	}
	
	public static final class NotFoundException extends RuntimeException {
		public NotFoundException(final String packagePath) {
			super("Could not find namespace for package: " + packagePath);
		}
	}
}
