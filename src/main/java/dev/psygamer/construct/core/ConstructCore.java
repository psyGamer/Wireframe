package dev.psygamer.construct.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConstructCore {
	
	public static final Logger LOGGER = LogManager.getLogger("Construct");
	public static final String MODID = "construct";
	
	public static final class Debug {
		public static final String ISSUE_TRACKER_URL = "https://github.com/psyGamer/Construct/issues";
		
		public static boolean verifyLibrary = false;
		public static boolean strictMode = true;
	}
	
	public static final class Constants {
		public static final String CONSTRUCT_PACKAGE = "dev.psygamer.construct";
		public static final String CORE_PACKAGE = "dev.psygamer.construct.core";
		public static final String LIBRARY_PACKAGE = CONSTRUCT_PACKAGE + ".lib";
		public static final String IMPLEMENTATION_PACKAGE_ROOT = CONSTRUCT_PACKAGE + ".impl";
		public static final String COMMON_IMPLEMENTATION_PACKAGE = CONSTRUCT_PACKAGE + ".impl.common";
	}
}
