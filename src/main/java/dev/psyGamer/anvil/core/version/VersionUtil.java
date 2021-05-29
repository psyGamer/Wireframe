package dev.psyGamer.anvil.core.version;

public class VersionUtil {
	
	public static MinecraftVersion[] getSupportedVersions(final Class<?> libraryClass) {
		if (libraryClass.isAnnotationPresent(SupportedVersion.class)) {
			return new MinecraftVersion[] {
					libraryClass.getAnnotation(SupportedVersion.class).value()
			};
		}
		
		if (libraryClass.isAnnotationPresent(SupportedVersionsList.class)) {
			return libraryClass.getAnnotation(SupportedVersionsList.class).value();
		}
		
		if (libraryClass.isAnnotationPresent(SupportedVersionsRange.class)) {
			final int startingIndex = libraryClass.getAnnotation(SupportedVersionsRange.class).from().ordinal();
			final int endingIndex = libraryClass.getAnnotation(SupportedVersionsRange.class).to().ordinal();
			
			final MinecraftVersion[] versions = new MinecraftVersion[endingIndex - startingIndex + 1];
			
			for (int i = startingIndex ; i <= endingIndex ; i++) {
				versions[i - startingIndex] = MinecraftVersion.values()[i];
			}
			
			return versions;
		}
		
		return null;
	}
}
