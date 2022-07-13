package dev.psygamer.wireframe.nativeapi.client.render.context

import net.minecraft.client.renderer.IRenderTypeBuffer
import dev.psygamer.wireframe.api.client.render.*
import dev.psygamer.wireframe.nativeapi.wfWrapped

abstract class RenderingContext {

	abstract val poseStack: PoseStack
	abstract val renderTypeBuffer: IRenderTypeBuffer

	abstract val packedLightmap: Int
	abstract val packedOverlay: Int

	val lightmapU
		get() = this.packedLightmap and 0xffff
	val lightmapV
		get() = this.packedLightmap shr 16 and 0xffff

	val overlayU
		get() = this.packedOverlay and 0xffff
	val overlayV
		get() = this.packedOverlay shr 16 and 0xffff

	fun getRenderBuffer(type: RenderBuffer.Type): RenderBuffer {
		return renderTypeBuffer.getBuffer(type.mcNative).wfWrapped
	}
}