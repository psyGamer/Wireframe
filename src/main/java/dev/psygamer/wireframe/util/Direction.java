package dev.psygamer.wireframe.util;

import dev.psygamer.wireframe.util.math.vector.Vector3i;

public enum Direction {
	NORTH(net.minecraft.util.Direction.NORTH),
	SOUTH(net.minecraft.util.Direction.SOUTH),
	EAST(net.minecraft.util.Direction.EAST),
	WEST(net.minecraft.util.Direction.WEST),
	UP(net.minecraft.util.Direction.UP),
	DOWN(net.minecraft.util.Direction.DOWN);
	
	private final net.minecraft.util.Direction internal;
	
	private final Vector3i unitVector;
	
	Direction(final net.minecraft.util.Direction internal) {
		this.internal = internal;
		
		this.unitVector = Vector3i.get(internal.getNormal());
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
	
	public Vector3i getUnitVector() {
		return this.unitVector;
	}
	
	public net.minecraft.util.Direction getInternal() {
		return this.internal;
	}
}
