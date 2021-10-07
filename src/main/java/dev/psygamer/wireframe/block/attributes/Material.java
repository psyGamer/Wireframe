package dev.psygamer.wireframe.block.attributes;

public enum Material {
		
	AIR(net.minecraft.block.material.Material.AIR, 0.0f, true, false, false),

	/* Natural Materials */

	STONE(net.minecraft.block.material.Material.STONE, 6.0f, 1.5f),
	GRASS(net.minecraft.block.material.Material.GRASS, 0.6f),
	DIRT(net.minecraft.block.material.Material.DIRT, 0.5f),
	SAND(net.minecraft.block.material.Material.SAND, 0.5f),
	CLAY(net.minecraft.block.material.Material.CLAY, 0.6f),

	WOOD(net.minecraft.block.material.Material.WOOD, 2.0f, 3.0f, false, true, true),
	FIREPROOF_WOOD(net.minecraft.block.material.Material.NETHER_WOOD, 2.0f, 3.0f, false, false, true),

	SNOW(net.minecraft.block.material.Material.SNOW, 0.2f),
	ICE(net.minecraft.block.material.Material.ICE, 0.5f),

	/* Vegetation */

	PLANT(net.minecraft.block.material.Material.PLANT, 0.0f, false, true, false),
	REPLACEABLE_PLANT(net.minecraft.block.material.Material.REPLACEABLE_PLANT, 0.0f, true, true, false),
	FIREPROOF_PLANT(net.minecraft.block.material.Material.REPLACEABLE_FIREPROOF_PLANT, 0.0f, false, false, false),
	REPLACEABLE_FIREPROOF_PLANT(net.minecraft.block.material.Material.REPLACEABLE_FIREPROOF_PLANT, 0.0f, true, false, false),

	VEGETABLE(net.minecraft.block.material.Material.VEGETABLE, 0.0f, false, false, false),
	LEAVES(net.minecraft.block.material.Material.LEAVES, 0.2f, false, true, true),

	/* Decoration */

	GLASS(net.minecraft.block.material.Material.GLASS, 0.3f),
	METAL(net.minecraft.block.material.Material.METAL, 6.0f, 5.0f),
	CLOTH(net.minecraft.block.material.Material.WOOL,  0.8f, false, true, true),
	
	/* Miscellaneous */

	UNBREAKABLE(net.minecraft.block.material.Material.STONE, 3600000.0f, -1.0f);
	
	private final net.minecraft.block.material.Material internal;
	
	private final float hardness;
	private final float blastResistance;
	
	private final boolean replaceable;
	private final boolean flammable;
	private final boolean solid;
	
	Material(final net.minecraft.block.material.Material internal,
			 final float strength
	) {
		this(internal, strength, strength);
	}
	
	Material(final net.minecraft.block.material.Material internal,
			 final float blastResistance, final float hardness
	) {
		this(internal, hardness, blastResistance, false, false, true);
	}
	
	Material(final net.minecraft.block.material.Material internal,
			 final float strength,
			 final boolean replaceable, final boolean flammable, final boolean solid
	) {
		this(internal, strength, strength, replaceable, flammable,solid);
	}
	
	Material(final net.minecraft.block.material.Material internal,
			 final float hardness, final float blastResistance,
			 final boolean replaceable, final boolean flammable, final boolean solid
	) {
		this.internal = internal;
		
		this.hardness = hardness;
		this.blastResistance = blastResistance;
		
		this.replaceable = replaceable;
		this.flammable = flammable;
		this.solid = solid;
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
	
	public boolean isSolid() {
		return this.solid;
	}
	
	public net.minecraft.block.material.Material getInternal() {
		return this.internal;
	}
}
