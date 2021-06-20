package dev.psygamer.construct.core;

import dev.psygamer.construct.core.exceptions.LibraryException;

import java.util.Arrays;

public class ConstructUtil {
	public static ModDefinition<?> getDependant(final String classPath) {
		for (final ModDefinition<?> dependant : ConstructCore.getDependants()) {
			if (classPath.startsWith(dependant.rootPackage)) {
				return dependant;
			}
		}
		
		throw new LibraryException("Couldn't find dependant for class " + classPath);
	}
	
	/**
	 * <p>Searches the StackTrace of the current Thread for the first non internal class, and gets the corresponding mod definition.</p>
	 * <p><strong>Note: </strong>Should only be used in methods that are directly call by the dependant.</p>
	 * <p>
	 *
	 * @return The mod definition of the current mod.
	 */
	public static ModDefinition<?> getCurrentDependant() {
		return getDependant(getFirstExternalClass());
	}
	
	public static Namespace getNamespace(final String classPath) {
		try {
			return new Namespace(getDependant(classPath).namespace, "");
		} catch (final LibraryException ex) {
			return new Namespace("", classPath);
		}
	}
	
	public static Namespace getCurrentNamespace() {
		return getNamespace(getFirstExternalClass());
	}
	
	public static String getFirstExternalClass() {
		return Arrays.stream(Thread.currentThread().getStackTrace())
				.filter(ConstructUtil::isPartOfLibrary)
				.findFirst()
				.orElseThrow(() -> new LibraryException("Could not find external class in stack trace"))
				.getClassName();
	}
	
	public static boolean isPartOfLibrary(final StackTraceElement element) {
		return isPartOfLibrary(element.getClassName());
	}
	
	public static boolean isPartOfLibrary(final String className) {
		return !className.startsWith(ConstructCore.Constants.CONSTRUCT_PACKAGE) &&
				!className.startsWith("net.minecraft") &&
				!className.startsWith("net.minecraftforge");
	}
}
