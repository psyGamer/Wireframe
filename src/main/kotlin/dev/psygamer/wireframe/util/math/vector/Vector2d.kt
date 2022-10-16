package dev.psygamer.wireframe.util.math.vector

import kotlin.math.*

open class Vector2d(val x: Double, val y: Double) {

	operator fun plus(other: Vector2i) = Vector2d(this.x + other.x, this.y + other.y)
	operator fun plus(other: Vector2f) = Vector2d(this.x + other.x, this.y + other.y)
	operator fun plus(other: Vector2d) = Vector2d(this.x + other.x, this.y + other.y)

	operator fun minus(other: Vector2i) = Vector2d(this.x - other.x, this.y - other.y)
	operator fun minus(other: Vector2f) = Vector2d(this.x - other.x, this.y - other.y)
	operator fun minus(other: Vector2d) = Vector2d(this.x - other.x, this.y - other.y)

	operator fun times(other: Vector2i) = Vector2d(this.x * other.x, this.y * other.y)
	operator fun times(other: Vector2f) = Vector2d(this.x * other.x, this.y * other.y)
	operator fun times(other: Vector2d) = Vector2d(this.x * other.x, this.y * other.y)

	operator fun times(scalar: Int) = Vector2d((this.x * scalar), (this.y * scalar))
	operator fun times(scalar: Float) = Vector2d((this.x * scalar), (this.y * scalar))
	operator fun times(scalar: Double) = Vector2d((this.x * scalar), (this.y * scalar))

	operator fun div(other: Vector2i) = Vector2d(this.x / other.x, y / other.y)
	operator fun div(other: Vector2f) = Vector2d(this.x / other.x, y / other.y)
	operator fun div(other: Vector2d) = Vector2d(this.x / other.x, this.y / other.y)

	operator fun div(scalar: Int) = Vector2d((this.x / scalar), (this.y / scalar))
	operator fun div(scalar: Float) = Vector2d((this.x / scalar), (this.y / scalar))
	operator fun div(scalar: Double) = Vector2d((this.x / scalar), (this.y / scalar))

	infix fun cross(other: Vector2i) = Vector2d(this.x * other.y, this.y * other.x)
	infix fun cross(other: Vector2f) = Vector2d(this.x * other.y, this.y * other.x)
	infix fun cross(other: Vector2d) = Vector2d(this.x * other.y, this.y * other.x)

	infix fun dot(other: Vector2i) = (this.x * other.x + this.y * other.y)
	infix fun dot(other: Vector2f) = (this.x * other.x + this.y * other.y)
	infix fun dot(other: Vector2d) = (this.x * other.x + this.y * other.y)

	fun distance(other: Vector2i) = (this - other).magnitude
	fun distance(other: Vector2f) = (this - other).magnitude
	fun distance(other: Vector2d) = (this - other).magnitude

	fun distanceSquared(other: Vector2i) = (this - other).magnitudeSquared
	fun distanceSquared(other: Vector2f) = (this - other).magnitudeSquared
	fun distanceSquared(other: Vector2d) = (this - other).magnitudeSquared

	fun angleBetween(other: Vector2i) = acos((this dot other) / (this.magnitude * other.magnitude))
	fun angleBetween(other: Vector2f) = acos((this dot other) / (this.magnitude * other.magnitude))
	fun angleBetween(other: Vector2d) = acos((this dot other) / (this.magnitude * other.magnitude))

	val magnitude get() = sqrt(this.x * this.x + this.y * this.y)
	val magnitudeSquared get() = (this.x * this.x + this.y * this.y)

	val normalized get() = this / this.magnitude
	val angle get() = atan2(this.x, this.y)
}