package dev.psygamer.wireframe.util;

import dev.psygamer.wireframe.util.math.vector.Vector3i;

public enum Direction {
	NORTH	(new Vector3i(0, 0, -1), net.minecraft.util.Direction.NORTH),
	SOUTH	(new Vector3i(0, 0, +1), net.minecraft.util.Direction.SOUTH),
	EAST	(new Vector3i(+1, 0, 0), net.minecraft.util.Direction.EAST),
	WEST	(new Vector3i(-1, 0, 0), net.minecraft.util.Direction.WEST),
	UP		(new Vector3i(0, +1, 0), net.minecraft.util.Direction.UP),
	DOWN	(new Vector3i(0, -1, 0), net.minecraft.util.Direction.DOWN);
	
	private final net.minecraft.util.Direction internal;
	
	private final Vector3i unitVector;
	
	Direction(final Vector3i unitVector, final net.minecraft.util.Direction internal) {
		this.internal = internal;
		
		this.unitVector = unitVector;
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
