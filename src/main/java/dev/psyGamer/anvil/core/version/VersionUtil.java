package dev.psyGamer.anvil.core.version;

import com.google.common.collect.ImmutableList;
import dev.psyGamer.anvil.core.exceptions.LibraryException;

import java.util.Arrays;

public class VersionUtil {
	
	public static ImmutableList<MinecraftVersion> getSupportedVersions(final Class<?> libraryClass) {
		if (libraryClass.isAnnotationPresent(SupportedOnlyIn.class)) {
			return ImmutableList.of(libraryClass.getAnnotation(SupportedOnlyIn.class).value());
		}
		
		if (libraryClass.isAnnotationPresent(SupportedSince.class) && libraryClass.isAnnotationPresent(SupportedUntil.class)) {
			final SupportedSince supportedSince = libraryClass.getAnnotation(SupportedSince.class);
			final SupportedUntil supportedUntil = libraryClass.getAnnotation(SupportedUntil.class);
			
			return ImmutableList.<MinecraftVersion>builder()
					.addAll(Arrays.asList(MinecraftVersion.getVersionBetween(supportedSince.value(), supportedUntil.value())))
					.add(supportedSince.value())
					.add(supportedUntil.value())
					.build();
		}
		
		if (libraryClass.isAnnotationPresent(SupportedSince.class)) {
			final SupportedSince supportedSince = libraryClass.getAnnotation(SupportedSince.class);
			
			return ImmutableList.<MinecraftVersion>builder()
					.addAll(Arrays.asList(MinecraftVersion.getVersionAbove(supportedSince.value())))
					.add(supportedSince.value())
					.build();
		}
		
		if (libraryClass.isAnnotationPresent(SupportedUntil.class)) {
			final SupportedUntil supportedUntil = libraryClass.getAnnotation(SupportedUntil.class);
			
			return ImmutableList.<MinecraftVersion>builder()
					.addAll(Arrays.asList(MinecraftVersion.getVersionBelow(supportedUntil.value())))
					.add(supportedUntil.value())
					.build();
		}
		
		if (libraryClass.isAnnotationPresent(LibraryOnly.class)) {
			throw new LibraryException(libraryClass + " is annotated with LibraryOnly yet it calls an implementation method");
		}
		
		throw new LibraryException(libraryClass + " has no supported versions defined");
	}
	
	public static boolean isCurrentVersionSupported(final Class<?> libraryClass) {
		return isVersionSupported(libraryClass, MinecraftVersion.getCurrentMinecraftVersion());
	}
	
	public static boolean isVersionSupported(final Class<?> libraryClass, final MinecraftVersion version) {
		try {
			return getSupportedVersions(libraryClass).contains(version);
		} catch (final LibraryException ex) {
			return false;
		}
	}
}
