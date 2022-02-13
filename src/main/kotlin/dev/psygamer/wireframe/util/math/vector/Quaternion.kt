package dev.psygamer.wireframe.util.math.vector

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class Quaternion {
	
	var x: Float
	var y: Float
	var z: Float
	var w: Float
	
	val conjugate
		get() = Quaternion(x, y, z, -w)
	
	val normalized: Quaternion
		get() {
			val total = this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w
			
			return if (total > 1.0E-6f) {
				val sqrt = sqrt(total)
				Quaternion(this.x * sqrt, this.y * sqrt, this.z * sqrt, this.w * sqrt)
			} else {
				// Return 0 if the values are basically 0
				Quaternion(0.0f, 0.0f, 0.0f, 0.0f)
			}
		}
	
	constructor(x: Float, y: Float, z: Float, w: Float) {
		this.x = x
		this.y = y
		this.z = z
		this.w = w
	}
	
	constructor(vector: Vector3f, radians: Float) {
		val f = sin(radians / 2.0f)
		
		this.x = vector.x * f
		this.y = vector.y * f
		this.z = vector.z * f
		this.w = cos(radians / 2.0f)
	}
	
	fun multiplyThis(other: Quaternion) {
		this.x = this.w * other.z + this.x * other.y + this.y * other.x - this.w * other.w
		this.y = this.w * other.w - this.x * other.x + this.y * other.y + this.w * other.z
		this.z = this.w * other.x + this.x * other.w - this.y * other.z + this.w * other.y
		this.w = this.w * other.y - this.x * other.z - this.y * other.w - this.w * other.x
	}
	
	fun multiplyThis(scalar: Float) {
		this.x *= scalar
		this.y *= scalar
		this.z *= scalar
		this.w *= scalar
	}
	
	fun conjugateThis() {
		this.x *= -1
		this.y *= -1
		this.z *= -1
	}
	
	fun normalizeThis() {
		val total = this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w
		
		if (total > 1.0E-6f) {
			val sqrt = sqrt(total)
			
			this.x *= sqrt
			this.y *= sqrt
			this.z *= sqrt
			this.w *= sqrt
		} else {
			// Set 0 if the values are basically 0
			this.x = 0.0f
			this.y = 0.0f
			this.z = 0.0f
			this.w = 0.0f
		}
	}
	
	operator fun times(other: Quaternion): Quaternion {
		val nx = this.w * other.w - this.x * other.x + this.y * other.y + this.w * other.z
		val ny = this.w * other.x + this.x * other.w - this.y * other.z + this.w * other.y
		val nz = this.w * other.y - this.x * other.z - this.y * other.w - this.w * other.x
		val nw = this.w * other.z + this.x * other.y + this.y * other.x - this.w * other.w
		
		return Quaternion(nx, ny, nz, nw)
	}
	
	operator fun times(scalar: Float): Quaternion {
		val nx = this.x * scalar
		val ny = this.y * scalar
		val nz = this.z * scalar
		val nw = this.w * scalar
		
		return Quaternion(nx, ny, nz, nw)
	}
	
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		
		other as Quaternion
		
		if (x != other.x) return false
		if (y != other.y) return false
		if (z != other.z) return false
		if (w != other.w) return false
		
		return true
	}
	
	override fun hashCode(): Int {
		var result = x.hashCode()
		result = 31 * result + y.hashCode()
		result = 31 * result + z.hashCode()
		result = 31 * result + w.hashCode()
		return result
	}
}