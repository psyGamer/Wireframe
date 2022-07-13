package dev.psygamer.wireframe.util.math.vector

import kotlin.math.*

open class Vector2d(val x: Double, val y: Double) {
	
	companion object {
		
		@JvmStatic
		val ZERO = Vector2d(0.0, 0.0)
		
		@JvmStatic
		val ONE = Vector2d(1.0, 1.0)
	}
	
	operator fun plus(other: Vector2d): Vector2d {
		return Vector2d(this.x + other.x, this.y + other.y)
	}

	operator fun minus(other: Vector2d): Vector2d {
		return Vector2d(this.x - other.x, this.y - other.y)
	}

	operator fun times(other: Vector2d): Vector2d {
		return Vector2d(this.x * other.x, this.y * other.y)
	}

	operator fun times(scalar: Double): Vector2d {
		return Vector2d((this.x * scalar), (this.y * scalar))
	}

	operator fun div(other: Vector2d): Vector2d {
		return Vector2d(this.x / other.x, this.y / other.y)
	}

	operator fun div(scalar: Double): Vector2d {
		return Vector2d((this.x / scalar), (this.y / scalar))
	}

	infix fun cross(other: Vector2d): Vector2d {
		return Vector2d(this.x * other.y, this.y * other.x)
	}

	infix fun dot(other: Vector2d): Double {
		return (this.x * other.x + this.y * other.y)
	}

	val magnitude: Double
		get() = sqrt(this.x * this.x + this.y * this.y)

	val magnitudeSquared: Double
		get() = (this.x * this.x + this.y * this.y)

	fun distance(other: Vector2d): Double {
		return (this - other).magnitude
	}

	fun distanceSquared(other: Vector2d): Double {
		return (this - other).magnitudeSquared
	}

	val normalized: Vector2d
		get() = this / this.magnitude

	val angle: Double
		get() = atan2(this.x, this.y)

	fun angleBetween(other: Vector2d): Double {
		return acos(this dot other / (this.magnitude * other.magnitude))
	}
	
	val inverted
		get() = Vector2d(-this.x, -this.y)
}