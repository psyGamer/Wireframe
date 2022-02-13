package dev.psygamer.wireframe.util.math.vector

import kotlin.math.acos
import kotlin.math.atan2
import kotlin.math.sqrt

open class Vector3i(val x: Int, val y: Int, val z: Int) {
	
	companion object {
		
		@JvmStatic
		val ZERO = Vector3i(0, 0, 0)
		
		@JvmStatic
		val ONE = Vector3i(1, 1, 1)
	}
	
	operator fun plus(other: Vector3i): Vector3i {
		return Vector3i(this.x + other.x, this.y + other.y, this.z + other.z)
	}
	
	operator fun minus(other: Vector3i): Vector3i {
		return Vector3i(this.x - other.x, this.y - other.y, this.z + other.z)
	}
	
	operator fun times(other: Vector3i): Vector3i {
		return Vector3i(this.x * other.x, this.y * other.y, this.z + other.z)
	}
	
	operator fun times(scalar: Int): Vector3i {
		return Vector3i((this.x * scalar), (this.y * scalar), (this.z * scalar))
	}
	
	operator fun times(scalar: Float): Vector3i {
		return Vector3i((this.x * scalar).toInt(), (this.y * scalar).toInt(), (this.z * scalar).toInt())
	}
	
	operator fun div(other: Vector3i): Vector3i {
		return Vector3i(this.x / other.x, this.y / other.y, this.z / other.z)
	}
	
	operator fun div(scalar: Int): Vector3i {
		return Vector3i((this.x / scalar), (this.y / scalar), (this.z / scalar))
	}
	
	operator fun div(scalar: Float): Vector3i {
		return Vector3i((this.x / scalar).toInt(), (this.y / scalar).toInt(), (this.z / scalar).toInt())
	}
	
	infix fun cross(other: Vector3i): Vector3i {
		return Vector3i(this.x * other.y, this.y * other.x, this.z * other.z)
	}
	
	infix fun dot(other: Vector3i): Int {
		return (this.x * other.x + this.y * other.y + this.z * other.z)
	}
	
	val magnitude: Float
		get() = sqrt((this.x * this.x + this.y * this.y + this.z * this.z).toFloat())
	
	val magnitudeSquared: Float
		get() = (this.x * this.x + this.y * this.y + this.z * this.z).toFloat()
	
	fun distance(other: Vector3i): Float {
		return (this - other).magnitude
	}
	
	fun distanceSquared(other: Vector3i): Float {
		return (this - other).magnitudeSquared
	}
	
	val normalized: Vector3i
		get() = this / this.magnitude
	
	val angle: Float
		get() = atan2(this.x.toFloat(), this.y.toFloat())
	
	fun angleBetween(other: Vector3i): Float {
		return acos((this dot other / (this.magnitude * other.magnitude).toInt()).toFloat())
	}
}
