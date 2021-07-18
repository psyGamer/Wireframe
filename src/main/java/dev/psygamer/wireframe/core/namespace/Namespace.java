package dev.psygamer.wireframe.core.namespace;

import dev.psygamer.wireframe.core.dependant.Dependant;
import dev.psygamer.wireframe.core.dependant.DependantsUtil;
import dev.psygamer.wireframe.core.exceptions.setup.WireframeSetupException;

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
	
	public String evaluate() {
		if (this.namespace.isEmpty()) {
			try {
				return DependantsUtil.getDependant(this).getNamespace();
			} catch (final Dependant.NotFoundException ex) {
				throw new WireframeSetupException(this.packagePath);
			}
		}
		
		return this.namespace;
	}
	
	public String getNamespace() {
		return this.namespace;
	}
	
	public String getPackagePath() {
		return this.packagePath;
	}
	
	public static final class NotFoundException extends RuntimeException {
		public NotFoundException(final String packagePath) {
			super("Could not find namespace for package: " + packagePath);
		}
	}
}
