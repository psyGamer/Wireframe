package dev.psyGamer.anvil.core.version;

import java.util.List;

public class VersionNotSupportedException extends RuntimeException {
	
	public VersionNotSupportedException(final MinecraftVersion notSupportedVersion, final List<MinecraftVersion> supportedVersions) {
		super("Minecraft version " + notSupportedVersion.getVersionString() + " is not supported!\n" +
				"Supported versions: " + supportedVersions.stream().map(MinecraftVersion::getVersionString));
	}
}
