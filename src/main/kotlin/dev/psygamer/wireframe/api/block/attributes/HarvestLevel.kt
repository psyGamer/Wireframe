package dev.psygamer.wireframe.api.block.attributes

enum class HarvestLevel(val harvestLevel: Int) {
	NONE(-1),
	WOOD(0),
	STONE(1),
	IRON(2),
	DIAMOND(3),
	NETHERITE(4);
}