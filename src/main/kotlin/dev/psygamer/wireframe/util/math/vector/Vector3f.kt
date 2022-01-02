package dev.psygamer.wireframe.util.math.vector

data class Vector3f(val x: Float, val y: Float, val z: Float)

val net.minecraft.util.math.vector.Vector3f.wfWrapped: Vector3f
	get() = Vector3f(this.x(), this.y(), this.z())

val Vector3f.mcNative: net.minecraft.util.math.vector.Vector3f
	get() = net.minecraft.util.math.vector.Vector3f(this.x, this.y, this.z)