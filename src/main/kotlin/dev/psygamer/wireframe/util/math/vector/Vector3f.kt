package dev.psygamer.wireframe.util.math.vector

import kotlin.math.*

open class Vector3f(val x: Float, val y: Float, val z: Float) {

	operator fun plus(other: Vector3f): Vector3f {
		return Vector3f(this.x + other.x, this.y + other.y, this.z + other.z)
	}

	operator fun minus(other: Vector3f): Vector3f {
		return Vector3f(this.x - other.x, this.y - other.y, this.z + other.z)
	}

	operator fun times(other: Vector3f): Vector3f {
		return Vector3f(this.x * other.x, this.y * other.y, this.z + other.z)
	}

	operator fun times(scalar: Float): Vector3f {
		return Vector3f((this.x * scalar), (this.y * scalar), (this.z * scalar))
	}

	operator fun div(other: Vector3f): Vector3f {
		return Vector3f(this.x / other.x, this.y / other.y, this.z / other.z)
	}

	operator fun div(scalar: Float): Vector3f {
		return Vector3f((this.x / scalar), (this.y / scalar), (this.z / scalar))
	}

	infix fun cross(other: Vector3f): Vector3f {
		return Vector3f(this.x * other.y, this.y * other.x, this.z * other.z)
	}

	infix fun dot(other: Vector3f): Float {
		return (this.x * other.x + this.y * other.y + this.z * other.z)
	}

	val magnitude: Float
		get() = sqrt(this.x * this.x + this.y * this.y + this.z * this.z)

	val magnitudeSquared: Float
		get() = (this.x * this.x + this.y * this.y + this.z * this.z)

	fun distance(other: Vector3f): Float {
		return (this - other).magnitude
	}

	fun distanceSquared(other: Vector3f): Float {
		return (this - other).magnitudeSquared
	}

	val normalized: Vector3f
		get() = this / this.magnitude

	val angle: Float
		get() = atan2(this.x, this.y)

	fun angleBetween(other: Vector3f): Float {
		return acos(this dot other / (this.magnitude * other.magnitude))
	}
}
