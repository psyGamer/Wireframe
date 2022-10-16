package dev.psygamer.wireframe.util.math.vector

import kotlin.math.*

open class Vector3f(val x: Float, val y: Float, val z: Float) {

	operator fun plus(scalar: Int) = Vector3f(this.x + scalar, this.y + scalar, this.z + scalar)
	operator fun plus(scalar: Float) = Vector3f(this.x + scalar, this.y + scalar, this.z + scalar)
	operator fun plus(scalar: Double) = Vector3d(this.x + scalar, this.y + scalar, this.z + scalar)

	operator fun plus(other: Vector3i) = Vector3f(this.x + other.x, this.y + other.y, this.z + other.z)
	operator fun plus(other: Vector3f) = Vector3f(this.x + other.x, this.y + other.y, this.z + other.z)
	operator fun plus(other: Vector3d) = Vector3d(this.x + other.x, this.y + other.y, this.z + other.z)

	operator fun minus(scalar: Int) = Vector3f(this.x - scalar, this.y - scalar, this.z - scalar)
	operator fun minus(scalar: Float) = Vector3f(this.x - scalar, this.y - scalar, this.z - scalar)
	operator fun minus(scalar: Double) = Vector3d(this.x - scalar, this.y - scalar, this.z - scalar)

	operator fun minus(other: Vector3i) = Vector3f(this.x - other.x, this.y - other.y, this.z - other.z)
	operator fun minus(other: Vector3f) = Vector3f(this.x - other.x, this.y - other.y, this.z - other.z)
	operator fun minus(other: Vector3d) = Vector3d(this.x - other.x, this.y - other.y, this.z - other.z)

	operator fun times(scalar: Int) = Vector3f(this.x * scalar, this.y * scalar, this.z * scalar)
	operator fun times(scalar: Float) = Vector3f(this.x * scalar, this.y * scalar, this.z * scalar)
	operator fun times(scalar: Double) = Vector3d(this.x * scalar, this.y * scalar, this.z * scalar)

	operator fun times(other: Vector3i) = Vector3f(this.x * other.x, this.y * other.y, this.z * other.z)
	operator fun times(other: Vector3f) = Vector3f(this.x * other.x, this.y * other.y, this.z * other.z)
	operator fun times(other: Vector3d) = Vector3d(this.x * other.x, this.y * other.y, this.z * other.z)

	operator fun div(other: Vector3i) = Vector3f(this.x / other.x, this.y / other.y, this.z / other.z)
	operator fun div(other: Vector3f) = Vector3f(this.x / other.x, this.y / other.y, this.z / other.z)
	operator fun div(other: Vector3d) = Vector3d(this.x / other.x, this.y / other.y, this.z / other.z)

	operator fun div(scalar: Int) = Vector3f(this.x / scalar, this.y / scalar, this.z / scalar)
	operator fun div(scalar: Float) = Vector3f(this.x / scalar, this.y / scalar, this.z / scalar)
	operator fun div(scalar: Double) = Vector3d(this.x / scalar, this.y / scalar, this.z / scalar)

	infix fun cross(other: Vector3i) = Vector3f(this.x * other.y, this.y * other.x, this.z * other.z)
	infix fun cross(other: Vector3f) = Vector3f(this.x * other.y, this.y * other.x, this.z * other.z)
	infix fun cross(other: Vector3d) = Vector3d(this.x * other.y, this.y * other.x, this.z * other.z)

	infix fun dot(other: Vector3i) = (this.x * other.x + this.y * other.y + this.z * other.z)
	infix fun dot(other: Vector3f) = (this.x * other.x + this.y * other.y + this.z * other.z)
	infix fun dot(other: Vector3d) = (this.x * other.x + this.y * other.y + this.z * other.z)

	fun distance(other: Vector3i) = (this - other).magnitude
	fun distance(other: Vector3f) = (this - other).magnitude
	fun distance(other: Vector3d) = (this - other).magnitude
	fun distanceSquared(other: Vector3i) = (this - other).magnitudeSquared
	fun distanceSquared(other: Vector3f) = (this - other).magnitudeSquared
	fun distanceSquared(other: Vector3d) = (this - other).magnitudeSquared

	fun angleBetween(other: Vector3i) = acos(((this dot other) / (this.magnitude * other.magnitude).toInt()))
	fun angleBetween(other: Vector3f) = acos(((this dot other) / (this.magnitude * other.magnitude).toInt()))
	fun angleBetween(other: Vector3d) = acos(((this dot other) / (this.magnitude * other.magnitude).toInt()).toFloat())

	val magnitude get() = sqrt((this.x * this.x + this.y * this.y))
	val magnitudeSquared get() = (this.x * this.x + this.y * this.y)

	val normalized get() = this / this.magnitude
	val angle get() = atan2(this.x, this.y)
}
