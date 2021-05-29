package dev.psyGamer.anvil.core.version;

import dev.psyGamer.anvil.core.AnvilCore;
import dev.psyGamer.anvil.core.exceptions.LibraryException;
import dev.psyGamer.anvil.util.reflection.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VersionHandler {
	
	public static Object executeOverloadedVersionedMethod(final Class<?>[] paramTypes, final Object... params) {
		StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
		
		if (caller.getClassName().equalsIgnoreCase(VersionHandler.class.getName())) {
			caller = Thread.currentThread().getStackTrace()[3];
		}
		
		final String implementationClassLocation =
				AnvilCore.Constants.getLibraryImplementationPath() + caller.getClassName().replace(
						AnvilCore.Constants.LIBRARY_PACKAGE, "");
		
		try {
			final Class<?> libraryClass = Class.forName(caller.getClassName());
			final Class<?> implementationClass = Class.forName(implementationClassLocation);
			
			final Method implementationMethod;
			
			if (paramTypes.length > 0) {
				implementationMethod = implementationClass.getMethod(caller.getMethodName(), paramTypes);
			} else {
				final List<Method> possibleImplementationMethods = ReflectionUtil.getMethodsByName(implementationClass, caller.getMethodName())
						.stream().filter(method -> method.getParameterCount() == params.length).collect(Collectors.toList());
				
				if (possibleImplementationMethods.size() == 1) {
					implementationMethod = possibleImplementationMethods.get(0);
				} else {
					throw new LibraryException("Static method overloading with same parameter count and no parameter type definitions.\n" +
							libraryClass + "#" + caller.getMethodName());
				}
			}
			
			if (!Modifier.isStatic(implementationMethod.getModifiers())) {
				throw new LibraryException("Versioned methods must be static, but is not in: " + caller.getClassName() + "." + caller.getMethodName());
			}
			
			return implementationMethod.invoke(null, params);
			
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
			
			throw new LibraryException("Could not find library implementation for " + caller.getClassName() + " in " + implementationClassLocation);
		} catch (final NoSuchMethodException e) {
			e.printStackTrace();
			
			throw new LibraryException("Could not find library implementation for " + caller.getClassName() + "." + caller.getMethodName() + " in " + implementationClassLocation);
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
			
			throw new LibraryException("Could not access " + caller.getClassName() + "." + caller.getMethodName() + " in " + implementationClassLocation);
		} catch (final InvocationTargetException e) {
			e.printStackTrace();
			
			throw new LibraryException("Could not invoke " + caller.getClassName() + "." + caller.getMethodName() + " in " + implementationClassLocation);
		}
	}
	
	public static Object executeVersionedMethod(final Object... params) {
		final Class<?>[] parameterTypes = new Class[params.length];
		
		for (int i = 0 ; i < params.length ; i++) {
			if (params[i] == null) {
				return executeOverloadedVersionedMethod(new Class[0], params);
			}
			
			parameterTypes[i] = params[i].getClass();
		}
		
		return executeOverloadedVersionedMethod(parameterTypes, params);
	}
	
}
