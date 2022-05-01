package dev.psygamer.wireframe.nativeapi.client.render.context

import net.minecraft.client.renderer.IRenderTypeBuffer
import dev.psygamer.wireframe.api.client.render.PoseStack

class BlockEntityRenderingContext(
	override val poseStack: PoseStack,
	override val renderTypeBuffer: IRenderTypeBuffer,
	
	override val packedLightmap: Int,
	override val packedOverlay: Int,
) : RenderingContext()