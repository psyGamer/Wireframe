package dev.psygamer.wireframe.core.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dev.psygamer.wireframe.core.exceptions.FrameworkException;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

final class ImplementorCache {
	
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
	
	public static LoadingCache<Method, Method> getMethodCache() {
		return methodCache;
	}
	
	public static Method getCachedMethod(final Method apiMethod) {
		try {
			return methodCache.get(apiMethod);
		} catch (final ExecutionException ex) {
			throw new FrameworkException("Could not get implementation method for " + methodCache, ex);
		}
	}
}
