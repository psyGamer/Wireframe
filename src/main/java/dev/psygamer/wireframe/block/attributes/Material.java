package dev.psygamer.wireframe.block.attributes;

public enum Material {
		
	AIR(0x000000, 0.0f, true, false),

	/* Natural Materials */

	STONE(0x707070, 1.5f, 6.0f),
	GRASS(0x7FB238, 0.6f),
	DIRT(0x976D4D, 0.5f),
	SAND(0xF7E9A3, 0.5f),
	CLAY(0xA4A8B8, 0.6f),

	WOOD(0x8F7748, 2.0f, 3.0f, false, true),
	FIREPROOF_WOOD(0x8F7748, 2.0f, 3.0f, false, false),

	SNOW(0xFFFFFF, 0.2f), ICE(0xA0A0FF, 0.5f),

	/* Vegetation */

	PLANT(0x007C00, 0.0f, false, true),
	REPLACEABLE_PLANT(0x007C00, 0.0f, true, true),
	FIREPROOF_PLANT(0x007C00, 0.0f, false, false),
	REPLACEABLE_FIREPROOF_PLANT(0x007C00, 0.0f, true, false),

	VEGETABLE(0x007C00, 0.0f, false, false),
	LEAVES(0x007C00, 0.2f, false, true),

	/* Decoration */

	GLASS(0x000000, 0.3f),
	METAL(0xA7A7A7, 5.0f, 6.0f),
	CLOTH(0xC7C7C7, 0.8f, false, true),
	
	/* Miscellaneous */

	UNBREAKABLE(0x606060, -1.0f, 3600000.0f);
	
	private final int mapColor;
	
	private final float hardness;
	private final float blastResistance;
	
	private final boolean replaceable;
	private final boolean flammable;
	
	Material(final int mapColor, final float strength) {
		this(mapColor, strength, strength, false, false);
	}
	
	Material(final int mapColor, final float hardness, final float blastResistance) {
		this(mapColor, hardness, blastResistance, false, false);
	}
	
	Material(final int mapColor, final float strength, final boolean replaceable, final boolean flammable) {
		this(mapColor, strength, strength, replaceable, flammable);
	}
	
	Material(final int mapColor, final float hardness, final float blastResistance, final boolean replaceable, final boolean flammable) {
		this.mapColor = mapColor;
		
		this.hardness = hardness;
		this.blastResistance = blastResistance;
		
		this.replaceable = replaceable;
		this.flammable = flammable;
	}
	
	public int getMapColor() {
		return this.mapColor;
	}
	
	public float getHardness() {
		return this.hardness;
	}
	
	public float getBlastResistance() {
		return this.blastResistance;
	}
	
	public boolean isReplaceable() {
		return this.replaceable;
	}
	
	public boolean isFlammable() {
		return this.flammable;
	}
}
