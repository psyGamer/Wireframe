package dev.psygamer.wireframe.util.math

import kotlin.math.*

fun Int.clamp(min: Int = 0, max: Int): Int {
	return min(max, max(min, this))
}

fun UInt.clamp(min: UInt = 0u, max: UInt): UInt {
	return min(max, max(min, this))
}

fun Long.clamp(min: Long = 0, max: Long): Long {
	return min(max, max(min, this))
}

fun ULong.clamp(min: ULong = 0u, max: ULong): ULong {
	return min(max, max(min, this))
}

fun Float.clamp(min: Float = 0.0f, max: Float): Float {
	return min(max, max(min, this))
}

fun Double.clamp(min: Double = 0.0, max: Double): Double {
	return min(max, max(min, this))
}