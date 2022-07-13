package dev.psygamer.wireframe.test

import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.client.renderer.IRenderTypeBuffer
import net.minecraft.client.renderer.tileentity.*
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.ForgeRenderTypes
import org.lwjgl.opengl.GL11.*
import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.api.client.render.*
import dev.psygamer.wireframe.nativeapi.block.entity.NativeBlockEntity
import dev.psygamer.wireframe.nativeapi.client.render.*
import dev.psygamer.wireframe.nativeapi.client.render.context.BlockEntityRenderingContext
import dev.psygamer.wireframe.nativeapi.wfWrapped
import dev.psygamer.wireframe.util.Identifier

class TestTESR(dispatcher: TileEntityRendererDispatcher) : TileEntityRenderer<NativeBlockEntity>(dispatcher) {

	var quadMesh = Mesh(RenderType.SOLID_QUADS)
		.add(Vertex(0.0f, 0.0f))
		.add(Vertex(0.0f, 1.0f))
		.add(Vertex(1.0f, 0.0f))

		.add(Vertex(1.0f, 0.0f))
		.add(Vertex(0.0f, 1.0f))
		.add(Vertex(1.0f, 1.0f))

	override fun render(
		// MatrixStack Location = Block Location
		pBlockEntity: NativeBlockEntity, pPartialTicks: Float, pMatrixStack: MatrixStack, pBuffer: IRenderTypeBuffer,
		pCombinedLight: Int, pCombinedOverlay: Int,
	) {
		try {
			val poseStack = pMatrixStack.wfWrapped

			RenderManager.startContext(
				BlockEntityRenderingContext(
					poseStack = poseStack, renderTypeBuffer = pBuffer,
					packedLightmap = pCombinedLight, packedOverlay = pCombinedOverlay
				)
			)

			quadMesh.render(poseStack)

			RenderManager.endContext()

			glDisable(GL_CULL_FACE)
			val debug = RenderType.solidQuadsWithTexture(Identifier("textures/block/debug.png"))
			val debug2 = RenderType.solidQuadsWithTexture(Identifier("textures/block/debug2.png"))

			poseStack.translate(0.0, 3.0, 0.0)
			poseStack.translate(0.0, 0.0, 1.0)
			poseStack.scale(-1 / 16.0f, -1 / 16.0f, -1 / 16.0f)

			Mesh(debug)
				.add(Vertex(0f, 0f, 0f).uv(0f, 0f))
				.add(Vertex(0f, 8f, 0f).uv(0f, 1f))
				.add(Vertex(8f, 8f, 0f).uv(1f, 1f))
				.add(Vertex(8f, 0f, 0f).uv(1f, 0f))
				.render(poseStack)

			poseStack.translate(0.0, 0.0, 1.0)
			poseStack.translate(0.0, 16.0, 0.0)
			poseStack.translate(8.0, 0.0, 0.0)

			val italic = 0.0f

			Mesh(debug2)
				.add(Vertex(0.0f, 0.0f, 0.0f).uv(0.0f, 0.0f).normal(0f, 0f, -1f))
				.add(Vertex(0.0f - italic, 8.0f, 0.0f).uv(0.0f, 1.0f).normal(0f, 0f, -1f))
				.add(Vertex(8.0f, 8.0f, 0.0f).uv(1.0f, 1.0f).normal(0f, 0f, -1f))
				.add(Vertex(8.0f + italic, 0.0f, 0.0f).uv(1.0f, 0.0f).normal(0f, 0f, -1f))

			val builder = Mesh(
				RenderType(ForgeRenderTypes.getText(ResourceLocation("minecraft:default/0")), VertexFormat.POSITION_COLOR_UV_LIGHTMAP)
			)

			builder
				.add(Vertex(0.0f, 0.0f, 0.0f).uv(0.51566404f, 3.90625E-5f))
				.add(Vertex(0.0f - italic, 8.0f, 0.0f).uv(0.51566404f, 0.031210937f))
				.add(Vertex(8.0f, 8.0f, 0.0f).uv(0.54683596f, 0.031210937f))
				.add(Vertex(8.0f + italic, 0.0f, 0.0f).uv(0.54683596f, 3.90625E-5f))
				.renderAndClear(poseStack)

			poseStack.translate(8.0, 0.0, 0.0)

			builder
				.add(Vertex(0.0f, 0.0f, 0.0f).uv(0.0f, 0.0f))
				.add(Vertex(0.0f - italic, 8.0f, 0.0f).uv(0.0f, 1.0f))
				.add(Vertex(8.0f, 8.0f, 0.0f).uv(1.0f, 1.0f))
				.add(Vertex(8.0f + italic, 0.0f, 0.0f).uv(1.0f, 0.0f))
				.render(poseStack)

			glEnable(GL_CULL_FACE)
		} catch (t: Throwable) {
			Wireframe.LOGGER.error("Failed rendering TestBlock!", t)
		}
	}
}