package dev.psygamer.wireframe.nativeapi.client.render.blockentity

import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.client.renderer.IRenderTypeBuffer
import net.minecraft.client.renderer.tileentity.*
import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.api.client.render.blockentity.BlockEntityRenderer
import dev.psygamer.wireframe.nativeapi.block.entity.NativeBlockEntity
import dev.psygamer.wireframe.nativeapi.client.render.RenderManager
import dev.psygamer.wireframe.nativeapi.client.render.context.BlockEntityRenderingContext
import dev.psygamer.wireframe.nativeapi.wfWrapped

class NativeBlockEntityRenderer<T : BlockEntity>(
	val blockEntityRenderer: BlockEntityRenderer<T>, dispatcher: TileEntityRendererDispatcher,
) : TileEntityRenderer<NativeBlockEntity>(dispatcher) {

	override fun render(
		blockEntity: NativeBlockEntity, partialTicks: Float, matrixStack: MatrixStack,
		buffer: IRenderTypeBuffer, combinedLight: Int, combinedOverlay: Int,
	) {
		val poseStack = matrixStack.wfWrapped
		val context = BlockEntityRenderingContext(poseStack, buffer, combinedLight, combinedOverlay)

		RenderManager.startContext(context)
		@Suppress("UNCHECKED_CAST") // It's checked
		this.blockEntityRenderer.render(blockEntity.blockEntity as T, poseStack)
		RenderManager.endContext()
	}

}