package dev.psygamer.construct.core.implementation;

import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.core.exceptions.LibraryException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ImplementationHandler {
	
	public static <T> T executeSpecificImplementation(final Class<?>[] parameterTypes, final Object... parameters) {
		final MethodCaller caller = new MethodCaller(Arrays.stream(Thread.currentThread().getStackTrace())
				.filter(element ->
						!element.getClassName().startsWith(ConstructCore.Constants.IMPLEMENTATION_PACKAGE_ROOT) &&
								!element.getClassName().startsWith(ConstructCore.Constants.CORE_PACKAGE) &&
								!element.getClassName().startsWith("cpw") &&
								!element.getClassName().startsWith("java") &&
								!element.getClassName().startsWith("sun.reflect") &&
								!element.getClassName().startsWith("com.mojang") &&
								!element.getClassName().startsWith("net.minecraft"))
				.findFirst()
				.orElseThrow(() -> new LibraryException("Could not find invocation off VersionHandler.runImplementation")), parameterTypes);
		
		if (ImplementationCache.directImplementationMethodCache.containsKey(caller)) {
			return invokeImplementationMethod(ImplementationCache.directImplementationMethodCache.get(caller), parameters, caller);
		}
		
		final Method libraryMethod = ImplementationUtil.getLibraryMethod(caller);
		final Method implementationMethod = ImplementationUtil.getImplementationMethod(libraryMethod);
		
		return invokeImplementationMethod(implementationMethod, parameters, caller);
	}
	
	public static <T> T executeImplementation(final Object... parameters) {
		return executeSpecificImplementation(
				Arrays.stream(parameters)
						.map(parameter -> {
							if (parameter == null) {
								return null;
							}
							
							return parameter.getClass();
						}).toArray(Class[]::new),
				
				parameters
		);
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T invokeImplementationMethod(final Method implementationMethod, final Object[] parameters, final MethodCaller caller) {
		try {
			return (T) implementationMethod.invoke(null, parameters);
		} catch (final IllegalAccessException ex) {
			ex.printStackTrace();
			
			throw new LibraryException("Could not access " + caller.className + "." + caller.methodName);
		} catch (final InvocationTargetException e) {
			Throwable ex = e;
			
			do { // This is literally the FIRST TIME i used a do-while statement ever.. over 2 years..
				ConstructCore.LOGGER.error("Caused by:", ex);
			} while ((ex = ex.getCause()) != null);
			
			throw new LibraryException("Could not invoke " + caller.className + "." + caller.methodName);
		}
	}
}
