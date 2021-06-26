package dev.psygamer.construct.core;

import dev.psygamer.construct.core.exceptions.LibraryException;
import lombok.Getter;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ConstructCore {
	
	public static final Logger LOGGER = LogManager.getLogger("Construct");
	public static final String MODID = "construct";
	
	@Getter
	private static final List<ModDefinition<?>> dependants = new ArrayList<>();
	
	public static <T> void registerMod(final Class<T> modClass, final FMLJavaModLoadingContext modLoadingContext) {
		if (!modClass.isAnnotationPresent(Mod.class)) {
			throw new LibraryException("Mod class is not annotated with @Mod");
		}
		
		dependants.add(new ModDefinition<>(
				modClass,
				modLoadingContext
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
		public static final String CONSTRUCT_PACKAGE = "dev.psygamer.construct";
		public static final String LIBRARY_PACKAGE = CONSTRUCT_PACKAGE + ".lib";
		public static final String IMPLEMENTATION_PACKAGE_ROOT = CONSTRUCT_PACKAGE + ".impl";
		public static final String COMMON_IMPLEMENTATION_PACKAGE = CONSTRUCT_PACKAGE + ".impl.common";
	}
}
