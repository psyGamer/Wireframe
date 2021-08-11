package dev.psygamer.wireframe.core.impl;

import dev.psygamer.wireframe.api.util.reflection.ClassUtil;
import dev.psygamer.wireframe.core.WireframePackages;
import dev.psygamer.wireframe.core.impl.exceptions.NoInvokerFoundException;
import dev.psygamer.wireframe.core.exceptions.FrameworkException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.function.Predicate;

public class Instancer {
	
	public static <T> T createInstance() {
		return invokeConstructorWithDefaultParameters(
				findConstructor(
						findCallingClass()
				)
		);
	}
	
	private static <T> Class<T> findCallingClass() {
		final StackTraceElement caller = Arrays.stream(Thread.currentThread().getStackTrace())
				.filter(WireframePackages::isAPIClass)
				.findFirst()
				.orElseThrow(() -> new NoInvokerFoundException("Class part of the Wireframe API classes"));
		
		return ClassUtil.getClassFromStackTraceElement(caller);
	}
	
	private static <T> Constructor<T> findConstructor(final Class<T> parentClass) {
		final Constructor<T> annotatedConstructor =
				getConstructorWithPredicate(parentClass, constructor -> constructor.isAnnotationPresent(InstanceConstructor.class));
		
		if (annotatedConstructor != null) {
			return annotatedConstructor;
		}
		
		final Constructor<T> publicConstructor =
				getConstructorWithPredicate(parentClass, constructor -> Modifier.isPublic(constructor.getModifiers()));
		
		if (publicConstructor != null) {
			return publicConstructor;
		}
		
		final Constructor<T> protectedConstructor =
				getConstructorWithPredicate(parentClass, constructor -> Modifier.isProtected(constructor.getModifiers()));
		
		if (protectedConstructor != null) {
			return protectedConstructor;
		}
		
		final Constructor<T> defaultConstructor =
				getConstructorWithPredicate(parentClass, constructor -> !Modifier.isPrivate(constructor.getModifiers()) &&
						!Modifier.isProtected(constructor.getModifiers()) &&
						!Modifier.isPublic(constructor.getModifiers())
				);
		
		if (defaultConstructor != null) {
			return defaultConstructor;
		}
		
		final Constructor<T> privateConstructor =
				getConstructorWithPredicate(parentClass, constructor -> Modifier.isPrivate(constructor.getModifiers()));
		
		if (privateConstructor != null) {
			return privateConstructor;
		}
		
		throw new FrameworkException("Could not find a constructor in " + parentClass);
	}
	
	@SuppressWarnings("unchecked")
	private static <T> Constructor<T> getConstructorWithPredicate(final Class<T> parentClass, final Predicate<Constructor<T>> constructorPredicate) {
		return Arrays.stream((Constructor<T>[]) parentClass.getConstructors())
				.filter(constructorPredicate)
				.findFirst()
				.orElse(null);
	}
	
	private static <T> T invokeConstructorWithDefaultParameters(final Constructor<T> constructor) {
		final Class<?>[] parameterTypes = constructor.getParameterTypes();
		final Object[] parameters = new Object[parameterTypes.length];
		
		for (int i = 0 ; i < parameterTypes.length ; i++) {
			final Class<?> parameterType = parameterTypes[i];
			
			if (parameterType == Byte.TYPE)
				parameters[i] = (byte) 0;
			else if (parameterType == Short.TYPE)
				parameters[i] = (short) 0;
			else if (parameterType == Integer.TYPE)
				parameters[i] = 0;
			else if (parameterType == Long.TYPE)
				parameters[i] = 0L;
			else if (parameterType == Float.TYPE)
				parameters[i] = 0F;
			else if (parameterType == Double.TYPE)
				parameters[i] = 0D;
			else if (parameterType == Character.TYPE)
				parameters[i] = '\u0000';
			else if (parameterType == Boolean.TYPE)
				parameters[i] = false;
			else
				parameters[i] = null;
			
		}
		
		constructor.setAccessible(true);
		
		try {
			return constructor.newInstance(parameters);
		} catch (InstantiationException | InvocationTargetException | IllegalAccessException ex) {
			throw new FrameworkException("Could not create instance of " + constructor.getDeclaringClass());
		}
	}
}
