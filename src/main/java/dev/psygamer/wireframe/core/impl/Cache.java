package dev.psygamer.wireframe.core.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.exceptions.FrameworkException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

public final class Cache {
	
	private static LoadingCache<Method, Method> methodCache = CacheBuilder.newBuilder()
			.maximumSize(300)
			.weigher((Weigher<Method, Method>) (key, value) -> {
				if (key.isAnnotationPresent(StartupOnly.class) && WireframeCore.isStartupComplete()) {
					return Integer.MIN_VALUE;
				}
				
				if (key.isAnnotationPresent(FrequentlyUsed.class)) {
					return 1;
				}
				
				return 0;
			})
			.build(
					new CacheLoader<Method, Method>() {
						
						@Override
						public Method load(final Method key) {
							return Implementor.evaluateImplementationMethod(key);
						}
					}
			);
	
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@interface StartupOnly {
	}
	
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@interface FrequentlyUsed {
	}
	
	protected static LoadingCache<Method, Method> getMethodCache() {
		return methodCache;
	}
	
	protected static Method getCachedMethod(final Method apiMethod) {
		try {
			return methodCache.get(apiMethod);
		} catch (final ExecutionException ex) {
			throw new FrameworkException("Could not get implementation method for " + methodCache, ex);
		}
	}
}
