package dev.psygamer.wireframecore;

import java.util.Arrays;
import java.util.Objects;

public final class WireframeUtil {
	
	public static Class<?> getFirstExternalClass() {
		try {
			return Class.forName(
					Objects.requireNonNull(
							Arrays.stream(Thread.currentThread().getStackTrace())
									.filter(WireframeUtil::isPartOfLibrary)
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
		return !className.startsWith(PackageUtil.ROOT_PACKAGE) &&
				!className.startsWith("cpw") &&
				!className.startsWith("java") &&
				!className.startsWith("sun.reflect") &&
				!className.startsWith("com.mojang") &&
				!className.startsWith("net.minecraft");
	}
	
	public static boolean isImplementationClass(final Class<?> internalClass) {
		return internalClass.getName().startsWith(PackageUtil.IMPLEMENTATION_PACKAGE_ROOT);
	}
	
	public static boolean isLibraryClass(final Class<?> internalClass) {
		return internalClass.getName().startsWith(PackageUtil.API_PACKAGE);
	}
	
	public static boolean isInternalClass(final Class<?> internalClass) {
		return isLibraryClass(internalClass) || isImplementationClass(internalClass);
	}
}
