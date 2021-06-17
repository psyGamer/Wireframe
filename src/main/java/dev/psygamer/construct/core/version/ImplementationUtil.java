package dev.psygamer.construct.core.version;

import com.google.common.collect.ImmutableList;
import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.core.exceptions.LibraryException;

import java.util.Arrays;
import java.util.List;

public class ImplementationUtil {
	
	public static List<MinecraftVersion> getSupportedVersions(final Class<?> libraryClass) {
		if (libraryClass.isAnnotationPresent(SupportedOnlyIn.class)) {
			return ImmutableList.of(libraryClass.getAnnotation(SupportedOnlyIn.class).value());
		}
		
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
		final String implementationClassLocation =
				ConstructCore.Constants.getLibraryImplementationPath(version) +
						libraryClass.getName().replace(ConstructCore.Constants.LIBRARY_PACKAGE, "");
		
		try {
			return Class.forName(implementationClassLocation);
		} catch (final ClassNotFoundException e) {
			return null;
		}
	}
}
