package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.internal.item.InternalItemAttributes;
import dev.psygamer.wireframe.item.util.Rarity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemAttributes {
	
	protected InternalItemAttributes internal;
	
	protected int maxStackSize = 64;
	
	protected boolean fireResistant = false;
	protected boolean repairable = false;
	
	protected ItemGroup itemGroup;
	protected Item craftingRemainder;
	
	protected Rarity rarity = Rarity.COMMON;
	
	/**
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public ItemAttributes() {
		this(null);
	}
	
	/**
	 * @param itemGroup Creative Tab for the Block
	 *
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public ItemAttributes(final ItemGroup itemGroup) {
		this.itemGroup = itemGroup;
		
		this.internal = new InternalItemAttributes(this);
	}
	
	public ItemAttributes maxStackSize(final int maxStackSize) {
		this.maxStackSize = maxStackSize;
		
		return this;
	}
	
	public ItemAttributes fireResistant(final boolean fireResistant) {
		this.fireResistant = fireResistant;
		
		return this;
	}
	
	public ItemAttributes repairable(final boolean repairable) {
		this.repairable = repairable;
		
		return this;
	}
	
	public ItemAttributes craftingRemainder(final Item craftingRemainder) {
		this.craftingRemainder = craftingRemainder;
		
		return this;
	}
	
	public ItemAttributes rarity(final Rarity rarity) {
		this.rarity = rarity;
		
		return this;
	}
	
	public int getMaxStackSize() {
		return this.maxStackSize;
	}
	
	public boolean isFireResistant() {
		return this.fireResistant;
	}
	
	public boolean isRepairable() {
		return this.repairable;
	}
	
	public ItemGroup getItemGroup() {
		return this.itemGroup;
	}
	
	public Item getCraftingRemainder() {
		return this.craftingRemainder;
	}
	
	public Rarity getRarity() {
		return this.rarity;
	}
	
	public InternalItemAttributes getInternal() {
		return this.internal;
	}
}
