package dev.psygamer.wireframe.core.implementation;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.WireframeUtil;
import dev.psygamer.wireframe.util.reflection.ClassUtil;

import com.google.common.collect.ImmutableMap;

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
		
		WireframeCore.LOGGER.debug("\n<== Generating cache for the Construct Library ==> \n");
		WireframeCore.LOGGER.debug("Initializing cache:");
		
		for (final MinecraftVersion version : MinecraftVersion.values()) {
			internalClassCache.put(version, new ArrayList<>());
			
			WireframeCore.LOGGER.debug("  Initialized for " + version.toString());
		}
		
		internalClassCache.put(null, new ArrayList<>());
		
		WireframeCore.LOGGER.debug("Defining class version:");
		
		for (final Class<?> internalClass : ClassUtil.getClasses(WireframeCore.Constants.WIREFRAME_PACKAGE)) {
			if (WireframeUtil.isLibraryClass(internalClass) || WireframeUtil.isImplementationClass(internalClass)) {
				final ImplementationVersion versionAnnotation = internalClass.getAnnotation(ImplementationVersion.class);
				final MinecraftVersion version = versionAnnotation != null ? versionAnnotation.value() : null;
				
				internalClassCache.get(version).add(internalClass);
				
				if (version != null) {
					WireframeCore.LOGGER.debug("  " + internalClass.getName() + " is version: " + version);
				}
			}
		}
		
		WireframeCore.LOGGER.debug("Mapping library methods to implementation:");
		
		for (final Class<?> libraryClass : ClassUtil.getClasses(WireframeCore.Constants.LIBRARY_PACKAGE)) {
			WireframeCore.LOGGER.debug("  Methods of " + libraryClass.getName() + ":");
			Arrays.stream(libraryClass.getDeclaredMethods())
					.filter(libraryMethod -> Modifier.isStatic(libraryMethod.getModifiers()))
					.forEach(libraryMethod -> {
						final MinecraftVersion implementationMethodVersion = Arrays.stream(MinecraftVersion.values())
								.filter(version -> ImplementationUtil.getImplementationMethod(version, libraryMethod) != null)
								.max(MinecraftVersion::compareTo)
								.orElse(MinecraftVersion.COMMON);
						
						final Method implementationMethod = ImplementationUtil.getImplementationMethod(implementationMethodVersion, libraryMethod);
						
						WireframeCore.LOGGER.debug("    " + libraryMethod.getName() + "");
						
						libraryMethodCache.put(MethodCaller.ofMethod(libraryMethod), libraryMethod);
						implementationMethodCache.put(MethodCaller.ofMethod(libraryMethod), implementationMethod);
					});
		}
		
		WireframeCore.LOGGER.debug("\n==> Successfully generated cache for the Construct Library <==\n");
		
		ImplementationCache.libraryMethodCache = ImmutableMap.copyOf(libraryMethodCache);
		ImplementationCache.implementationMethodCache = ImmutableMap.copyOf(implementationMethodCache);
	}
}
