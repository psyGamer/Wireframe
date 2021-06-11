package dev.psyGamer.anvil.core;

public class ModImplementation <T> {
	public final String modID;
	public final T modInstance;
	public final Class<? extends T> modClass;
	
	public ModImplementation(final String modID, final T modInstance, final Class<? extends T> modClass) {
		this.modID = modID;
		this.modInstance = modInstance;
		this.modClass = modClass;
	}
}
