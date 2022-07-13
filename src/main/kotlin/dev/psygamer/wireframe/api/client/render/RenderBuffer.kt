package dev.psygamer.wireframe.api.client.render

import com.mojang.blaze3d.vertex.IVertexBuilder
import net.minecraft.client.renderer.*
import dev.psygamer.wireframe.nativeapi.client.render.*

class RenderBuffer(internal val mcNative: IVertexBuilder) {

	fun vertex(x: Float, y: Float, z: Float = 0.0f) = this.also { mcNative.vertex(x.toDouble(), y.toDouble(), z.toDouble()) }
	fun vertex(x: Double, y: Double, z: Double = 0.0) =
		this.also { mcNative.vertex(RenderManager.currentContext.poseStack.mcNative.last().pose(), x.toFloat(), y.toFloat(), z.toFloat()) }

	fun color(r: Float, g: Float, b: Float, a: Float = 1.0f) = this.also { mcNative.color(r, g, b, a) }

	fun uv(u: Float, v: Float) = this.also { mcNative.uv(u, v) }

	fun lightmapUV(packedLight: Int) = this.also { mcNative.uv2(packedLight) }
	fun lightmapUV(u: Int, v: Int) = this.also { mcNative.uv2(u, v) }

	fun overlayUV(packedOverlay: Int) = this.also { mcNative.overlayCoords(packedOverlay) }
	fun overlayUV(u: Int, v: Int) = this.also { mcNative.overlayCoords(u, v) }

	fun normal(x: Float, y: Float, z: Float) = this.also { mcNative.normal(x, y, z) }

	fun finish() = mcNative.endVertex()

	enum class Type(internal val mcNative: RenderType, val format: VertexFormat) {
		SOLID_QUADS(Atlases.solidBlockSheet(), VertexFormat.ENTITY),
		TRANSLUCENT_LINES(RenderType.lines(), VertexFormat.ENTITY)
	}
}
