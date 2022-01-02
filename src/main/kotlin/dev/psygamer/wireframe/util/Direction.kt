package dev.psygamer.wireframe.util

import dev.psygamer.wireframe.util.math.vector.Vector3i

enum class Direction(val unitVector: Vector3i) {
	SOUTH(Vector3i(0, 0, +1)), // Positive Z
	NORTH(Vector3i(0, 0, -1)), // Negative Z
	
	EAST (Vector3i(+1, 0, 0)), // Positive X
	WEST (Vector3i(-1, 0, 0)), // Negative X
	
	UP	 (Vector3i(0, +1, 0)), // Positive Y
	DOWN (Vector3i(0, -1, 0)); // Negative Y
}

val net.minecraft.util.Direction.wfWrapped: Direction
	get() {
		return when (this) {
			net.minecraft.util.Direction.SOUTH -> Direction.SOUTH
			net.minecraft.util.Direction.NORTH -> Direction.NORTH
			net.minecraft.util.Direction.EAST  -> Direction.EAST
			net.minecraft.util.Direction.WEST  -> Direction.WEST
			net.minecraft.util.Direction.UP    -> Direction.UP
			net.minecraft.util.Direction.DOWN  -> Direction.DOWN
		}
	}

val Direction.mcNative: net.minecraft.util.Direction
	get() {
		return when (this) {
			Direction.SOUTH -> net.minecraft.util.Direction.SOUTH
			Direction.NORTH -> net.minecraft.util.Direction.NORTH
			Direction.EAST  -> net.minecraft.util.Direction.EAST
			Direction.WEST  -> net.minecraft.util.Direction.WEST
			Direction.UP    -> net.minecraft.util.Direction.UP
			Direction.DOWN  -> net.minecraft.util.Direction.DOWN
		}
	}