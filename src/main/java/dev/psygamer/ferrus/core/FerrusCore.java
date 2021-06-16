package dev.psygamer.ferrus.core;

import dev.psygamer.ferrus.core.exceptions.LibraryException;
import dev.psygamer.ferrus.core.version.MinecraftVersion;
import lombok.Getter;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FerrusCore {
	
	public static final Logger LOGGER = LogManager.getLogger("Ferrus");
	public static final String MODID = "ferrus";
	
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
	
	public static final class Util extends FerrusUtil {
		// Alias for FerrusUtil
	}
	
	public static final class Debug {
		public static final String ISSUE_TRACKER_URL = "https://github.com/psyGamer/Ferrus/issues";
		
		public static boolean verifyLibrary = false;
		public static boolean strictMode = true;
	}
	
	public static final class Constants {
		public static final String FERRUS_PACKAGE = "dev.psygamer.ferrus";
		public static final String LIBRARY_PACKAGE = FERRUS_PACKAGE + ".lib";
		
		public static String getLibraryImplementationPath(final MinecraftVersion version) {
			return FERRUS_PACKAGE + ".impl.v" + version.getVersionString().split("\\.")[1];
		}
	}
}
