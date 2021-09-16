package dev.psygamer.wireframe.util;

public enum Direction {
	NORTH(net.minecraft.util.Direction.NORTH),
	SOUTH(net.minecraft.util.Direction.SOUTH),
	EAST(net.minecraft.util.Direction.EAST),
	WEST(net.minecraft.util.Direction.WEST),
	UP(net.minecraft.util.Direction.UP),
	DOWN(net.minecraft.util.Direction.DOWN);
	
	private final net.minecraft.util.Direction internal;
	
	Direction(final net.minecraft.util.Direction internal) {
		this.internal = internal;
	}
	
	public static Direction get(final net.minecraft.util.Direction internalDirection) {
		switch (internalDirection) {
			default:
			case NORTH:
				return NORTH;
			case SOUTH:
				return SOUTH;
			case EAST:
				return EAST;
			case WEST:
				return WEST;
			case UP:
				return UP;
			case DOWN:
				return DOWN;
		}
	}
	
	public net.minecraft.util.Direction getInternal() {
		return this.internal;
	}
}
