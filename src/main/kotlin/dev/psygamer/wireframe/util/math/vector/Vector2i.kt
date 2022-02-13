package dev.psygamer.wireframe.util.math.vector

import kotlin.math.acos
import kotlin.math.atan2
import kotlin.math.sqrt

open class Vector2i(val x: Int, val y: Int) {
	
	companion object {
		
		@JvmStatic
		val ZERO = Vector2i(0, 0)
		
		@JvmStatic
		val ONE = Vector2i(1, 1)
	}
	
	operator fun plus(other: Vector2i): Vector2i {
		return Vector2i(this.x + other.x, this.y + other.y)
	}
	
	operator fun minus(other: Vector2i): Vector2i {
		return Vector2i(this.x - other.x, this.y - other.y)
	}
	
	operator fun times(scalar: Float): Vector2i {
		return Vector2i((this.x * scalar).toInt(), (this.y * scalar).toInt())
	}
	
	operator fun times(scalar: Int): Vector2i {
		return Vector2i((this.x * scalar), (this.y * scalar))
	}
	
	operator fun times(other: Vector2i): Vector2i {
		return Vector2i(this.x * other.x, this.y * other.y)
	}
	
	operator fun div(other: Vector2i): Vector2i {
		return Vector2i(this.x / other.x, this.y / other.y)
	}
	
	operator fun div(scalar: Int): Vector2i {
		return Vector2i((this.x / scalar), (this.y / scalar))
	}
	
	operator fun div(scalar: Float): Vector2i {
		return Vector2i((this.x / scalar).toInt(), (this.y / scalar).toInt())
	}
	
	infix fun cross(other: Vector2i): Vector2i {
		return Vector2i(this.x * other.y, this.y * other.x)
	}
	
	infix fun dot(other: Vector2i): Int {
		return (this.x * other.x + this.y * other.y)
	}
	
	val magnitude: Float
		get() = sqrt((this.x * this.x + this.y * this.y).toFloat())
	
	val magnitudeSquared: Float
		get() = (this.x * this.x + this.y * this.y).toFloat()
	
	fun distance(other: Vector2i): Float {
		return (this - other).magnitude
	}
	
	fun distanceSquared(other: Vector2i): Float {
		return (this - other).magnitudeSquared
	}
	
	val normalized: Vector2i
		get() = this / this.magnitude
	
	val angle: Float
		get() = atan2(this.x.toFloat(), this.y.toFloat())
	
	fun angleBetween(other: Vector2i): Float {
		return acos((this dot other / (this.magnitude * other.magnitude).toInt()).toFloat())
	}
}