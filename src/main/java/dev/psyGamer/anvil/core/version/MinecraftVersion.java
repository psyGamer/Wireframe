package dev.psyGamer.anvil.core.version;

import lombok.Getter;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.versions.forge.ForgeVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum MinecraftVersion {
	v16("1.16"),
	v16_1("1.16.1"),
	v16_2("1.16.2"),
	v16_3("1.16.3"),
	v16_4("1.16.4"),
	v16_5("1.16.5");
	
	@Getter
	private String versionString;
	
	MinecraftVersion(final String versionString) {
		this.versionString = versionString;
	}
	
	static MinecraftVersion getCurrentMinecraftVersion() {
		try {
			return MinecraftVersion.valueOf(
					"v" + ForgeVersion.getVersion()
							.replace("1.", "")
							.replace(".", "_")
			);
		} catch (final IllegalArgumentException ex) {
			return v16;
		}
	}
	
	static MinecraftVersion[] getVersionAbove(final MinecraftVersion version) {
		return Arrays.stream(values()).filter(mcVersion -> mcVersion.ordinal() >= version.ordinal()).toArray(MinecraftVersion[]::new);
	}
	
	static MinecraftVersion[] getVersionBelow(final MinecraftVersion version) {
		return Arrays.stream(values()).filter(mcVersion -> mcVersion.ordinal() <= version.ordinal()).toArray(MinecraftVersion[]::new);
	}
	
	static MinecraftVersion[] getVersionBetween(final MinecraftVersion olderVersion, final MinecraftVersion newerVersion) {
		return Arrays.stream(getVersionAbove(olderVersion)).filter(version -> version.ordinal() < newerVersion.ordinal()).toArray(MinecraftVersion[]::new);
	}
}
