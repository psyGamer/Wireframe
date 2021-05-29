package dev.psyGamer.anvil.impl.v12.registry;

import dev.psyGamer.anvil.core.version.VersionHandler;
import net.minecraft.item.Item;

import java.util.List;

public class ItemRegistry {
	
	public static String registerItems(final List<Item> items) {
		
		System.out.println("REG ITEM");
		return "69 nice";
	}
	
	public static String registerItems(final Item items) {
		return "VersionHandler.executeVersionedMethod(items);";
	}
}
