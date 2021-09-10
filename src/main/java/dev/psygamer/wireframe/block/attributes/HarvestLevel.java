package dev.psygamer.wireframe.block.attributes;

public enum HarvestLevel {
	NONE(-1),
	WOOD(0),
	STONE(1),
	IRON(2),
	DIAMOND(3),
	NETHERITE(4);
	
	private final int level;
	
	HarvestLevel(final int level) {
		this.level = level;
	}
	
	public int getHarvestLevel() {
		return this.level;
	}
}