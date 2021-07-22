package dev.psygamer.wireframecore.impl.handle;

import dev.psygamer.wireframecore.WireframePackages;
import dev.psygamer.wireframecore.exceptions.FrameworkException;
import dev.psygamer.wireframecore.impl.ImplementationVersion;
import dev.psygamer.wireframecore.impl.MinecraftVersion;
import dev.psygamer.wireframecore.impl.handle.exceptions.NoInvokerFoundException;
import dev.psygamer.wireframecore.impl.handle.exceptions.NoMethodImplementorFoundException;

import dev.psygamer.wireframe.util.reflection.ClassUtil;
import dev.psygamer.wireframe.util.reflection.MethodUtil;
import dev.psygamer.wireframe.util.reflection.ObjectUtil;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Arrays;

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
		final Method libraryMethod = MethodUtil.getStaticMethod(libraryClass, invoker.getMethodName(), parameterTypes);
		
		return new Implementor(libraryMethod);
	}
	
	protected static Method evaluateImplementationMethod(final Method apiMethod) {
		final Class<?> apiClass = apiMethod.getDeclaringClass();
		
		return ClassUtil.getClasses(WireframePackages.IMPL_PACKAGE).stream()
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
						apiMethod.getName(),
						apiMethod.getParameterTypes()))
				.filter(Objects::nonNull)
				.findFirst()
				.orElseThrow(() -> new NoMethodImplementorFoundException(apiMethod));
		
	}
	
	@SuppressWarnings("unchecked")
	public <R> R run(final Object... parameters) {
		final Method implementedMethod = Cache.getCachedMethod(this.apiMethod);
		
		try {
			return (R) implementedMethod.invoke(null, parameters);
		} catch (IllegalAccessException | InvocationTargetException ex) {
			throw new NoMethodImplementorFoundException(this.apiMethod, ex);
		}
	}
	
	private static final class Cache {
		private static final LoadingCache<Method, Method> methodCache = CacheBuilder.newBuilder()
				.maximumSize(300)
				.build(
						new CacheLoader<Method, Method>() {
							
							@Override
							public Method load(final Method key) {
								return Implementor.evaluateImplementationMethod(key);
							}
						}
				);
		
		private static LoadingCache<Method, Method> getMethodCache() {
			return methodCache;
		}
		
		private static Method getCachedMethod(final Method apiMethod) {
			try {
				return methodCache.get(apiMethod);
			} catch (final ExecutionException ex) {
				throw new FrameworkException("Could not get implementation method for " + methodCache, ex);
			}
		}
	}
}
