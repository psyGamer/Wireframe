package dev.psygamer.wireframe.api.client.render

import java.io.Closeable
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.math.vector.Vector3d
import dev.psygamer.wireframe.util.math.vector.Vector3f
import dev.psygamer.wireframe.util.math.vector.Vector3i
import dev.psygamer.wireframe.util.math.vector.VectorUtil
import dev.psygamer.wireframe.util.math.vector.VectorUtil.asQuaternion

class MatrixStack {
	
	internal val mcNative: com.mojang.blaze3d.matrix.MatrixStack
	
	internal constructor(mcNative: com.mojang.blaze3d.matrix.MatrixStack) {
		this.mcNative = mcNative
	}
	
	constructor() {
		this.mcNative = com.mojang.blaze3d.matrix.MatrixStack()
	}
	
	fun push(): Closeable {
		this.mcNative.pushPose()
		
		return Closeable {
			this.mcNative.popPose()
		}
	}
	
	fun pop() =
		this.mcNative.popPose()
	
	fun translate(x: Int, y: Int, z: Int) =
		this.mcNative.translate(x.toDouble(), y.toDouble(), z.toDouble())
	
	fun translate(x: Float, y: Float, z: Float) =
		this.mcNative.translate(x.toDouble(), y.toDouble(), z.toDouble())
	
	fun translate(x: Double, y: Double, z: Double) =
		this.mcNative.translate(x, y, z)
	
	fun translate(translation: Vector3i) =
		this.mcNative.translate(translation.x.toDouble(), translation.y.toDouble(), translation.z.toDouble())
	
	fun translate(translation: Vector3f) =
		this.mcNative.translate(translation.x.toDouble(), translation.y.toDouble(), translation.z.toDouble())
	
	fun translate(translation: Vector3d) =
		this.mcNative.translate(translation.x, translation.y, translation.z)
	
	fun rotate(x: Float, y: Float, z: Float) =
		this.mcNative.mulPose(VectorUtil.eulerToQuaternion(x, y, z).mcNative)
	
	fun rotate(x: Double, y: Double, z: Double) =
		this.mcNative.mulPose(VectorUtil.eulerToQuaternion(x.toFloat(), y.toFloat(), z.toFloat()).mcNative)
	
	fun rotate(rotation: Vector3f) =
		this.mcNative.mulPose(rotation.asQuaternion().mcNative)
	
	fun rotate(rotation: Vector3d) =
		this.mcNative.mulPose(rotation.asQuaternion().mcNative)
	
	fun scale(x: Int, y: Int, z: Int) =
		this.mcNative.scale(x.toFloat(), y.toFloat(), z.toFloat())
	
	fun scale(x: Float, y: Float, z: Float) =
		this.mcNative.scale(x, y, z)
	
	fun scale(x: Double, y: Double, z: Double) =
		this.mcNative.scale(x.toFloat(), y.toFloat(), z.toFloat())
	
	fun scale(scale: Vector3i) =
		this.mcNative.scale(scale.x.toFloat(), scale.y.toFloat(), scale.z.toFloat())
	
	fun scale(scale: Vector3f) =
		this.mcNative.scale(scale.x, scale.y, scale.z)
	
	fun scale(scale: Vector3d) =
		this.mcNative.scale(scale.x.toFloat(), scale.y.toFloat(), scale.z.toFloat())
	
}