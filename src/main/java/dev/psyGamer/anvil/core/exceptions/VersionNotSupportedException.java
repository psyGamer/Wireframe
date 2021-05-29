package dev.psyGamer.anvil.core.exceptions;

import dev.psyGamer.anvil.core.version.MinecraftVersion;

import java.util.Arrays;

public class VersionNotSupportedException extends RuntimeException {
	
	public VersionNotSupportedException(final MinecraftVersion notSupportedVersion, final MinecraftVersion... supportedVersions) {
		super("Minecraft version " + notSupportedVersion.getVersionString() + " is not supported!\n" +
				"Supported versions: " + Arrays.stream(supportedVersions).map(version -> version.getVersionString()));
	}
}
