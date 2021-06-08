package dev.psyGamer.anvil.lib.block.properties;

import lombok.Getter;

public enum HarvestLevel {
	HAND(-1),
	WOOD(0),
	STONE(1),
	IRON(2),
	DIAMOND(3),
	NETHERITE(4);
	
	@Getter
	private int level;
	
	HarvestLevel(final int level) {
		this.level = level;
	}
}
