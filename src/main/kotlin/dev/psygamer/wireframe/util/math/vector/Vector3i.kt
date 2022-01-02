package dev.psygamer.wireframe.util.math.vector

open class Vector3i(val x: Int, val y: Int, val z: Int)

val net.minecraft.util.math.vector.Vector3i.wfWrapped: Vector3i
	get() = Vector3i(this.x, this.y, this.z)

val Vector3i.mcNative: net.minecraft.util.math.vector.Vector3i
	get() = net.minecraft.util.math.vector.Vector3i(this.x, this.y, this.z)