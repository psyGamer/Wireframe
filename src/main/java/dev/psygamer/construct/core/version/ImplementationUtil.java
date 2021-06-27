package dev.psygamer.construct.core.version;

import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.core.exceptions.LibraryException;
import dev.psygamer.construct.util.reflection.MethodUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ImplementationUtil {
	
	public static Method getLibraryMethod(final MethodCaller caller) {
		if (ImplementationCache.libraryMethodCache.containsKey(caller)) {
			return ImplementationCache.libraryMethodCache.get(caller);
		}
		
		try {
			final Class<?> libraryClass = Class.forName(caller.className);
			
			List<Method> possibleMethods = MethodUtil.getStaticMethodsByName(
					libraryClass,
					caller.methodName
			);
			
			if (possibleMethods.size() == 1) {
				ImplementationCache.libraryMethodCache.put(caller, possibleMethods.get(0));
				
				return possibleMethods.get(0);
			}
			
			possibleMethods = possibleMethods.stream()
					.filter(method -> method.getParameterCount() == caller.parameterTypes.length)
					.collect(Collectors.toList());
			
			if (possibleMethods.size() == 1) {
				ImplementationCache.libraryMethodCache.put(caller, possibleMethods.get(0));
				
				return possibleMethods.get(0);
			}
			
			for (final Method possibleMethod : possibleMethods) {
				if (Arrays.equals(possibleMethod.getParameterTypes(), caller.parameterTypes)) {
					return possibleMethod;
				}
			}
		} catch (final ClassNotFoundException ignored) {
		}
		
		throw new LibraryException("oh no");
	}
	
	private static Class<?> getImplementationClass(final Class<?> libraryClass, final MinecraftVersion version) {
		try {
			return Class.forName(
					getLibraryImplementationPackagePath(version) + "." +
							getImplementationClassPath(libraryClass, version)
			);
		} catch (final ClassNotFoundException e) {
			return null;
		}
	}
	
	public static Class<?> getImplementationClass(final Class<?> libraryClass) {
		final MinecraftVersion newestVersion = Arrays.stream(MinecraftVersion.getVersionBelow(MinecraftVersion.getCurrentVersion()))
				.filter(version -> ImplementationUtil.getImplementationClass(libraryClass, version) != null)
				.max(MinecraftVersion::compareTo)
				.orElse(MinecraftVersion.COMMON);
		
		return ImplementationUtil.getImplementationClass(libraryClass, newestVersion);
	}
	
	public static Method getImplementationMethod(final Method libraryMethod) {
		MinecraftVersion version = MinecraftVersion.getCurrentVersion();
		
		while (true) {
			final Class<?> implementationClass = getImplementationClass(libraryMethod.getDeclaringClass(), version);
			
			version = version.getPreviousVersion();
			
			if (implementationClass == null) {
				continue;
			}
			
			try {
				return implementationClass.getDeclaredMethod(libraryMethod.getName(), libraryMethod.getParameterTypes());
			} catch (final NoSuchMethodException ignored) {
			}
			
			if (version == MinecraftVersion.COMMON) {
				break;
			}
		}
		
		throw new LibraryException("Could not find impl for + " + libraryMethod);
	}
	
	private static String getLibraryImplementationPackagePath(final MinecraftVersion version) {
		return version == MinecraftVersion.COMMON
				? ConstructCore.Constants.COMMON_IMPLEMENTATION_PACKAGE
				: ConstructCore.Constants.CONSTRUCT_PACKAGE + ".impl.v" + version.getVersionString().substring(2).replace(".", "_");
	}
	
	private static String getInternalPackagePath(final Class<?> internalClass) {
		return Arrays.stream(internalClass.getName()
				.split("\\."))
				.skip(isLibraryClass(internalClass) ? 4 : 5)
				.limit(internalClass.getName().split("\\.").length - (isLibraryClass(internalClass) ? 5 : 6))
				.collect(Collectors.joining("."));
	}
	
	private static String getImplementationClassPath(final Class<?> libraryClass, final MinecraftVersion version) {
		return version == MinecraftVersion.COMMON
				? getInternalPackagePath(libraryClass) + ".Common" + libraryClass.getSimpleName()
				: getInternalPackagePath(libraryClass) + "." + libraryClass.getSimpleName() + "Impl" + version.name().replace("v", "");
	}
	
	private static boolean isImplementationClass(final Class<?> internalClass) {
		return internalClass.getName().startsWith(ConstructCore.Constants.IMPLEMENTATION_PACKAGE_ROOT);
	}
	
	private static boolean isLibraryClass(final Class<?> internalClass) {
		return internalClass.getName().startsWith(ConstructCore.Constants.LIBRARY_PACKAGE);
	}
	
	private static boolean isInternalClass(final Class<?> internalClass) {
		return isLibraryClass(internalClass) || isImplementationClass(internalClass);
	}
}
