package dev.psygamer.wireframecore.namespace;

import dev.psygamer.wireframecore.dependant.Dependant;
import dev.psygamer.wireframecore.dependant.DependantsUtil;
import dev.psygamer.wireframecore.exceptions.setup.WireframeSetupException;

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
