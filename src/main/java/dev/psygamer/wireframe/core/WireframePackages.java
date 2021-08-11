package dev.psygamer.wireframe.core;

import java.util.Arrays;
import java.util.Objects;

public final class WireframePackages {
	
	public static final String WIREFRAME_PACKAGE = "dev.psygamer.wireframe";
	
	public static final String API_PACKAGE = WIREFRAME_PACKAGE + ".api";
	public static final String CORE_PACKAGE = WIREFRAME_PACKAGE + ".core";
	public static final String IMPL_PACKAGE = WIREFRAME_PACKAGE + ".impl";
	
	public static boolean isAPIClass(final StackTraceElement element) {
		return isAPIClass(element.getClassName());
	}
	
	public static boolean isAPIClass(final Class<?> clazz) {
		return isAPIClass(clazz.getName());
	}
	
	public static boolean isAPIClass(final String className) {
		return className.startsWith(API_PACKAGE);
	}
	
	public static boolean isCoreClass(final StackTraceElement element) {
		return isCoreClass(element.getClassName());
	}
	
	public static boolean isCoreClass(final Class<?> clazz) {
		return isCoreClass(clazz.getName());
	}
	
	public static boolean isCoreClass(final String className) {
		return className.startsWith(CORE_PACKAGE);
	}
	
	public static boolean isImplClass(final StackTraceElement element) {
		return isImplClass(element.getClassName());
	}
	
	public static boolean isImplClass(final Class<?> clazz) {
		return isImplClass(clazz.getName());
	}
	
	public static boolean isImplClass(final String className) {
		return className.startsWith(IMPL_PACKAGE);
	}
	
	public static boolean isInternalClass(final StackTraceElement element) {
		return isInternalClass(element.getClassName());
	}
	
	public static boolean isInternalClass(final Class<?> clazz) {
		return isInternalClass(clazz.getName());
	}
	
	public static boolean isInternalClass(final String className) {
		return className.startsWith(WIREFRAME_PACKAGE);
	}
	
	public static Class<?> getFirstExternalClass() {
		try {
			return Class.forName(
					Objects.requireNonNull(
							Arrays.stream(Thread.currentThread().getStackTrace())
									.filter(element -> !(isInternalClass(element)
											|| element.getClassName().startsWith("cpw.")
											|| element.getClassName().startsWith("java.")
											|| element.getClassName().startsWith("sun.reflect.")
											|| element.getClassName().startsWith("com.mojang.")
											|| element.getClassName().startsWith("net.minecraft.")
											|| element.getClassName().startsWith("net.minecraftforge.")
									))
									.findFirst()
									.orElse(null)
					).getClassName()
			);
		} catch (final NullPointerException | ClassNotFoundException ex) {
			return null;
		}
	}
}
