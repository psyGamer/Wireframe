package dev.psygamer.wireframecore.impl.handle;

import dev.psygamer.wireframe.util.reflection.ClassUtil;
import dev.psygamer.wireframe.util.reflection.MethodUtil;
import dev.psygamer.wireframe.util.reflection.ObjectUtil;

import dev.psygamer.wireframecore.WireframePackages;
import dev.psygamer.wireframecore.impl.ImplementationVersion;
import dev.psygamer.wireframecore.impl.MinecraftVersion;
import dev.psygamer.wireframecore.impl.handle.exceptions.NoInvokerFoundException;
import dev.psygamer.wireframecore.impl.handle.exceptions.NoClassImplementorFoundException;
import dev.psygamer.wireframecore.impl.handle.exceptions.NoMethodImplementorFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public final class Implementor {
	
	private final Method apiMethod;
	
	protected Implementor(final Method apiMethod) {
		if (apiMethod == null) {
			throw new IllegalArgumentException("The API Method method may not be null");
		}
		if (!WireframePackages.isAPIClass(apiMethod.getDeclaringClass())) {
			throw new IllegalArgumentException("The unimplemented method must be in an API class");
		}
		
		this.apiMethod = apiMethod;
	}
	
	public static <R> R execute(final Object... parameters) {
		return Implementor.find(ObjectUtil.getClassTypes(parameters)).run(parameters);
	}
	
	public static Implementor find(final Class<?>... parameterTypes) {
		final StackTraceElement invoker = Arrays.stream(Thread.currentThread().getStackTrace())
				.filter(WireframePackages::isAPIClass)
				.findFirst()
				.orElseThrow(() -> new NoInvokerFoundException("Class part of the Wireframe library classes"));
		
		final Class<?> libraryClass = ClassUtil.getClassFromStackTraceElement(invoker);
		final Method libraryMethod = MethodUtil.getStaticMethod(libraryClass, invoker.getMethodName(), mapToAPIClasses(parameterTypes));
		
		return new Implementor(libraryMethod);
	}
	
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
	
	protected static Method evaluateImplementationMethod(final Method apiMethod) {
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
	
	protected static Stream<Class<?>> getImplementationClassStream(final Class<?> apiClass) {
		return ClassUtil.getClasses(WireframePackages.IMPL_PACKAGE).stream()
				.filter(apiClass::isAssignableFrom)
				.filter(clazz -> clazz.isAnnotationPresent(ImplementationVersion.class))
				.filter(clazz -> MinecraftVersion.getCurrentVersion().compareTo( // Check if the impl version is <= the current version
						clazz.getAnnotation(ImplementationVersion.class).value()) >= 0)
				.sorted((classA, classB) -> { // Sort from newest to newest to oldest
					final MinecraftVersion versionA = classA.getAnnotation(ImplementationVersion.class).value();
					final MinecraftVersion versionB = classB.getAnnotation(ImplementationVersion.class).value();
					
					return versionB.compareTo(versionA);
				});
	}
	
	@SuppressWarnings("unchecked")
	public <R> R run(final Object... parameters) {
		final Method implementedMethod = ImplementorCache.getCachedMethod(this.apiMethod);
		
		try {
			return (R) implementedMethod.invoke(null, parameters);
		} catch (IllegalAccessException | InvocationTargetException ex) {
			throw new NoMethodImplementorFoundException(this.apiMethod, ex);
		}
	}
}
