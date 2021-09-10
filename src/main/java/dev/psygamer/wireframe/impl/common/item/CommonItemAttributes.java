package dev.psygamer.wireframe.impl.common.item;

import dev.psygamer.wireframe.item.ItemAttributes;

import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public abstract class CommonItemAttributes extends ItemAttributes {
	
	protected int maxStackSize;
	
	protected boolean fireResistant;
	protected boolean repairable;
	
	protected Item craftingRemainder;
	protected Rarity rarity;
	
	@Override
	public ItemAttributes maxStackSize(final int maxStackSize) {
		this.maxStackSize = maxStackSize;
		
		return this;
	}
	
	@Override
	public ItemAttributes fireResistant(final boolean fireResistant) {
		this.fireResistant = fireResistant;
		
		return this;
	}
	
	@Override
	public ItemAttributes repairable(final boolean repairable) {
		this.repairable = repairable;
		
		return this;
	}
	
	@Override
	public ItemAttributes craftingRemainder(final Item craftingRemainder) {
		this.craftingRemainder = craftingRemainder;
		
		return this;
	}
	
	@Override
	public ItemAttributes rarity(final Rarity rarity) {
		this.rarity = rarity;
		
		return this;
	}
}
