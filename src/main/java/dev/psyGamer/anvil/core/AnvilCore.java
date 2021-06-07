package dev.psyGamer.anvil.core;

import dev.psyGamer.anvil.core.version.MinecraftVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnvilCore {
	
	public static final Logger LOGGER = LogManager.getLogger("Anvil");
	
	private static ModImplementation modImplementation;
	
	/**
	 * Sets up the Anvil Library.
	 * <p>
	 * Should be called before any other library access.
	 * <p>
	 * <p>
	 * <strong>Important:</strong> Only the Debug Flags must be set before!
	 */
	public static void setup(final String modID, final Object modInstance) {
		AnvilCore.modImplementation = new ModImplementation(modID, modInstance);
	}
	
	public static ModImplementation getModImplementation() {
		return modImplementation;
	}
	
	public static final class Debug {
		public static final String ISSUE_TRACKER_URL = "https://github.com/psyGamer/Anvil/issues";
		
		public static boolean verifyLibrary = false;
		public static boolean strictMode = true;
	}
	
	public static final class Constants {
		public static final String ANVIL_PACKAGE = "dev.psyGamer.anvil";
		public static final String LIBRARY_PACKAGE = ANVIL_PACKAGE + ".lib";
		
		public static String getLibraryImplementationPath(final MinecraftVersion version) {
			return ANVIL_PACKAGE + ".impl.v" + version.getVersionString().split("\\.")[1];
		}
	}
}
