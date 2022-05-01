package dev.psygamer.wireframe.api.client.render

import java.io.Closeable
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.math.vector.*
import dev.psygamer.wireframe.util.math.vector.VectorUtil.asQuaternion

class PoseStack {
	
	internal val mcNative: com.mojang.blaze3d.matrix.MatrixStack
	
	internal constructor(mcNative: com.mojang.blaze3d.matrix.MatrixStack) {
		this.mcNative = mcNative
	}
	
	constructor() {
		this.mcNative = com.mojang.blaze3d.matrix.MatrixStack()
	}
	
	fun push(): Closeable {
		this.mcNative.pushPose()
		return Closeable { this.mcNative.popPose() }
	}
	
	fun pop() =
		this.mcNative.popPose()
	
	fun translate(x: Int, y: Int, z: Int): Closeable {
		this.mcNative.translate(x.toDouble(), y.toDouble(), z.toDouble())
		return Closeable { this.mcNative.translate(-x.toDouble(), -y.toDouble(), -z.toDouble()) }
	}
	
	fun translate(x: Float, y: Float, z: Float): Closeable {
		this.mcNative.translate(x.toDouble(), y.toDouble(), z.toDouble())
		return Closeable { this.mcNative.translate(-x.toDouble(), -y.toDouble(), -z.toDouble()) }
	}
	
	fun translate(x: Double, y: Double, z: Double): Closeable {
		this.mcNative.translate(x, y, z)
		return Closeable { this.mcNative.translate(-x, -y, -z) }
	}
	
	fun translate(translation: Vector3i): Closeable {
		this.mcNative.translate(translation.x.toDouble(), translation.y.toDouble(), translation.z.toDouble())
		return Closeable {
			this.mcNative.translate(-translation.x.toDouble(), -translation.y.toDouble(), -translation.z.toDouble())
		}
	}
	
	fun translate(translation: Vector3f): Closeable {
		this.mcNative.translate(translation.x.toDouble(), translation.y.toDouble(), translation.z.toDouble())
		return Closeable {
			this.mcNative.translate(-translation.x.toDouble(), -translation.y.toDouble(), -translation.z.toDouble())
		}
	}
	
	fun translate(translation: Vector3d): Closeable {
		this.mcNative.translate(translation.x, translation.y, translation.z)
		return Closeable {
			this.mcNative.translate(-translation.x, -translation.y, -translation.z)
		}
	}
	
	fun rotate(x: Float, y: Float, z: Float): Closeable {
		this.mcNative.mulPose(VectorUtil.eulerToQuaternion(x, y, z).mcNative)
		return Closeable { this.mcNative.mulPose(VectorUtil.eulerToQuaternion(-x, -y, -z).mcNative) }
	}
	
	fun rotate(x: Double, y: Double, z: Double): Closeable {
		this.mcNative.mulPose(VectorUtil.eulerToQuaternion(x.toFloat(), y.toFloat(), z.toFloat()).mcNative)
		return Closeable {
			this.mcNative.mulPose(VectorUtil.eulerToQuaternion(-x.toFloat(), -y.toFloat(), -z.toFloat()).mcNative)
		}
	}
	
	fun rotate(rotation: Vector3f): Closeable {
		this.mcNative.mulPose(rotation.asQuaternion().mcNative)
		return Closeable { this.mcNative.mulPose(rotation.inverted.asQuaternion().mcNative) }
	}
	
	fun rotate(rotation: Vector3d): Closeable {
		this.mcNative.mulPose(rotation.asQuaternion().mcNative)
		return Closeable { this.mcNative.mulPose(rotation.inverted.asQuaternion().mcNative) }
	}
	
	fun scale(x: Int, y: Int, z: Int): Closeable {
		this.mcNative.scale(x.toFloat(), y.toFloat(), z.toFloat())
		return Closeable { this.mcNative.scale(-x.toFloat(), -y.toFloat(), -z.toFloat()) }
	}
	
	fun scale(x: Float, y: Float, z: Float): Closeable {
		this.mcNative.scale(x, y, z)
		return Closeable { this.mcNative.scale(-x, -y, -z) }
	}
	
	fun scale(x: Double, y: Double, z: Double): Closeable {
		this.mcNative.scale(x.toFloat(), y.toFloat(), z.toFloat())
		return Closeable { this.mcNative.scale(-x.toFloat(), -y.toFloat(), -z.toFloat()) }
	}
	
	fun scale(scale: Vector3i): Closeable {
		this.mcNative.scale(scale.x.toFloat(), scale.y.toFloat(), scale.z.toFloat())
		return Closeable { this.mcNative.scale(-scale.x.toFloat(), -scale.y.toFloat(), -scale.z.toFloat()) }
	}
	
	fun scale(scale: Vector3f): Closeable {
		this.mcNative.scale(scale.x, scale.y, scale.z)
		return Closeable { this.mcNative.scale(-scale.x, -scale.y, -scale.z) }
	}
	
	fun scale(scale: Vector3d): Closeable {
		this.mcNative.scale(scale.x.toFloat(), scale.y.toFloat(), scale.z.toFloat())
		return Closeable { this.mcNative.scale(-scale.x.toFloat(), -scale.y.toFloat(), -scale.z.toFloat()) }
	}
}