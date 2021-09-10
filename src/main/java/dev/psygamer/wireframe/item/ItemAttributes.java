package dev.psygamer.wireframe.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemAttributes {
	
	protected Internal internal;
	
	protected int maxStackSize;
	
	protected boolean fireResistant;
	protected boolean repairable;
	
	protected ItemGroup itemGroup;
	protected Item craftingRemainder;
	protected Rarity rarity;
	
	/**
	 * @return A new instance of a {@link ItemAttributes}
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public ItemAttributes() {
		this(null);
	}
	
	/**
	 * @param itemGroup Creative Tab for the Block
	 * @return A new instance of a {@link ItemAttributes}
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public ItemAttributes(final ItemGroup itemGroup) {
		this.itemGroup = itemGroup;
		
		this.internal = new Internal();
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
	
	public Internal getInternal() {
		return this.internal;
	}
	
	protected class Internal {
		public Item.Properties createProperties() {
			final Item.Properties properties = new Item.Properties()
					.rarity(ItemAttributes.this.rarity.getInternal())
					.stacksTo(ItemAttributes.this.maxStackSize);
			
			if (ItemAttributes.this.itemGroup != null)
				properties.tab(ItemAttributes.this.itemGroup);
			
			if (ItemAttributes.this.fireResistant)
				properties.fireResistant();
			
			if (!ItemAttributes.this.repairable)
				properties.setNoRepair();
			
			return properties;
		}
	}
}
