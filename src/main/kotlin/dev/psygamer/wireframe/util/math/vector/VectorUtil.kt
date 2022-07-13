package dev.psygamer.wireframe.util.math.vector

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import dev.psygamer.wireframe.util.math.clamp

object VectorUtil {
	
	@JvmStatic
	fun eulerToQuaternion(v: Vector3f) =
		eulerToQuaternion(v.x, v.y, v.z)
	
	@JvmStatic
			/** See: [EuclideanSpace - Conversion Euler to Quaternion](http://www.euclideanspace.com/maths/geometry/rotations/conversions/eulerToQuaternion/index.htm) */
	fun eulerToQuaternion(x: Float, y: Float, z: Float): Quaternion {
		val sinX = sin(x / 2.0f)
		val cosX = cos(x / 2.0f)
		val sinY = sin(y / 2.0f)
		val cosY = cos(y / 2.0f)
		val sinZ = sin(z / 2.0f)
		val cosZ = cos(z / 2.0f)
		
		return Quaternion(
			x = sinZ * cosY * cosX - cosZ * sinY * sinX,
			y = cosZ * sinY * cosX + sinZ * cosY * sinX,
			z = cosZ * cosY * sinX - sinZ * sinY * cosX,
			w = cosZ * cosY * cosX + sinZ * sinY * sinX
		)
	}
	
	@JvmStatic
	fun quaternionToEuler(q: Quaternion): Vector3f {
		val x0 = 2.0f * (q.w * q.z + q.w * q.y)
		val x1 = 1.0f - 2.0f * (q.y * q.y + q.z * q.z)
		
		val z0 = 2.0f * (q.w * q.x + q.y * q.z)
		val z1 = 1.0f - 2.0f * (q.x * q.x + q.y * q.y)
		
		return Vector3f(
			x = atan2(x0, x1),
			z = atan2(z0, z1),
			y = (2.0f * (q.w * q.y - q.z * q.x)).clamp(min = -1.0f, max = 1.0f)
		)
	}
	
	// Conversions
	
	fun Vector2i.asVector2f() = Vector2f(this.x.toFloat(), this.y.toFloat())
	fun Vector2i.asVector2d() = Vector2d(this.x.toDouble(), this.y.toDouble())
	
	fun Vector2f.asVector2d() = Vector2d(this.x.toDouble(), this.y.toDouble())
	fun Vector2f.asVector2i() = Vector2i(this.x.roundToInt(), this.y.roundToInt())
	
	fun Vector2d.asVector2f() = Vector2f(this.x.toFloat(), this.y.toFloat())
	fun Vector2d.asVector2i() = Vector2i(this.x.roundToInt(), this.y.roundToInt())
	
	fun Vector3i.asVector3f() = Vector3f(this.x.toFloat(), this.y.toFloat(), this.z.toFloat())
	fun Vector3i.asVector3d() = Vector3d(this.x.toDouble(), this.y.toDouble(), this.z.toDouble())
	
	fun Vector3f.asVector3d() = Vector3d(this.x.toDouble(), this.y.toDouble(), this.z.toDouble())
	fun Vector3f.asVector3i() = Vector3i(this.x.roundToInt(), this.y.roundToInt(), this.z.roundToInt())
	
	fun Vector3d.asVector3f() = Vector3f(this.x.toFloat(), this.y.toFloat(), this.z.toFloat())
	fun Vector3d.asVector3i() = Vector3i(this.x.roundToInt(), this.y.roundToInt(), this.z.roundToInt())
	
	fun Vector3i.asQuaternion(): Quaternion = eulerToQuaternion(this.asVector3f())
	fun Vector3f.asQuaternion(): Quaternion = eulerToQuaternion(this)
	fun Vector3d.asQuaternion(): Quaternion = eulerToQuaternion(this.asVector3f())
	fun Quaternion.asEulerAngles(): Vector3f = quaternionToEuler(this)
}