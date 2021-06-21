package dev.psygamer.construct.core.version;

import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.core.exceptions.LibraryException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ImplementationUtil {
	
	public static List<MinecraftVersion> getSupportedVersions(final Class<?> libraryClass) {
		if (libraryClass.isAnnotationPresent(SupportedSince.class) && libraryClass.isAnnotationPresent(SupportedUntil.class)) {
			final SupportedSince supportedSince = libraryClass.getAnnotation(SupportedSince.class);
			final SupportedUntil supportedUntil = libraryClass.getAnnotation(SupportedUntil.class);
			
			return Arrays.asList(MinecraftVersion.getVersionBetween(supportedSince.value(), supportedUntil.value()));
		}
		
		if (libraryClass.isAnnotationPresent(SupportedSince.class)) {
			final SupportedSince supportedSince = libraryClass.getAnnotation(SupportedSince.class);
			
			return Arrays.asList(MinecraftVersion.getVersionAbove(supportedSince.value()));
		}
		
		if (libraryClass.isAnnotationPresent(SupportedUntil.class)) {
			final SupportedUntil supportedUntil = libraryClass.getAnnotation(SupportedUntil.class);
			
			return Arrays.asList(MinecraftVersion.getVersionBelow(supportedUntil.value()));
		}
		
		if (libraryClass.isAnnotationPresent(LibraryOnly.class)) {
			throw new LibraryException(libraryClass + " is annotated with LibraryOnly yet it calls an implementation method");
		}
		
		throw new LibraryException(libraryClass + " has no supported versions defined");
	}
	
	public static boolean isCurrentVersionSupported(final Class<?> libraryClass) {
		return isVersionSupported(libraryClass, MinecraftVersion.getCurrentVersion());
	}
	
	public static boolean isVersionSupported(final Class<?> libraryClass, final MinecraftVersion version) {
		try {
			return getSupportedVersions(libraryClass).contains(version);
		} catch (final LibraryException ex) {
			return false;
		}
	}
	
	public static Class<?> getImplementationClass(final Class<?> libraryClass, final MinecraftVersion version) {
		try {
			return Class.forName(
					getLibraryImplementationPackage(version) + "." +
							getImplementationClassPath(libraryClass, version)
			);
		} catch (final ClassNotFoundException e) {
			return null;
		}
	}
	
	public static String getLibraryImplementationPackage(final MinecraftVersion version) {
		return version == MinecraftVersion.COMMON
				? ConstructCore.Constants.COMMON_IMPLEMENTATION_PACKAGE
				: ConstructCore.Constants.CONSTRUCT_PACKAGE + ".impl.v" + version.getVersionString().substring(2).replace(".", "_");
	}
	
	public static String getInternalPackage(final Class<?> internalClass) {
		return Arrays.stream(internalClass.getName()
				.split("\\."))
				.skip(isLibraryClass(internalClass) ? 4 : 5)
				.limit(internalClass.getName().split("\\.").length - (isLibraryClass(internalClass) ? 5 : 6))
				.collect(Collectors.joining("."));
	}
	
	public static String getImplementationClassPath(final Class<?> libraryClass, final MinecraftVersion version) {
		return version == MinecraftVersion.COMMON
				? getInternalPackage(libraryClass) + ".Common" + libraryClass.getSimpleName()
				: getInternalPackage(libraryClass) + "." + libraryClass.getSimpleName() + "Impl" + version.name().replace("v", "");
	}
	
	public static String getLibraryClassName(final Class<?> implementationClass) {
		final String className = implementationClass.getSimpleName();
		
		if (className.startsWith("Common")) {
			return className.substring(6);
		}
		
		if (isImplementationClass(implementationClass)) {
			return Pattern
					.compile("Impl\\d+(_\\d+)?")
					.matcher(className)
					.replaceFirst("");
		}
		
		return className;
	}
	
	public static Class<?> getLibraryClass(final Class<?> implementationClass) {
		if (!isLibraryClass(implementationClass) && !isImplementationClass(implementationClass)) {
			return implementationClass;
		}
		
		try {
			return Class.forName(
					ConstructCore.Constants.LIBRARY_PACKAGE + "." +
							getInternalPackage(implementationClass) + "." +
							getLibraryClassName(implementationClass)
			);
		} catch (final ClassNotFoundException | IllegalArgumentException e) {
			return null;
		}
	}
	
	public static boolean isImplementationClass(final Class<?> implementationClass) {
		return implementationClass.getName().startsWith(ConstructCore.Constants.IMPLEMENTATION_PACKAGE_ROOT);
	}
	
	public static boolean isLibraryClass(final Class<?> implementationClass) {
		return implementationClass.getName().startsWith(ConstructCore.Constants.LIBRARY_PACKAGE);
	}
}
