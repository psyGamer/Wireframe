package dev.psyGamer.anvil.lib.registry;

import dev.psyGamer.anvil.core.version.MinecraftVersion;
import dev.psyGamer.anvil.core.version.RequireImplementation;
import dev.psyGamer.anvil.core.version.SupportedVersion;

@RequireImplementation
@SupportedVersion(MinecraftVersion.v12)
public interface ItemRegistry {
	
	void registerItems();
}
