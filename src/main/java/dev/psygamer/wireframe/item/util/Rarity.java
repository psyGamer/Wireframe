package dev.psygamer.wireframe.item.util;

public enum Rarity {
	
	COMMON(net.minecraft.item.Rarity.COMMON),
	UNCOMMON(net.minecraft.item.Rarity.UNCOMMON),
	RARE(net.minecraft.item.Rarity.RARE),
	EPIC(net.minecraft.item.Rarity.EPIC);
	
	private final net.minecraft.item.Rarity internal;
	
	Rarity(final net.minecraft.item.Rarity internal) {
		this.internal = internal;
	}
	
	public static Rarity get(final net.minecraft.item.Rarity internal) {
		switch (internal) {
			default:
			case COMMON:
				return COMMON;
			case UNCOMMON:
				return UNCOMMON;
			case RARE:
				return RARE;
			case EPIC:
				return EPIC;
		}
	}
	
	public net.minecraft.item.Rarity getInternal() {
		return this.internal;
	}
}
