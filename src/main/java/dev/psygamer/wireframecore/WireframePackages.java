package dev.psygamer.wireframecore;

import java.util.Arrays;
import java.util.Objects;

public final class WireframePackages {
	
	public static final String API_PACKAGE = "dev.psygamer.wireframe";
	public static final String CORE_PACKAGE = "dev.psygamer.wireframecore";
	
	public static final String UTIL_PACKAGE = API_PACKAGE + ".util";
	public static final String IMPL_PACKAGE = CORE_PACKAGE + ".impl";
	
	public static boolean isAPIClass(final StackTraceElement element) {
		return isAPIClass(element.getClassName());
	}
	
	public static boolean isAPIClass(final Class<?> clazz) {
		return isAPIClass(clazz.getName());
	}
	
	public static boolean isAPIClass(final String className) {
		return className.startsWith(API_PACKAGE) && !isCoreClass(className);
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
	
	public static boolean isUtilClass(final StackTraceElement element) {
		return isUtilClass(element.getClassName());
	}
	
	public static boolean isUtilClass(final Class<?> clazz) {
		return isUtilClass(clazz.getName());
	}
	
	public static boolean isUtilClass(final String className) {
		return className.startsWith(UTIL_PACKAGE);
	}
	
	public static boolean isImplClass(final StackTraceElement element) {
		return isImplClass(element.getClassName());
	}
	
	public static boolean isImplClass(final Class<?> clazz) {
		return isImplClass(clazz.getName());
	}
	
	public static boolean isImplClass(final String className) {
		return className.startsWith(UTIL_PACKAGE);
	}
	
	public static boolean isInternalClass(final StackTraceElement element) {
		return isInternalClass(element.getClassName());
	}
	
	public static boolean isInternalClass(final Class<?> clazz) {
		return isInternalClass(clazz.getName());
	}
	
	public static boolean isInternalClass(final String className) {
		return isAPIClass(className) || isCoreClass(className);
	}
	
	public static Class<?> getFirstExternalClass() {
		try {
			return Class.forName(
					Objects.requireNonNull(
							Arrays.stream(Thread.currentThread().getStackTrace())
									.filter(WireframePackages::isInternalClass)
									.findFirst()
									.orElse(null)
					).getClassName()
			);
		} catch (final NullPointerException | ClassNotFoundException ex) {
			return null;
		}
	}
}
