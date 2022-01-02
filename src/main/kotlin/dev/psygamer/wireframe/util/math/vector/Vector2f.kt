package dev.psygamer.wireframe.util.math.vector

data class Vector2f(val x: Float, val y: Float)

val net.minecraft.util.math.vector.Vector2f.wfWrapped: Vector2f
	get() = Vector2f(this.x, this.y)

val Vector2f.mcNative: net.minecraft.util.math.vector.Vector2f
	get() = net.minecraft.util.math.vector.Vector2f(this.x, this.y)

