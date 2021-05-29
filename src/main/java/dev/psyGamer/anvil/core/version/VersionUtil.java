package dev.psyGamer.anvil.core.version;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VersionUtil {
	
	@NonNull
	public static List<MinecraftVersion> getSupportedVersions(final Class<?> libraryClass) {
		if (libraryClass.isAnnotationPresent(SupportedVersion.class)) {
			return new ArrayList<MinecraftVersion>() {{
				add(libraryClass.getAnnotation(SupportedVersion.class).value());
			}};
		}
		
		if (libraryClass.isAnnotationPresent(SupportedVersionsList.class)) {
			return Arrays.stream(libraryClass.getAnnotation(SupportedVersionsList.class).value()).collect(Collectors.toList());
		}
		
		if (libraryClass.isAnnotationPresent(SupportedVersionsRange.class)) {
			final int startingIndex = libraryClass.getAnnotation(SupportedVersionsRange.class).from().ordinal();
			final int endingIndex = libraryClass.getAnnotation(SupportedVersionsRange.class).to().ordinal();
			
			return new ArrayList<>(Arrays.asList(MinecraftVersion.values()).subList(startingIndex, endingIndex + 1));
		}
		
		return new ArrayList<>();
	}
}
