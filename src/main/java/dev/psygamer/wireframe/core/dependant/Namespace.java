package dev.psygamer.wireframe.core.dependant;

import dev.psygamer.wireframe.core.WireframePackages;
import dev.psygamer.wireframe.core.exceptions.setup.WireframeSetupException;

import dev.psygamer.wireframe.util.collection.FreezableArrayList;
import dev.psygamer.wireframe.util.collection.FreezableList;

public final class Namespace {
	
	private static final FreezableList<Namespace> namespaces = new FreezableArrayList<>();
	
	private final String namespace;
	private final String packagePath;
	
	public Namespace(final String packagePath) {
		this("", packagePath);
	}
	
	public Namespace(final String namespace, final String packagePath) {
		this.namespace = namespace;
		this.packagePath = packagePath;
	}
	
	public static Namespace fromPackage(final String packagePath) {
		for (final Namespace namespace : namespaces) {
			if (namespace.packagePath.equalsIgnoreCase(packagePath)) {
				return namespace;
			}
		}
		
		return new Namespace(packagePath);
	}
	
	public static Namespace getCurrent() {
		return fromPackage(WireframePackages.getFirstExternalClass().getPackage().getName());
	}
	
	public String evaluate() {
		if (this.namespace.isEmpty()) {
			try {
				return Dependant.fromNamespace(this).getNamespace();
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
