package dev.psygamer.wireframe.util

import dev.psygamer.wireframe.util.math.vector.Vector3i

enum class Direction(val unitVector: Vector3i) {
	SOUTH(Vector3i(0, 0, +1)), // Positive Z
	NORTH(Vector3i(0, 0, -1)), // Negative Z

	EAST(Vector3i(+1, 0, 0)), // Positive X
	WEST(Vector3i(-1, 0, 0)), // Negative X

	UP(Vector3i(0, +1, 0)), // Positive Y
	DOWN(Vector3i(0, -1, 0)); // Negative Y
}