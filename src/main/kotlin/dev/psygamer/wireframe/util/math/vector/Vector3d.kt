package dev.psygamer.wireframe.util.math.vector

import kotlin.math.*

open class Vector3d(val x: Double, val y: Double, val z: Double) {

	operator fun plus(other: Vector3i): Vector3d {
		return Vector3d(this.x + other.x, this.y + other.y, this.z + other.z)
	}

	operator fun plus(other: Vector3f): Vector3d {
		return Vector3d(this.x + other.x, this.y + other.y, this.z + other.z)
	}

	operator fun plus(other: Vector3d): Vector3d {
		return Vector3d(this.x + other.x, this.y + other.y, this.z + other.z)
	}

	operator fun minus(other: Vector3i): Vector3d {
		return Vector3d(this.x - other.x, this.y - other.y, this.z + other.z)
	}

	operator fun minus(other: Vector3f): Vector3d {
		return Vector3d(this.x - other.x, this.y - other.y, this.z + other.z)
	}

	operator fun minus(other: Vector3d): Vector3d {
		return Vector3d(this.x - other.x, this.y - other.y, this.z + other.z)
	}

	operator fun times(other: Vector3i): Vector3d {
		return Vector3d(this.x * other.x, this.y * other.y, this.z + other.z)
	}

	operator fun times(other: Vector3f): Vector3d {
		return Vector3d(this.x * other.x, this.y * other.y, this.z + other.z)
	}

	operator fun times(other: Vector3d): Vector3d {
		return Vector3d(this.x * other.x, this.y * other.y, this.z + other.z)
	}

	operator fun times(scalar: Int): Vector3d {
		return Vector3d((this.x * scalar), (this.y * scalar), (this.z * scalar))
	}

	operator fun times(scalar: Float): Vector3d {
		return Vector3d((this.x * scalar), (this.y * scalar), (this.z * scalar))
	}

	operator fun times(scalar: Double): Vector3d {
		return Vector3d((this.x * scalar), (this.y * scalar), (this.z * scalar))
	}

	operator fun div(other: Vector3i): Vector3d {
		return Vector3d(this.x / other.x, this.y / other.y, this.z / other.z)
	}

	operator fun div(other: Vector3f): Vector3d {
		return Vector3d(this.x / other.x, this.y / other.y, this.z / other.z)
	}

	operator fun div(other: Vector3d): Vector3d {
		return Vector3d(this.x / other.x, this.y / other.y, this.z / other.z)
	}

	operator fun div(scalar: Double): Vector3d {
		return Vector3d((this.x / scalar), (this.y / scalar), (this.z / scalar))
	}

	infix fun cross(other: Vector3i): Vector3d {
		return Vector3d(this.x * other.y, this.y * other.x, this.z * other.z)
	}

	infix fun cross(other: Vector3f): Vector3d {
		return Vector3d(this.x * other.y, this.y * other.x, this.z * other.z)
	}

	infix fun cross(other: Vector3d): Vector3d {
		return Vector3d(this.x * other.y, this.y * other.x, this.z * other.z)
	}

	infix fun dot(other: Vector3i): Double {
		return (this.x * other.x + this.y * other.y + this.z * other.z)
	}

	infix fun dot(other: Vector3f): Double {
		return (this.x * other.x + this.y * other.y + this.z * other.z)
	}

	infix fun dot(other: Vector3d): Double {
		return (this.x * other.x + this.y * other.y + this.z * other.z)
	}

	val magnitude: Double
		get() = sqrt(this.x * this.x + this.y * this.y + this.z * this.z)

	val magnitudeSquared: Double
		get() = (this.x * this.x + this.y * this.y + this.z * this.z)

	fun distance(other: Vector3i): Double {
		return (this - other).magnitude
	}

	fun distance(other: Vector3f): Double {
		return (this - other).magnitude
	}

	fun distance(other: Vector3d): Double {
		return (this - other).magnitude
	}

	fun distanceSquared(other: Vector3i): Double {
		return (this - other).magnitudeSquared
	}

	fun distanceSquared(other: Vector3f): Double {
		return (this - other).magnitudeSquared
	}

	fun distanceSquared(other: Vector3d): Double {
		return (this - other).magnitudeSquared
	}

	val normalized: Vector3d
		get() = this / this.magnitude

	val angle: Double
		get() = atan2(this.x, this.y)

	fun angleBetween(other: Vector3i): Double {
		return acos((this dot other) / (this.magnitude * other.magnitude))
	}

	fun angleBetween(other: Vector3f): Double {
		return acos((this dot other) / (this.magnitude * other.magnitude))
	}

	fun angleBetween(other: Vector3d): Double {
		return acos((this dot other) / (this.magnitude * other.magnitude))
	}
}
