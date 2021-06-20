package dev.psygamer.construct.core.version;

import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.core.exceptions.LibraryException;

import java.util.Arrays;
import java.util.List;
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
		final String implementationClassLocation = getLibraryImplementationPackage(version) +
				libraryClass.getName().replace(ConstructCore.Constants.LIBRARY_PACKAGE, "");
		
		try {
			return Class.forName(implementationClassLocation);
		} catch (final ClassNotFoundException e) {
			return null;
		}
	}
	
	public static String getLibraryImplementationPackage(final MinecraftVersion version) {
		return version == MinecraftVersion.COMMON
				? ConstructCore.Constants.COMMON_IMPLEMENTATION_PACKAGE
				: ConstructCore.Constants.CONSTRUCT_PACKAGE + ".impl.v" + version.getVersionString().split("\\.")[1];
	}
	
	public static String getInternalPackage(final Class<?> internalClass) {
		return Arrays.stream(internalClass.getName()
				.replace(ConstructCore.Constants.LIBRARY_PACKAGE, "")
				.replace(ConstructCore.Constants.IMPLEMENTATION_PACKAGE_ROOT, "")
				.split("\\."))
				.skip(1)
				.collect(Collectors.joining("."));
		
	}
	
	public static String getImplementationClassPath(final Class<?> implementationClass, final MinecraftVersion version) {
		return version == MinecraftVersion.COMMON
				? getInternalPackage(implementationClass) + ".Common" + implementationClass.getSimpleName()
				: getInternalPackage(implementationClass) + implementationClass.getSimpleName() + "Impl" + version.name().replace("v", "");
	}
}
