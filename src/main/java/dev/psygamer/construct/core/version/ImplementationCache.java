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

public class ImplementationCache {
	
	public static final Map<MethodCaller, Method> methodCache = new HashMap<>();
	
	private static void generateCache() {
		for (final Class<?> libraryClass : ClassUtil.getClasses(ConstructCore.Constants.LIBRARY_PACKAGE)) {
			for (final Method libraryMethod : libraryClass.getDeclaredMethods()) {
				final MethodCaller caller = getMethodCallerObject(libraryMethod);
				
				methodCache.put(caller, getImplementationMethod(caller));
			}
		}
	}
	
	private static MethodCaller getMethodCallerObject(final Method libraryMethod) {
		return new MethodCaller(
				libraryMethod.getDeclaringClass().getSimpleName(),
				libraryMethod.getName(),
				libraryMethod.getParameterTypes()
		);
	}
	
	private static Method getImplementationMethod(final MethodCaller caller) {
		try {
			final Class<?> libraryClass = Class.forName(caller.className);
			
			List<Method> possibleMethods = MethodUtil.getStaticMethodsByName(
					libraryClass,
					caller.methodName
			);
			
			if (possibleMethods.size() == 1) {
				return possibleMethods.get(0);
			}
			
			possibleMethods = possibleMethods.stream()
					.filter(method -> method.getParameterCount() == caller.parameterTypes.length)
					.collect(Collectors.toList());
			
			if (possibleMethods.size() == 1) {
				return possibleMethods.get(0);
			}
			
			for (final Method possibleMethod : possibleMethods) {
				if (Arrays.equals(possibleMethod.getParameterTypes(), caller.parameterTypes)) {
					return possibleMethod;
				}
			}
		} catch (final ClassNotFoundException ignored) {
		}
		
		throw new LibraryException("oh no");
	}
}
