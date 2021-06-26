package dev.psygamer.construct.core.version;

import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.core.exceptions.LibraryException;
import dev.psygamer.construct.util.reflection.ClassUtil;
import dev.psygamer.construct.util.reflection.MethodUtil;

import java.util.stream.Collectors;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public final class ImplementationCache {
	
	public static final Map<MethodCaller, Method> libraryMethodCache = new HashMap<>();
	public static final Map<MethodCaller, Method> directImplementationMethodCache = new HashMap<>();
	public static final Map<Method, Method> implementationMethodCache = new HashMap<>();
	
	public static void generateCache() {
		for (final Class<?> libraryClass : ClassUtil.getClasses(ConstructCore.Constants.LIBRARY_PACKAGE)) {
			for (final Method libraryMethod : libraryClass.getDeclaredMethods()) {
				final MethodCaller caller = getMethodCallerObject(libraryMethod);
				
				libraryMethodCache.put(caller, ImplementationUtil.getLibraryMethod(caller));
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
