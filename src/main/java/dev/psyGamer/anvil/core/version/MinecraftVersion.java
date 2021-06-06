package dev.psyGamer.anvil.core.version;

import lombok.Getter;
import net.minecraftforge.common.MinecraftForge;

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
					"v" + MinecraftForge.MC_VERSION
							.replace("1.", "")
							.replace(".", "_")
			);
		} catch (final IllegalArgumentException ex) {
			return v16;
		}
	}
}
