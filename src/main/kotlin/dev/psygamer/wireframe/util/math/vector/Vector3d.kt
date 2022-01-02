package dev.psygamer.wireframe.util.math.vector

data class Vector3d(val x: Double, val y: Double, val z: Double)

val net.minecraft.util.math.vector.Vector3d.wfWrapped: Vector3d
	get() = Vector3d(this.x, this.y, this.z)

val Vector3d.mcNative: net.minecraft.util.math.vector.Vector3d
	get() = net.minecraft.util.math.vector.Vector3d(this.x, this.y, this.z)