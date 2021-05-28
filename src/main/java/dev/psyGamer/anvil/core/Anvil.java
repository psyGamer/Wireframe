package dev.psyGamer.anvil.core;

import dev.psyGamer.anvil.core.util.HasStaticMember;
import dev.psyGamer.anvil.core.util.common.ReflectionUtil;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Anvil {
	
	/**
	 * This is the first initialization event. Register tile entities here.
	 * The registry events below will have fired prior to entry to this method.
	 */
	public static void preInit(final FMLPreInitializationEvent event) {
		modImplementation.setLogger(event.getModLog());
	}
	
	/**
	 * This is the second initialization event. Register custom recipes
	 */
	public static void init(final FMLInitializationEvent event) {
	
	}
	
	/**
	 * This is the final initialization event. Register actions from other mods here
	 */
	public static void postInit(final FMLPostInitializationEvent event) {
	}
	
	public static Logger getLogger() {
		return logger;
	}
	
	public static ModImplementation getModImplementation() {
		return modImplementation;
	}
	
	public static final class Debug {
		public static final String ISSUE_TRACKER_URL = "https://github.com/psyGamer/Anvil/issues/new";
		
		public static boolean verifyLibrary = false;
		public static boolean strictMode = true;
	}
	
	public static final class Constants {
		public static final String LIBRARY_PACKAGE_PATH = "dev.psyGamer.anvil.lib";
	}
}
