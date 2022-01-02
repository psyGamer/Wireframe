package dev.psygamer.wireframe.util.math.vector

import kotlin.math.acos
import kotlin.math.atan2
import kotlin.math.sqrt

open class Vector2f(val x: Float, val y: Float) {
	operator fun plus(other: Vector2f): Vector2f {
		return Vector2f(this.x + other.x, this.y + other.y)
	}
	
	operator fun minus(other: Vector2f): Vector2f {
		return Vector2f(this.x - other.x, this.y - other.y)
	}
	
	operator fun times(other: Vector2f): Vector2f {
		return Vector2f(this.x * other.x, this.y * other.y)
	}
	
	operator fun times(scalar: Float): Vector2f {
		return Vector2f((this.x * scalar), (this.y * scalar))
	}
	
	operator fun div(other: Vector2f): Vector2f {
		return Vector2f(this.x / other.x, y / other.y)
	}
	
	operator fun div(scalar: Float): Vector2f {
		return Vector2f((this.x / scalar), (this.y / scalar))
	}
	
	infix fun cross(other: Vector2f): Vector2f {
		return Vector2f(this.x * other.y, this.y * other.x)
	}
	
	infix fun dot(other: Vector2f): Float {
		return (this.x * other.x + this.y * other.y)
	}
	
	val magnitude: Float
		get() = sqrt(this.x * this.x + this.y * this.y)
	
	val magnitudeSquared: Float
		get() = this.x * this.x + this.y * this.y
	
	fun distance(other: Vector2f): Float {
		return (this - other).magnitude
	}
	
	fun distanceSquared(other: Vector2f): Float {
		return (this - other).magnitudeSquared
	}
	
	val normalized: Vector2f
		get() = this / this.magnitude
	
	val angle: Float
		get() = atan2(this.x, this.y)
	
	fun angleBetween(other: Vector2f): Float {
		return acos(this dot other / (this.magnitude * other.magnitude))
	}
}

val net.minecraft.util.math.vector.Vector2f.wfWrapped: Vector2f
	get() = Vector2f(this.x, this.y)

val Vector2f.mcNative: net.minecraft.util.math.vector.Vector2f
	get() = net.minecraft.util.math.vector.Vector2f(this.x, this.y)

