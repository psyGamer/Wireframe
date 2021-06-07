package dev.psyGamer.anvil.core.version;

import dev.psyGamer.anvil.core.AnvilCore;
import dev.psyGamer.anvil.core.exceptions.LibraryException;
import dev.psyGamer.anvil.util.reflection.ReflectionUtil;
import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VersionHandler {
	
	public static Object executeOverloadedImplementationMethod(final Class<?>[] paramTypes, final Object... params) {
		final StackTraceElement caller = getCaller();
		
		try {
			final Class<?> libraryClass = Class.forName(caller.getClassName());
			final Class<?> implementationClass = getImplementationClass(caller);
			
			if (!VersionUtil.getSupportedVersions(libraryClass).contains(MinecraftVersion.getCurrentMinecraftVersion())) {
				throw new VersionNotSupportedException(MinecraftVersion.getCurrentMinecraftVersion(), VersionUtil.getSupportedVersions(libraryClass));
			}
			
			final Method implementationMethod = getImplementationMethod(paramTypes, caller, implementationClass, params);
			
			if (!Modifier.isStatic(implementationMethod.getModifiers())) {
				throw new LibraryException("Versioned methods must be static, but is not in: " + caller.getClassName() + "." + caller.getMethodName());
			}
			
			return implementationMethod.invoke(null, params);
			
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
			
			throw new LibraryException("Could not find library implementation for " + caller.getClassName());
		} catch (final NoSuchMethodException e) {
			e.printStackTrace();
			
			throw new LibraryException("Could not find library implementation for " + caller.getClassName() + "." + caller.getMethodName());
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
			
			throw new LibraryException("Could not access " + caller.getClassName() + "." + caller.getMethodName());
		} catch (final InvocationTargetException e) {
			e.printStackTrace();
			
			throw new LibraryException("Could not invoke " + caller.getClassName() + "." + caller.getMethodName());
		}
	}
	
	private static Class<?> getImplementationClass(final StackTraceElement caller) throws ClassNotFoundException {
		final Class<?> libraryClass = Class.forName(caller.getClassName());
		final MinecraftVersion newestVersion = Arrays.stream(MinecraftVersion.getVersionBelow(MinecraftVersion.getCurrentMinecraftVersion()))
				.filter(version -> VersionUtil.getImplementationClass(libraryClass, version) != null)
				.findFirst()
				.orElseThrow(() -> new LibraryException("No implementation found for " + libraryClass));
		
		return VersionUtil.getImplementationClass(libraryClass, newestVersion);
	}
	
	private static Method getImplementationMethod(final Class<?>[] paramTypes, final StackTraceElement caller, final Class<?> implementationClass, final Object[] params) throws NoSuchMethodException {
		if (paramTypes.length > 0) {
			return implementationClass.getMethod(caller.getMethodName(), paramTypes);
		} else {
			final List<Method> possibleImplementationMethods = ReflectionUtil.getMethodsByName(implementationClass, caller.getMethodName())
					.stream().filter(method -> method.getParameterCount() == params.length).collect(Collectors.toList());
			
			if (possibleImplementationMethods.size() == 1) {
				return possibleImplementationMethods.get(0);
			} else {
				throw new LibraryException("Static method overloading with same parameter count and no parameter type definitions.\n" +
						caller.getMethodName() + "#" + caller.getMethodName());
			}
		}
	}
	
	public static StackTraceElement getCaller() {
		return Arrays.stream(Thread.currentThread().getStackTrace())
				.filter(element -> !element.getClassName().equalsIgnoreCase(VersionHandler.class.getName()))
				.findFirst()
				.orElseThrow(() -> new LibraryException("Could not find invocation off VersionHandler.runImplementation"));
	}
	
	public static Object executeImplementation(final Object... params) {
		return executeOverloadedImplementationMethod(getParameterTypes(params), params);
	}
	
	public static Class<?>[] getParameterTypes(final @NonNull Object[] parameters) {
		try {
			return Arrays.stream(parameters)
					.map(Object::getClass)
					.toArray(Class[]::new);
		} catch (final NullPointerException ex) {
			return null;
		}
	}
}
