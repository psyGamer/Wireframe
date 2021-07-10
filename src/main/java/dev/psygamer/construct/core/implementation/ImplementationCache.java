package dev.psygamer.construct.core.implementation;

import com.google.common.collect.ImmutableMap;
import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.core.ConstructUtil;
import dev.psygamer.construct.util.reflection.ClassUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public final class ImplementationCache {
	
	private static ImmutableMap<MethodCaller, Method> libraryMethodCache;
	private static ImmutableMap<MethodCaller, Method> implementationMethodCache;
	
	public static ImmutableMap<MethodCaller, Method> getLibraryMethodCache() {
		if (libraryMethodCache == null) {
			generateCache();
		}
		
		return libraryMethodCache;
	}
	
	public static Method getLibraryMethod(final MethodCaller caller) {
		if (getLibraryMethodCache().containsKey(caller)) {
			return libraryMethodCache.get(caller);
		} else {
			return null;
		}
	}
	
	public static ImmutableMap<MethodCaller, Method> getImplementationMethodCache() {
		if (implementationMethodCache == null) {
			generateCache();
		}
		
		return implementationMethodCache;
	}
	
	public static Method getImplementationMethod(final MethodCaller caller) {
		if (getImplementationMethodCache().containsKey(caller)) {
			return implementationMethodCache.get(caller);
		} else {
			return null;
		}
	}
	
	protected static void generateCache() {
		final Map<MinecraftVersion, List<Class<?>>> internalClassCache = new HashMap<>();
		final Map<MethodCaller, Method> libraryMethodCache = new HashMap<>();
		final Map<MethodCaller, Method> implementationMethodCache = new HashMap<>();
		
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
		
		for (final Class<?> libraryClass : ClassUtil.getClasses(ConstructCore.Constants.LIBRARY_PACKAGE)) {
			Arrays.stream(libraryClass.getDeclaredMethods())
					.filter(libraryMethod -> Modifier.isStatic(libraryMethod.getModifiers()))
					.forEach(libraryMethod -> {
						final MinecraftVersion implementationMethodVersion = Arrays.stream(MinecraftVersion.values())
								.filter(version -> ImplementationUtil.getImplementationMethod(version, libraryMethod) != null)
								.max(MinecraftVersion::compareTo)
								.orElse(MinecraftVersion.COMMON);
						
						final Method implementationMethod = ImplementationUtil.getImplementationMethod(implementationMethodVersion, libraryMethod);
						
						libraryMethodCache.put(MethodCaller.ofMethod(libraryMethod), libraryMethod);
						implementationMethodCache.put(MethodCaller.ofMethod(libraryMethod), implementationMethod);
					});
		}
		
		ImplementationCache.libraryMethodCache = ImmutableMap.copyOf(libraryMethodCache);
		ImplementationCache.implementationMethodCache = ImmutableMap.copyOf(implementationMethodCache);
	}
}
