package dev.psygamer.wireframe.block.attributes;

import net.minecraftforge.common.ToolType;

public enum Material {
		
	AIR(net.minecraft.block.material.Material.AIR, null, 0.0f),

	/* Natural Materials */

	STONE(net.minecraft.block.material.Material.STONE, ToolType.PICKAXE, 6.0f, 1.5f),
	GRASS(net.minecraft.block.material.Material.GRASS, ToolType.SHOVEL, 0.6f),
	DIRT(net.minecraft.block.material.Material.DIRT, ToolType.SHOVEL, 0.5f),
	SAND(net.minecraft.block.material.Material.SAND, ToolType.SHOVEL, 0.5f),
	CLAY(net.minecraft.block.material.Material.CLAY, ToolType.SHOVEL, 0.6f),

	WOOD(net.minecraft.block.material.Material.WOOD, ToolType.AXE, 2.0f, 3.0f),
	FIREPROOF_WOOD(net.minecraft.block.material.Material.NETHER_WOOD, ToolType.AXE, 2.0f, 3.0f),

	SNOW(net.minecraft.block.material.Material.SNOW, ToolType.SHOVEL, 0.2f),
	ICE(net.minecraft.block.material.Material.ICE, ToolType.PICKAXE, 0.5f),

	/* Vegetation */

	PLANT(net.minecraft.block.material.Material.PLANT, null, 0.0f),
	REPLACEABLE_PLANT(net.minecraft.block.material.Material.REPLACEABLE_PLANT, null, 0.0f),
	FIREPROOF_PLANT(net.minecraft.block.material.Material.REPLACEABLE_FIREPROOF_PLANT, null, 0.0f),
	REPLACEABLE_FIREPROOF_PLANT(net.minecraft.block.material.Material.REPLACEABLE_FIREPROOF_PLANT, null, 0.0f),

	VEGETABLE(net.minecraft.block.material.Material.VEGETABLE, null, 0.0f),
	LEAVES(net.minecraft.block.material.Material.LEAVES, ToolType.HOE, 0.2f),

	/* Decoration */

	GLASS(net.minecraft.block.material.Material.GLASS, ToolType.PICKAXE, 0.3f),
	METAL(net.minecraft.block.material.Material.METAL, ToolType.PICKAXE, 6.0f, 5.0f),
	CLOTH(net.minecraft.block.material.Material.WOOL, null, 0.8f),
	
	/* Miscellaneous */

	UNBREAKABLE(net.minecraft.block.material.Material.STONE, null, 3600000.0f, -1.0f);
	
	private final net.minecraft.block.material.Material internal;
	
	private final ToolType correctTool;
	
	private final float hardness;
	private final float blastResistance;
	
	Material(final net.minecraft.block.material.Material internal,
			 final ToolType correctTool, final float strength
	) {
		this(internal, correctTool, strength, strength);
	}
	
	Material(final net.minecraft.block.material.Material internal,
			 final ToolType correctTool, final float hardness, final float blastResistance
	) {
		this.internal = internal;
		this.correctTool = correctTool;
		
		this.hardness = hardness;
		this.blastResistance = blastResistance;
	}
	
	public ToolType getCorrectTool() {
		return this.correctTool;
	}
	
	public float getHardness() {
		return this.hardness;
	}
	
	public float getBlastResistance() {
		return this.blastResistance;
	}
	
	public net.minecraft.block.material.Material getInternal() {
		return this.internal;
	}
}
