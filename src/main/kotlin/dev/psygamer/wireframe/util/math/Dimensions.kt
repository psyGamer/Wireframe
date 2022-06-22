package dev.psygamer.wireframe.util.math

open class Dimension2I(open val width: Int, open val height: Int)
open class Dimension2F(open val width: Float, open val height: Float)
open class Dimension2D(open val width: Double, open val height: Double)

open class Dimension3I(open val width: Int, open val height: Int, open val depth: Int)
open class Dimension3F(open val width: Float, open val height: Float, open val depth: Float)
open class Dimension3D(open val width: Double, open val height: Double, open val depth: Double)

data class MutableDimension2I(override var width: Int, override var height: Int) : Dimension2I(width, height)
data class MutableDimension2F(override var width: Float, override var height: Float) : Dimension2F(width, height)
data class MutableDimension2D(override var width: Double, override var height: Double) : Dimension2D(width, height)

data class MutableDimension3I(override var width: Int, override var height: Int, override var depth: Int) : Dimension3I(width, height, depth)
data class MutableDimension3F(override var width: Float, override var height: Float, override var depth: Float) : Dimension3F(width, height, depth)
data class MutableDimension3D(override var width: Double, override var height: Double, override var depth: Double) : Dimension3D(width, height, depth)

fun MutableDimension2I.asImmutable(): Dimension2I = Dimension2I(width, height)
fun MutableDimension2F.asImmutable(): Dimension2F = Dimension2F(width, height)
fun MutableDimension2D.asImmutable(): Dimension2D = Dimension2D(width, height)

fun MutableDimension3I.asImmutable(): Dimension3I = Dimension3I(width, height, depth)
fun MutableDimension3F.asImmutable(): Dimension3F = Dimension3F(width, height, depth)
fun MutableDimension3D.asImmutable(): Dimension3D = Dimension3D(width, height, depth)