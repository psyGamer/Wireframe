package dev.psyGamer.anvil.lib.registry;

import dev.psyGamer.anvil.core.version.MinecraftVersion;
import dev.psyGamer.anvil.core.version.RequireImplementation;
import dev.psyGamer.anvil.core.version.SupportedVersion;
import dev.psyGamer.anvil.core.version.VersionHandler;
import net.minecraft.item.Item;

import java.util.List;

@RequireImplementation
@SupportedVersion(MinecraftVersion.v12)
public abstract class ItemRegistry {
	
	public static String registerItems(final List<Item> items) {
		return (String) VersionHandler.executeOverloadedVersionedMethod(new Class[] { List.class }, items);
	}
	
	public static String registerItems(final Item items) {
		return (String) VersionHandler.executeOverloadedVersionedMethod(new Class[] { Item.class }, items);
	}
}
