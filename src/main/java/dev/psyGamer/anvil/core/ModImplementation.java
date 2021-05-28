package dev.psyGamer.anvil.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModImplementation {
	public final String MODID;
	public final Object INSTANCE;
	
	private Logger logger;
	
	public ModImplementation(final String modID, final Object instance) {
		this.MODID = modID;
		this.INSTANCE = instance;
		
		this.logger = LogManager.getLogger(this.MODID);
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	
	void setLogger(final Logger logger) {
		this.logger = logger;
	}
}
