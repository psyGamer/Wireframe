package dev.psyGamer.anvil.core;

import dev.psyGamer.anvil.core.exceptions.LibraryException;
import dev.psyGamer.anvil.core.version.MinecraftVersion;
import lombok.Getter;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class AnvilCore {
	
	public static final Logger LOGGER = LogManager.getLogger("Anvil");
	
	@Getter
	private static List<ModDefinition<?>> dependants;
	
	public static <T> void registerMod(final T modInstance, final Class<T> modClass) {
		if (!modClass.isAnnotationPresent(Mod.class)) {
			throw new LibraryException("Mod class is not annotated with @Mod");
		}
		
		dependants.add(new ModDefinition<>(
				modClass.getAnnotation(Mod.class).value(),
				modInstance,
				modClass
		));
	}
	
	public static final class Util extends AnvilUtil {
		// Alias for AnvilUtil
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
