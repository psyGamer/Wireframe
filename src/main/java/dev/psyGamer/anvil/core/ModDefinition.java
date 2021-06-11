package dev.psyGamer.anvil.core;

public class ModDefinition <T> {
	public final String modID;
	public final String rootPackage;
	
	public final Class<? extends T> modClass;
	public final T modInstance;
	
	public ModDefinition(final String modID, final T modInstance, final Class<? extends T> modClass) {
		this.modID = modID;
		this.rootPackage = modClass.getPackage().getName();
		
		this.modClass = modClass;
		this.modInstance = modInstance;
	}
}
