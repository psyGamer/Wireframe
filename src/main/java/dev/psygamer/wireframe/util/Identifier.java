package dev.psygamer.wireframe.util;

public class Identifier {
	
	private final String namespace, path;
	
	public Identifier(final String namespace, final String path) {
		this.namespace = namespace;
		this.path = path;
	}
	
	public String getNamespace() {
		return this.namespace;
	}
	
	public String getPath() {
		return this.path;
	}
}
