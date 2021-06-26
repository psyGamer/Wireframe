package dev.psygamer.construct.core;

import java.util.Arrays;
import java.util.Objects;

public final class ConstructUtil {
	
	public static Class<?> getFirstExternalClass() {
		try {
			return Class.forName(
					Objects.requireNonNull(
							Arrays.stream(Thread.currentThread().getStackTrace())
									.filter(ConstructUtil::isPartOfLibrary)
									.findFirst()
									.orElse(null)
					).getClassName()
			);
			
			
		} catch (final NullPointerException | ClassNotFoundException ex) {
			return null;
		}
	}
	
	public static boolean isPartOfLibrary(final Class<?> clazz) {
		return isPartOfLibrary(clazz.getName());
	}
	
	public static boolean isPartOfLibrary(final StackTraceElement element) {
		return isPartOfLibrary(element.getClassName());
	}
	
	public static boolean isPartOfLibrary(final String className) {
		return !className.startsWith(ConstructCore.Constants.CONSTRUCT_PACKAGE) &&
				!className.startsWith("cpw") &&
				!className.startsWith("java") &&
				!className.startsWith("sun.reflect") &&
				!className.startsWith("com.mojang") &&
				!className.startsWith("net.minecraft");
	}
}
