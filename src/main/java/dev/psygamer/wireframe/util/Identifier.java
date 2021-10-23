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
	
	@Override
	public int hashCode() {
		return 31 * this.namespace.hashCode() + this.path.hashCode();
	}
	
	@Override
	public boolean equals(final Object other) {
		if (this == other)
			return true;
		
		if (other == null || getClass() != other.getClass())
			return false;
		
		final Identifier that = (Identifier) other;
		
		return this.namespace.equals(that.namespace) && this.path.equals(that.path);
	}
	
	@Override
	public String toString() {
		return this.namespace + ":" + this.path;
	}
}
