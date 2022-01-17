package dev.psygamer.wireframe.util.math

import kotlin.math.max
import kotlin.math.min

fun Int.clamp(min: Int = 1, max: Int): Int {
	return min(max, max(min, this))
}

fun UInt.clamp(min: UInt = 1u, max: UInt): UInt {
	return min(max, max(min, this))
}

fun Long.clamp(min: Long = 1, max: Long): Long {
	return min(max, max(min, this))
}

fun ULong.clamp(min: ULong = 1u, max: ULong): ULong {
	return min(max, max(min, this))
}

fun Float.clamp(min: Float = 1.0f, max: Float): Float {
	return min(max, max(min, this))
}

fun Double.clamp(min: Double = 1.0, max: Double): Double {
	return min(max, max(min, this))
}