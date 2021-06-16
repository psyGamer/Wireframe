package dev.psygamer.ferrus.core;

public class ModDefinition <T> {
	public final String namespace;
	public final String rootPackage;
	
	public final Class<? extends T> modClass;
	public final T modInstance;
	
	public ModDefinition(final String namespace, final T modInstance, final Class<? extends T> modClass) {
		this.namespace = namespace;
		this.rootPackage = modClass.getPackage().getName();
		
		this.modClass = modClass;
		this.modInstance = modInstance;
	}
}
