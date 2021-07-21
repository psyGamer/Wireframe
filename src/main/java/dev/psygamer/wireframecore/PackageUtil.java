package dev.psygamer.wireframecore;

public final class PackageUtil {
	public static final String ROOT_PACKAGE = "dev.psygamer.wireframe";
	public static final String CORE_PACKAGE = ROOT_PACKAGE + ".core";
	public static final String UTIL_PACKAGE = ROOT_PACKAGE + ".util";
	public static final String IMPL_PACKAGE = CORE_PACKAGE + ".impl";
	
	public static final String API_PACKAGE = ROOT_PACKAGE + ".lib";
	public static final String IMPLEMENTATION_PACKAGE_ROOT = ROOT_PACKAGE + ".impl";
	public static final String COMMON_IMPLEMENTATION_PACKAGE = ROOT_PACKAGE + ".impl.common";
	
	public static boolean isAPIClass(final StackTraceElement element) {
		return isAPIClass(element.getClassName());
	}
	
	public static boolean isAPIClass(final Class<?> clazz) {
		return isAPIClass(clazz.getName());
	}
	
	public static boolean isAPIClass(final String className) {
		return className.startsWith(ROOT_PACKAGE) && !(isCoreClass(className) || isUtilClass(className));
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
}
