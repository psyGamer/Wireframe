package dev.psygamer.wireframe.block.properties;

public enum HarvestLevel {
	HAND(-1),
	WOOD(0),
	STONE(1),
	IRON(2),
	DIAMOND(3),
	NETHERITE(4);
	
	private int level;
	
	HarvestLevel(final int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return this.level;
	}
}
