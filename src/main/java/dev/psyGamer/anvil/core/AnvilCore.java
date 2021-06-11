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
	
	public static <T> void registerMod(final Class<T> modClass) throws InstantiationException, IllegalAccessException {
		if (!modClass.isAnnotationPresent(Mod.class)) {
			throw new LibraryException("Mod class is not annotated with @Mod");
		}
		
		dependants.add(new ModDefinition<>(
				modClass.getAnnotation(Mod.class).value(),
				modClass.newInstance(),
				modClass
		));
	}
	
	public static final class Util {
		/**
		 * <p>Searches the StackTrace of the current Thread for the first non internal class, and gets the corresponding mod definition.</p>
		 * <p>
		 *
		 * @return The mod definition of the current mod.
		 * @apiNote Should only be used in methods that are directly call by the dependant.
		 */
		public static ModDefinition<?> getCurrentDependant() {
			final String callingClassName = Arrays.stream(Thread.currentThread().getStackTrace())
					.filter(element -> !element.getClassName().startsWith(Constants.ANVIL_PACKAGE))
					.findFirst()
					.orElseThrow(() -> new LibraryException("Could not find external class in stack trace"))
					.getClassName();
			
			for (final ModDefinition<?> dependant : dependants) {
				if (callingClassName.startsWith(dependant.rootPackage)) {
					return dependant;
				}
			}
			
			throw new LibraryException("Couldn't find dependant for class " + callingClassName);
		}
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
