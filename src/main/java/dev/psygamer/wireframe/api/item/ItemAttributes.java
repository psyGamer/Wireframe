package dev.psygamer.wireframe.api.item;

import dev.psygamer.wireframe.core.impl.Instancer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

public abstract class ItemAttributes {
	
	private static final ItemAttributes INSTANCE = Instancer.createInstance();
	
	/**
	 * @return A new instance of a {@link ItemAttributes}
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public static ItemAttributes create() {
		return INSTANCE.createInstance();
	}
	
	/**
	 * @param group Creative Tab for the Block
	 * @return A new instance of a {@link ItemAttributes}
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public static ItemAttributes create(final ItemGroup group) {
		return INSTANCE.createInstance(group);
	}
	
	public abstract ItemAttributes maxStackSize(final int stackSize);
	
	public abstract ItemAttributes rarity(final Rarity rarity);
	
	public abstract ItemAttributes fireResistant(final boolean fireResistant);
	
	public abstract ItemAttributes repairable(final boolean repairable);
	
	public abstract ItemAttributes craftingRemainder(final Item remainder);
	
	protected abstract ItemAttributes createInstance();
	
	protected abstract ItemAttributes createInstance(final ItemGroup group);
}
