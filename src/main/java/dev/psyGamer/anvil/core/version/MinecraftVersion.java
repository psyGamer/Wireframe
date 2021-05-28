package dev.psyGamer.anvil.core.version;

import net.minecraftforge.common.MinecraftForge;

public enum MinecraftVersion {
	v12;
	
	static MinecraftVersion getCurrentMinecraftVersion() {
		switch (MinecraftForge.MC_VERSION) {
			case "1.12":
			case "1.12.1":
			case "1.12.2":
			default:
				return MinecraftVersion.v12;
		}
	}
}
