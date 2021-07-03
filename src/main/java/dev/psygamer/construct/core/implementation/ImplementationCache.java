package dev.psygamer.construct.core.implementation;

import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.util.reflection.ClassUtil;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;

public final class ImplementationCache {
	
	public static final Map<MethodCaller, Method> libraryMethodCache = new HashMap<>();
	public static final Map<MethodCaller, Method> directImplementationMethodCache = new HashMap<>();
	public static final Map<Method, Method> implementationMethodCache = new HashMap<>();
	
	public static void generateCache() {
		for (final Class<?> libraryClass : ClassUtil.getClasses(ConstructCore.Constants.LIBRARY_PACKAGE)) {
			for (final Method libraryMethod : libraryClass.getDeclaredMethods()) {
				final MethodCaller caller = getMethodCallerObject(libraryMethod);
				final Method implementationMethod = ImplementationUtil.getImplementationMethod(libraryMethod);
				
				libraryMethodCache.put(caller, libraryMethod);
				directImplementationMethodCache.put(caller, implementationMethod);
				implementationMethodCache.put(libraryMethod, implementationMethod);
			}
		}
	}
	
	public static MethodCaller getMethodCallerObject(final Method libraryMethod) {
		return new MethodCaller(
				libraryMethod.getDeclaringClass().getSimpleName(),
				libraryMethod.getName(),
				libraryMethod.getParameterTypes()
		);
	}
}
