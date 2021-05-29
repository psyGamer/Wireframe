package dev.psyGamer.anvil.core.version;

import dev.psyGamer.anvil.core.AnvilCore;
import dev.psyGamer.anvil.core.exceptions.LibraryException;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class VersionHandler {
	
	public static Object executeVersionedMethod(final Object... params) {
		final StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
		final String implementationClassLocation =
				AnvilCore.Constants.getLibraryImplementationPath() + caller.getClassName().replace(
						AnvilCore.Constants.LIBRARY_PACKAGE, ""
				);
		
		try {
			final Class<?>[] parameterTypes = new Class[params.length];
			System.out.println(Arrays.toString(params));
			for (int i = 0 ; i < params.length ; i++) {
				System.out.println(params[i]);
				parameterTypes[i] = params[i].getClass();
			}
			
			final Class<?> implementationClass = Class.forName(implementationClassLocation);
			final Method implementationMethod = implementationClass.getMethod(caller.getMethodName(), parameterTypes);
			
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
	
}
