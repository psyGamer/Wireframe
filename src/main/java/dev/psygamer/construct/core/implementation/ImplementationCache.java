package dev.psygamer.construct.core.implementation;

import com.mojang.datafixers.util.Pair;
import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.core.ConstructUtil;
import dev.psygamer.construct.core.exceptions.LibraryException;
import dev.psygamer.construct.util.reflection.ClassUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public final class ImplementationCache {
	
	public static final Map<MethodCaller, Method> libraryMethodCache = new HashMap<>();
	public static final Map<MethodCaller, Method> directImplementationMethodCache = new HashMap<>();
	public static final Map<Method, Method> LEGACYimplementationMethodCache = new HashMap<>();
	
	public static final Map<MinecraftVersion, List<Class<?>>> internalClassCache = new HashMap<>();
	public static final Map<Method, Map<MinecraftVersion, Method>> implementationMethodCache = new HashMap<>();
	
	private static final CacheState cacheState = new CacheState();
	
	public static void prepareCache() {
		for (final MinecraftVersion version : MinecraftVersion.values()) {
			internalClassCache.put(version, new ArrayList<>());
		}
		
		internalClassCache.put(null, new ArrayList<>());
		
		for (final Class<?> internalClass : ClassUtil.getClasses(ConstructCore.Constants.CONSTRUCT_PACKAGE)) {
			if (ConstructUtil.isLibraryClass(internalClass) || ConstructUtil.isImplementationClass(internalClass)) {
				final ImplementationVersion versionAnnotation = internalClass.getAnnotation(ImplementationVersion.class);
				final MinecraftVersion version = versionAnnotation != null ? versionAnnotation.value() : null;
				
				internalClassCache.get(version).add(internalClass);
			}
		}
		
		cacheState.markPrepared();
	}
	
	public static void generateCache() {
		if (cacheState.isPrepared()) {
			prepareCache();
		}
		
		for (final Class<?> libraryClass : ClassUtil.getClasses(ConstructCore.Constants.LIBRARY_PACKAGE)) {
			Arrays.stream(libraryClass.getDeclaredMethods())
					.filter(libraryMethod -> Modifier.isStatic(libraryMethod.getModifiers()))
					.forEach(libraryMethod -> {
						implementationMethodCache.put(libraryMethod, new HashMap<>());
						
						Arrays.stream(MinecraftVersion.values())
								.filter(version -> ImplementationUtil.getImplementationMethod(version, libraryMethod) != null)
								.map(version -> Pair.of(version, ImplementationUtil.getImplementationMethod(version, libraryMethod)))
								.forEach(versionPair -> implementationMethodCache.get(libraryMethod).put(versionPair.getFirst(), versionPair.getSecond()));
					});
		}
		
		cacheState.markGenerated();
	}
	
	public static MethodCaller getMethodCallerObject(final Method libraryMethod) {
		return new MethodCaller(
				libraryMethod.getDeclaringClass().getSimpleName(),
				libraryMethod.getName(),
				libraryMethod.getParameterTypes()
		);
	}
	
	private static class CacheState {
		private boolean loaded = false;
		private boolean generated = false;
		private boolean cleared = false;
		private boolean prepared = false;
		
		public boolean isPrepared() {
			return this.prepared;
		}
		
		public void markPrepared() {
			this.prepared = true;
		}
		
		public boolean isLoaded() {
			return this.loaded;
		}
		
		public void markLoaded() {
			this.loaded = true;
		}
		
		public boolean isGenerated() {
			return this.generated;
		}
		
		public void markGenerated() {
			this.generated = true;
		}
		
		public boolean isCleared() {
			return this.cleared;
		}
		
		public void markCleared() {
			this.cleared = true;
		}
	}
}
