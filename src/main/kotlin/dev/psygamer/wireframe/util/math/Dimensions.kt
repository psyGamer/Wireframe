package dev.psygamer.wireframe.util.math

data class Dimension2I(val width: Int, val height: Int)
data class Dimension2F(val width: Float, val height: Float)
data class Dimension2D(val width: Double, val height: Double)

data class Dimension3I(val width: Int, val height: Int, val depth: Int)
data class Dimension3F(val width: Float, val height: Float, val depth: Float)
data class Dimension3D(val width: Double, val height: Double, val depth: Double)

data class MutableDimension2I(var width: Int, var height: Int)
data class MutableDimension2F(var width: Float, var height: Float)
data class MutableDimension2D(var width: Double, var height: Double)

data class MutableDimension3I(var width: Int, var height: Int, var depth: Int)
data class MutableDimension3F(var width: Float, var height: Float, var depth: Float)
data class MutableDimension3D(var width: Double, var height: Double, var depth: Double)

fun MutableDimension2I.asImmutable(): Dimension2I = Dimension2I(width, height)
fun MutableDimension2F.asImmutable(): Dimension2F = Dimension2F(width, height)
fun MutableDimension2D.asImmutable(): Dimension2D = Dimension2D(width, height)

fun MutableDimension3I.asImmutable(): Dimension3I = Dimension3I(width, height, depth)
fun MutableDimension3F.asImmutable(): Dimension3F = Dimension3F(width, height, depth)
fun MutableDimension3D.asImmutable(): Dimension3D = Dimension3D(width, height, depth)