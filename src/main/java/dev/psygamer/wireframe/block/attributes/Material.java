package dev.psygamer.wireframe.block.attributes;

public enum Material {
		
	AIR(0.0f, true, false),

	/* Natural Materials */

	STONE(1.5f, 6.0f),
	GRASS(0.6f),
	DIRT(0.5f),
	SAND(0.5f),
	CLAY(0.6f),

	WOOD(2.0f, 3.0f, false, true),
	FIREPROOF_WOOD(2.0f, 3.0f, false, false),

	SNOW(0.2f), ICE(0.5f),

	/* Vegetation */

	PLANT(0.0f, false, true),
	REPLACEABLE_PLANT(0.0f, true, true),
	FIREPROOF_PLANT(0.0f, false, false),
	REPLACEABLE_FIREPROOF_PLANT(0.0f, true, false),

	VEGETABLE(0.0f, false, false),
	LEAVES(0.2f, false, true),

	/* Decoration */

	GLASS(0.3f),
	METAL(5.0f, 6.0f),
	CLOTH(0.8f, false, true),
	
	/* Miscellaneous */

	UNBREAKABLE(-1.0f, 3600000.0f);
	
	private final float hardness;
	private final float blastResistance;
	
	private final boolean replaceable;
	private final boolean flammable;
	
	Material(final float strength) {
		this(strength, strength, false, false);
	}
	
	Material(final float hardness, final float blastResistance) {
		this(hardness, blastResistance, false, false);
	}
	
	Material(final float strength, final boolean replaceable, final boolean flammable) {
		this(strength, strength, replaceable, flammable);
	}
	
	Material(final float hardness, final float blastResistance, final boolean replaceable, final boolean flammable) {
		this.hardness = hardness;
		this.blastResistance = blastResistance;
		
		this.replaceable = replaceable;
		this.flammable = flammable;
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
