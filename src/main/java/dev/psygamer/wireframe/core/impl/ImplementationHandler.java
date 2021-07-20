package dev.psygamer.wireframe.core.impl;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.exceptions.FrameworkException;
import dev.psygamer.wireframe.util.reflection.MethodUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImplementationHandler {
	
	public static <T> T executeSpecificImplementation(final Class<?>[] parameterTypes, final Object... parameters) {
		final MethodCaller caller = new MethodCaller(Arrays.stream(Thread.currentThread().getStackTrace())
				.filter(element ->
						!element.getClassName().startsWith(WireframeCore.Packages.IMPLEMENTATION_PACKAGE_ROOT) &&
								!element.getClassName().startsWith(WireframeCore.Packages.CORE_PACKAGE) &&
								!element.getClassName().startsWith("cpw") &&
								!element.getClassName().startsWith("java") &&
								!element.getClassName().startsWith("sun.reflect") &&
								!element.getClassName().startsWith("com.mojang") &&
								!element.getClassName().startsWith("net.minecraft"))
				.findFirst()
				.orElseThrow(() -> new FrameworkException("Could not find invocation off ImplementationHandler.executeImplementation")), parameterTypes);
		
		if (ImplementationCache.getImplementationMethodCache().containsKey(caller)) {
			return invokeImplementationMethod(ImplementationCache.getImplementationMethodCache().get(caller), parameters, caller);
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
			
			throw new FrameworkException("Could not access " + caller.className + "." + caller.methodName);
		} catch (final InvocationTargetException e) {
			Throwable ex = e;
			
			do { // This is literally the FIRST TIME i used a do-while statement ever.. over 2 years..
				WireframeCore.LOGGER.error("Caused by:", ex);
			} while ((ex = ex.getCause()) != null);
			
			throw new FrameworkException("Could not invoke " + caller.className + "." + caller.methodName);
		}
	}
}
