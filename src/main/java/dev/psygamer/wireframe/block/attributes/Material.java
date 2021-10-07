package dev.psygamer.wireframe.block.attributes;

public class Material {
	
	public static final Material
		
		/* Air */
		
		AIR = get(net.minecraft.block.material.Material.AIR),
		STRUCTURAL_AIR = get(net.minecraft.block.material.Material.STRUCTURAL_AIR),
	
		/* Natural Materials */
	
		STONE = get(net.minecraft.block.material.Material.STONE),
		GRASS = get(net.minecraft.block.material.Material.GRASS),
		DIRT = get(net.minecraft.block.material.Material.DIRT),
		SAND = get(net.minecraft.block.material.Material.SAND),
		CLAY = get(net.minecraft.block.material.Material.CLAY),
	
		WOOD = get(net.minecraft.block.material.Material.WOOD),
		NETHER_WOOD = get(net.minecraft.block.material.Material.NETHER_WOOD),
	
		SNOW = get(net.minecraft.block.material.Material.SNOW),
		TOP_SNOW = get(net.minecraft.block.material.Material.TOP_SNOW),
		ICE = get(net.minecraft.block.material.Material.ICE),
		ICE_SOLID = get(net.minecraft.block.material.Material.ICE_SOLID),
	
		WATER = get(net.minecraft.block.material.Material.WATER),
		LAVA = get(net.minecraft.block.material.Material.LAVA),
		FIRE = get(net.minecraft.block.material.Material.FIRE),
	
		/* Vegetation */
	
		PLANT = get(net.minecraft.block.material.Material.PLANT),
		REPLACEABLE_PLANT = get(net.minecraft.block.material.Material.REPLACEABLE_PLANT),
		WATER_PLANT = get(net.minecraft.block.material.Material.WATER_PLANT),
		REPLACEABLE_WATER_PLANT = get(net.minecraft.block.material.Material.REPLACEABLE_WATER_PLANT),
		FIREPROOF_PLANT = null,
		REPLACEABLE_FIREPROOF_PLANT = get(net.minecraft.block.material.Material.REPLACEABLE_FIREPROOF_PLANT),
	
		VEGETABLE = get(net.minecraft.block.material.Material.VEGETABLE),
	
		BAMBOO = get(net.minecraft.block.material.Material.BAMBOO),
		BAMBOO_SAPLING = get(net.minecraft.block.material.Material.BAMBOO_SAPLING),
	
		CACTUS = get(net.minecraft.block.material.Material.CACTUS),
	
		CORAL = get(net.minecraft.block.material.Material.CORAL),
	
		LEAVES = get(net.minecraft.block.material.Material.LEAVES),
	
		/* Decoration */
	
		DECORATION = get(net.minecraft.block.material.Material.DECORATION),
	
		GLASS = get(net.minecraft.block.material.Material.GLASS),
		BUILDABLE_GLASS = get(net.minecraft.block.material.Material.BUILDABLE_GLASS),
	
		METAL = get(net.minecraft.block.material.Material.METAL),
		HEAVY_METAL = get(net.minecraft.block.material.Material.HEAVY_METAL),
	
		SPONGE = get(net.minecraft.block.material.Material.SPONGE),
		SHULKER_SHELL = get(net.minecraft.block.material.Material.SHULKER_SHELL),
	
		WOOL = get(net.minecraft.block.material.Material.WOOL),
		CLOTH_DECORATION = get(net.minecraft.block.material.Material.CLOTH_DECORATION),
	
		WEB = get(net.minecraft.block.material.Material.WEB),
		
		/* Miscellaneous */
	
		PORTAL = get(net.minecraft.block.material.Material.PORTAL),
	
		BARRIER = get(net.minecraft.block.material.Material.BARRIER),
		PISTON = get(net.minecraft.block.material.Material.PISTON),
	
		EXPLOSIVE = get(net.minecraft.block.material.Material.EXPLOSIVE),
	
		BUBBLE_COLUMN = get(net.minecraft.block.material.Material.BUBBLE_COLUMN),
	
		EGG = get(net.minecraft.block.material.Material.EGG),
		CAKE = get(net.minecraft.block.material.Material.CAKE);
	
	
	private net.minecraft.block.material.Material internal;
	
	private Material(final net.minecraft.block.material.Material internal) {
		this.internal = internal;
	}
	
	public static Material get(final net.minecraft.block.material.Material internal) {
		return new Material(internal);
	}
	
	public net.minecraft.block.material.Material getInternal() {
		return this.internal;
	}
}
