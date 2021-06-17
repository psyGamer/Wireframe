package dev.psygamer.construct.core;

import dev.psygamer.construct.core.exceptions.LibraryException;
import dev.psygamer.construct.core.version.MinecraftVersion;
import lombok.Getter;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ConstructCore {
	
	public static final Logger LOGGER = LogManager.getLogger("Construct");
	public static final String MODID = "construct";
	
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
	
	public static final class Util extends ConstructUtil {
		// Alias for ConstructUtil
	}
	
	public static final class Debug {
		public static final String ISSUE_TRACKER_URL = "https://github.com/psyGamer/Construct/issues";
		
		public static boolean verifyLibrary = false;
		public static boolean strictMode = true;
	}
	
	public static final class Constants {
		public static final String FERRUS_PACKAGE = "dev.psygamer.construct";
		public static final String LIBRARY_PACKAGE = FERRUS_PACKAGE + ".lib";
		public static final String IMPLEMENTATION_PACKAGE = FERRUS_PACKAGE + ".impl";
		public static final String COMMON_IMPLEMENTATION_PACKAGE = FERRUS_PACKAGE + ".impl.common";
		
		public static String getLibraryImplementationPath(final MinecraftVersion version) {
			return version == MinecraftVersion.COMMON
					? COMMON_IMPLEMENTATION_PACKAGE
					: FERRUS_PACKAGE + ".impl.v" + version.getVersionString().split("\\.")[1];
		}
	}
}
