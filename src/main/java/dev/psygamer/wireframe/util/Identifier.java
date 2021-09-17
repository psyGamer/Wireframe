package dev.psygamer.wireframe.util;

public class Identifier {
	
	private final String namespace, path;
	
	public Identifier(final String namespace, final String path) {
		this.namespace = namespace;
		this.path = path;
	}
	
	public static Identifier get(final net.minecraft.util.ResourceLocation internal) {
		return new Identifier(internal.getNamespace(), internal.getPath());
	}
	
	public String getNamespace() {
		return this.namespace;
	}
	
	public String getPath() {
		return this.path;
	}
}
