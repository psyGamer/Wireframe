package dev.psygamer.wireframe.core.impl;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.util.reflection.ClassUtil;
import dev.psygamer.wireframe.util.reflection.MethodUtil;
import dev.psygamer.wireframe.util.reflection.ObjectUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class Implementor {
	
	private final Method apiMethod;
	
	protected Implementor(final Method apiMethod) {
		if (apiMethod == null) {
			throw new IllegalArgumentException("The API Method method may not be null");
		}
		if (!WireframeCore.Packages.isAPIClass(apiMethod.getDeclaringClass())) {
			throw new IllegalArgumentException("The unimplemented method must be in an API class");
		}
		
		this.apiMethod = apiMethod;
	}
	
	public static <R> R execute(final Object... parameters) {
		return Implementor.find(ObjectUtil.getClassTypes(parameters)).run(parameters);
	}
	
	public static Implementor find(final Class<?>... parameterTypes) {
		final StackTraceElement invoker = Arrays.stream(Thread.currentThread().getStackTrace())
				.filter(WireframeCore.Packages::isAPIClass)
				.findFirst()
				.orElseThrow(() -> new NoInvokerFoundException("Class part of the Wireframe library classes"));
		
		final Class<?> libraryClass = ClassUtil.getClassFromStackTraceElement(invoker);
		final Method libraryMethod = MethodUtil.getStaticMethod(libraryClass, invoker.getMethodName(), parameterTypes);
		
		return new Implementor(libraryMethod);
	}
	
	@SuppressWarnings("unchecked")
	public <R> R run(final Object... parameters) {
		final Method implementedMethod = evaluateImplementationMethod();
		
		try {
			return (R) implementedMethod.invoke(null, parameters);
		} catch (IllegalAccessException | InvocationTargetException ex) {
			throw new NoMethodImplementorFoundException(this.apiMethod, ex);
		}
	}
	
	/* TODO Add caching system */
	private Method evaluateImplementationMethod() {
		final Class<?> apiClass = this.apiMethod.getDeclaringClass();
		
		return ClassUtil.getClasses(WireframeCore.Packages.IMPL_PACKAGE).stream()
				.filter(clazz -> clazz.isAssignableFrom(apiClass))
				.filter(clazz -> clazz.isAnnotationPresent(ImplementationVersion.class))
				.filter(clazz -> MinecraftVersion.getCurrentVersion().compareTo( // Check if the impl version is <= the current version
						clazz.getAnnotation(ImplementationVersion.class).value()) <= 0)
				.sorted((classA, classB) -> { // Sort from newest to newest to oldest
					final MinecraftVersion versionA = classA.getAnnotation(ImplementationVersion.class).value();
					final MinecraftVersion versionB = classB.getAnnotation(ImplementationVersion.class).value();
					
					return versionA.compareTo(versionB);
				})
				.map(clazz -> MethodUtil.getStaticMethod( // Map to implementation method
						clazz,
						this.apiMethod.getName(),
						this.apiMethod.getParameterTypes()))
				.filter(Objects::nonNull)
				.findFirst()
				.orElseThrow(() -> new NoMethodImplementorFoundException(this.apiMethod));
		
	}
}
