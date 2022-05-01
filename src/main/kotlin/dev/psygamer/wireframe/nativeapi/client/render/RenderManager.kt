package dev.psygamer.wireframe.nativeapi.client.render

import net.minecraft.client.renderer.IRenderTypeBuffer
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.nativeapi.wfWrapped

object RenderManager {
	
	var currentPoseStack: PoseStack? = null
		private set
	private var currentRenderBuffer: IRenderTypeBuffer? = null
	
	fun isRendering() =
		currentPoseStack != null && currentRenderBuffer != null
	
	fun startBatch(poseStack: PoseStack, renderBuffer: IRenderTypeBuffer) {
		if (currentPoseStack != null || currentRenderBuffer != null)
			throw IllegalStateException("Cannot start another batch before ending the previous one!")
		currentPoseStack = poseStack
		currentRenderBuffer = renderBuffer
	}
	
	fun endBatch() {
		currentPoseStack = null
		currentRenderBuffer = null
	}
	
	fun getRenderBuffer(type: RenderBuffer.Type): RenderBuffer {
		if (currentRenderBuffer == null)
			throw IllegalStateException("Cannot get RenderBuffer while not rendering!")
		return currentRenderBuffer!!.getBuffer(type.mcNative).wfWrapped
	}
}