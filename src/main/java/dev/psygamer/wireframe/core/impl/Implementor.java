package dev.psygamer.wireframe.core.impl;

import dev.psygamer.wireframe.api.util.reflection.ClassUtil;
import dev.psygamer.wireframe.api.util.reflection.MethodUtil;
import dev.psygamer.wireframe.api.util.reflection.ObjectUtil;

import dev.psygamer.wireframe.core.WireframePackages;
import dev.psygamer.wireframe.core.impl.exceptions.NoInvokerFoundException;
import dev.psygamer.wireframe.core.impl.exceptions.NoClassImplementorFoundException;
import dev.psygamer.wireframe.core.impl.exceptions.NoMethodImplementorFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>A simple way of executing static implementation methods.</p>
 * <p><strong>Important:</strong> If possible, you should use the {@link Instancer}.</p>
 *
 * @author psyGamer
 * @version 1.0
 * @see Instancer
 * @since 1.0
 */
public final class Implementor {
	
	private final Method apiMethod;
	
	private Implementor(final Method apiMethod) {
		if (apiMethod == null) {
			throw new IllegalArgumentException("The API Method method may not be null");
		}
		if (!WireframePackages.isAPIClass(apiMethod.getDeclaringClass())) {
			throw new IllegalArgumentException("The unimplemented method must be in an API class");
		}
		
		this.apiMethod = apiMethod;
	}
	
	/**
	 * <p>Finds and invokes the implementation method with said parameter types.</p>
	 * <p><strong>Important:</strong> If you have two or more methods with the same name and parameter count,
	 * you should use {@link Implementor#find(Class[]) find} and then {@link Implementor#run(Object[]) run}
	 * to avoid invoking the wrong method.</p>
	 *
	 * @param parameters A class array, representing the parameter types.
	 * @return A new {@link Implementor} instance.
	 * @throws NoInvokerFoundException If this class isn't part of the Wireframe API classes.
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static <R> R execute(final Object... parameters) {
		return Implementor.find(ObjectUtil.getClassTypes(parameters)).run(parameters);
	}
	
	/**
	 * Finds an implementation method with said parameter types.
	 *
	 * @param parameterTypes A class array, representing the parameter types.
	 * @return A new {@link Implementor} instance.
	 * @throws NoInvokerFoundException If this class isn't part of the Wireframe API classes.
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static Implementor find(final Class<?>... parameterTypes) {
		final StackTraceElement invoker = Arrays.stream(Thread.currentThread().getStackTrace())
				.filter(WireframePackages::isAPIClass)
				.findFirst()
				.orElseThrow(() -> new NoInvokerFoundException("Class part of the Wireframe API classes"));
		
		final Class<?> libraryClass = ClassUtil.getClassFromStackTraceElement(invoker);
		final Method libraryMethod = MethodUtil.getStaticMethod(libraryClass, invoker.getMethodName(), mapToAPIClasses(parameterTypes));
		
		return new Implementor(libraryMethod);
	}
	
	/**
	 * Finds an implementation of said class.
	 *
	 * @param apiClass The class of which the implementation should be found.
	 * @return The implementation class.
	 * @throws NoClassImplementorFoundException If no implementation class is found.
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static Class<?> findClass(final Class<?> apiClass) {
		return getImplementationClassStream(apiClass)
				.findFirst()
				.orElseThrow(() -> new NoClassImplementorFoundException(apiClass));
	}
	
	private static Class<?>[] mapToAPIClasses(final Class<?>[] classes) {
		return Arrays.stream(classes)
				.map(clazz -> {
					if (!WireframePackages.isImplClass(clazz)) {
						return clazz;
					}
					
					while (Arrays.stream(clazz.getInterfaces())
							.filter(WireframePackages::isAPIClass)
							.count() != 1
					) {
						clazz = clazz.getSuperclass();
					}
					
					return Arrays.stream(clazz.getInterfaces())
							.filter(WireframePackages::isAPIClass)
							.findFirst()
							.orElse(clazz);
				})
				.toArray(Class[]::new);
	}
	
	static Method evaluateImplementationMethod(final Method apiMethod) {
		final Class<?> apiClass = apiMethod.getDeclaringClass();
		
		return getImplementationClassStream(apiClass)
				.map(clazz -> MethodUtil.getStaticMethod( // Map to implementation method
						clazz,
						apiMethod.getName(),
						apiMethod.getParameterTypes()))
				.filter(Objects::nonNull)
				.findFirst()
				.orElseThrow(() -> new NoMethodImplementorFoundException(apiMethod));
		
	}
	
	private static Stream<Class<?>> getImplementationClassStream(final Class<?> apiClass) {
		return ClassUtil.getClasses(WireframePackages.IMPL_PACKAGE).stream()
				.filter(apiClass::isAssignableFrom)
				.filter(clazz -> clazz.isAnnotationPresent(ImplementationVersion.class))
				.filter(clazz -> MinecraftVersion.getCurrentVersion().compareTo( // Check if the impl version is <= the current version
						clazz.getAnnotation(ImplementationVersion.class).value()) >= 0)
				.sorted((classA, classB) -> { // Sort from newest to oldest
					final MinecraftVersion versionA = classA.getAnnotation(ImplementationVersion.class).value();
					final MinecraftVersion versionB = classB.getAnnotation(ImplementationVersion.class).value();
					
					return versionB.compareTo(versionA);
				});
	}
	
	/**
	 * <p>Invokes the implementation method.</p>
	 *
	 * @param parameters The parameters which will be passed to the implementation method.
	 * @return <p>The return value implementation method returns.</p>
	 * <p><code>null</code> if the return type is <code>void</code>.</p>
	 * @throws NoInvokerFoundException If this class isn't part of the Wireframe API classes.
	 * @version 1.0
	 * @since 1.0
	 */
	@SuppressWarnings("unchecked")
	public <R> R run(final Object... parameters) {
		final Method implementedMethod = ImplementorCache.getCachedMethod(this.apiMethod);
		
		try {
			if (implementedMethod.getReturnType() == Void.TYPE) {
				implementedMethod.invoke(null, parameters);
				
				return null;
			}
			
			return (R) implementedMethod.invoke(null, parameters);
		} catch (IllegalAccessException | InvocationTargetException ex) {
			throw new NoMethodImplementorFoundException(this.apiMethod, ex);
		}
	}
}
