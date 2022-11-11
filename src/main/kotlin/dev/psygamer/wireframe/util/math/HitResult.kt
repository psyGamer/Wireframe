package dev.psygamer.wireframe.util.math

import org.joml.Vector3d
import kotlin.math.sqrt

abstract class HitResult protected constructor(val location: Vector3d) {
	enum class Type {
		MISS, BLOCK, ENTITY
	}

	fun distanceTo(other: Vector3d): Double {
		return sqrt(squaredDistanceTo(other))
	}

	fun squaredDistanceTo(other: Vector3d): Double {
		val xDiff = location.x - other.x
		val yDiff = location.y - other.y
		val zDiff = location.z - other.z
		return xDiff * xDiff + yDiff * yDiff + zDiff * zDiff
	}
}