package dev.psygamer.wireframe.test

import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.vertex.IVertexBuilder
import net.minecraft.client.renderer.*
import net.minecraft.client.renderer.tileentity.*
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.ForgeRenderTypes
import org.lwjgl.opengl.GL11.*
import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.api.client.model.*
import dev.psygamer.wireframe.api.client.render.*
import dev.psygamer.wireframe.nativeapi.block.entity.NativeBlockEntity
import dev.psygamer.wireframe.nativeapi.client.render.RenderManager
import dev.psygamer.wireframe.nativeapi.client.render.context.BlockEntityRenderingContext
import dev.psygamer.wireframe.nativeapi.wfWrapped
import dev.psygamer.wireframe.util.helper.using

class TestTESR(dispatcher: TileEntityRendererDispatcher) : TileEntityRenderer<NativeBlockEntity>(dispatcher) {

	var quadMesh = MeshBuilder(RenderBuffer.Type.SOLID_QUADS)
		.add(Vertex(0.0f, 0.0f))
		.add(Vertex(0.0f, 1.0f))
		.add(Vertex(1.0f, 0.0f))

		.add(Vertex(1.0f, 0.0f))
		.add(Vertex(0.0f, 1.0f))
		.add(Vertex(1.0f, 1.0f))
		.build()

	fun rend(blockEntity: BlockEntity, poseStack: PoseStack, partialTicks: Float) {
		using(poseStack.translate(0, 1, 0)) {
			runCatching { quadMesh.render() }.onFailure { Wireframe.LOGGER.error("Failed rendering quad!", it) }
		}
	}

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

			rend(pBlockEntity.blockEntity, poseStack, pPartialTicks)

			RenderManager.endContext()

//		RenderManager.endBatch()

//		glPushMatrix()
//
//		RenderSystem.enableDepthTest()
//		RenderSystem.enableBlend()
//		RenderSystem.defaultBlendFunc()
//
//		RenderSystem.bindTexture(0)
//		// use API
//
//		// Colored Quad
//		glTranslatef(0.0f, 1.0f, 0.0f)
//		Minecraft.getInstance().itemRenderer.render(
//			ItemStack(Items.SPONGE), ItemCameraTransforms.TransformType.GROUND, false,
//			pMatrixStack, pBuffer, pCombinedLight, pCombinedOverlay,
//			Minecraft.getInstance().itemRenderer.getModel(ItemStack(Items.SPONGE), null, null)
//		)
////		glPushMatrix()
////		glLoadIdentity()
////		pMatrixStack.pushPose()
//		val cam = Minecraft.getInstance().gameRenderer.mainCamera
//		pMatrixStack.translate(-(pBlockEntity.blockPos.x - camPos.x), -(pBlockEntity.blockPos.y - camPos.y),
//							   -(pBlockEntity.blockPos.z - camPos.z))
//		@Suppress("DEPRECATION")
//		RenderSystem.multMatrix(pMatrixStack.last().pose())
//		pMatrixStack.popPose()
//		use(Mesh(quadBuffer)) {
//			it.render()
//		}
//		glPopMatrix()
//		glTranslatef(pBlockEntity.blockPos.x.toFloat(), pBlockEntity.blockPos.y.toFloat(), pBlockEntity.blockPos.z.toFloat())

//		pMatrixStack.pushPose()
//		pMatrixStack.translate(0.5, 0.5, 0.5)
//
//		val a = Vector4f(0.0f, 1.0f, 0.0f, 1.0f)
//		a.transform(pMatrixStack.last().pose())
//		val b = Vector4f(1.0f, 1.0f, 0.0f, 1.0f)
//		b.transform(pMatrixStack.last().pose())
//		val c = Vector4f(1.0f, 0.0f, 0.0f, 1.0f)
//		c.transform(pMatrixStack.last().pose())
//		val d = Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
//		d.transform(pMatrixStack.last().pose())
//
//		pMatrixStack.popPose()

			glDisable(GL_CULL_FACE)
//		glTranslated(-5.0, -5.0, -5.0)
//		glTranslatef(0.0f, 1.0f, 0.0f)
//		val builder = Tessellator.getInstance().builder
//			Minecraft.getInstance().font.drawInBatch(
//				StringTextComponent("moin"), 0.0f, 0.0f, 2830397,
//				false, pMatrixStack.last().pose(), pBuffer,
//				false, 0x00000000, pCombinedLight
//			)
			val debug = ForgeRenderTypes.getItemLayeredSolid(ResourceLocation("textures/block/debug.png"))
			val debug2 = ForgeRenderTypes.getItemLayeredSolid(ResourceLocation("textures/block/debug2.png"))
			val builder = pBuffer.getBuffer(debug)

			fun vertex(x: Float, y: Float, z: Float, u: Float, v: Float) {
				builder.vertex(pMatrixStack.last().pose(), x, y, z)
					.color(1f, 1f, 1f, 1f)
					.uv(u, v)
					.overlayCoords(pCombinedOverlay)
					.uv2(pCombinedLight)
					.normal(0f, 0f, -1f)
					.endVertex()
			}
			pMatrixStack.translate(0.0, 3.0, 0.0)
			pMatrixStack.translate(0.0, 0.0, 1.0)
			pMatrixStack.scale(-1 / 16.0f, -1 / 16.0f, -1 / 16.0f)
//		vertex(0f, 1f, 0f, 1f, 0f, 1f, 0.25f)
//		vertex(1f, 1f, 0f, 0f, 0f, 1f, 0.50f)
//		vertex(1f, 0f, 0f, 0f, 1f, 1f, 0.75f)
//		vertex(0f, 0f, 0f, 1f, 1f, 1f, 1.00f)
			vertex(0f, 0f, 0f, 0f, 0f)
			vertex(0f, 8f, 0f, 0f, 1f)
			vertex(8f, 8f, 0f, 1f, 1f)
			vertex(8f, 0f, 0f, 1f, 0f)

			pMatrixStack.translate(0.0, 0.0, 1.0)

			pMatrixStack.translate(0.0, 16.0, 0.0)
			val italic = 0.0f

			val ivertexbuilder2: IVertexBuilder = pBuffer.getBuffer(debug2)
			pMatrixStack.translate(8.0, 0.0, 0.0)
			ivertexbuilder2.vertex(pMatrixStack.last().pose(), 0.0f, 0.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(0.0f, 0.0f)
				.overlayCoords(pCombinedOverlay)
				.uv2(pCombinedLight)
				.normal(0f, 0f, -1f)
				.endVertex()
			ivertexbuilder2.vertex(pMatrixStack.last().pose(), 0.0f - italic, 8.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(0.0f, 1.0f)
				.overlayCoords(pCombinedOverlay)
				.uv2(pCombinedLight)
				.normal(0f, 0f, -1f)
				.endVertex()
			ivertexbuilder2.vertex(pMatrixStack.last().pose(), 8.0f, 8.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(1.0f, 1.0f)
				.overlayCoords(pCombinedOverlay)
				.uv2(pCombinedLight)
				.normal(0f, 0f, -1f)
				.endVertex()
			ivertexbuilder2.vertex(pMatrixStack.last().pose(), 8.0f + italic, 0.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(1.0f, 0.0f)
				.overlayCoords(pCombinedOverlay)
				.uv2(pCombinedLight)
				.normal(0f, 0f, -1f)
				.endVertex()

			val ivertexbuilder: IVertexBuilder = pBuffer.getBuffer(RenderType.text(ResourceLocation("minecraft:default/0")))
			ivertexbuilder.vertex(pMatrixStack.last().pose(), 0.0f, 0.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(0.51566404f, 3.90625E-5f)
				.uv2(pCombinedLight)
				.endVertex()
			ivertexbuilder.vertex(pMatrixStack.last().pose(), 0.0f - italic, 8.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(0.51566404f, 0.031210937f)
				.uv2(pCombinedLight)
				.endVertex()
			ivertexbuilder.vertex(pMatrixStack.last().pose(), 8.0f, 8.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(0.54683596f, 0.031210937f)
				.uv2(pCombinedLight)
				.endVertex()
			ivertexbuilder.vertex(pMatrixStack.last().pose(), 8.0f + italic, 0.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(0.54683596f, 3.90625E-5f)
				.uv2(pCombinedLight)
				.endVertex()

			pMatrixStack.translate(8.0, 0.0, 0.0)
			ivertexbuilder.vertex(pMatrixStack.last().pose(), 0.0f, 0.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(0.0f, 0.0f)
				.uv2(pCombinedLight)
				.endVertex()
			ivertexbuilder.vertex(pMatrixStack.last().pose(), 0.0f - italic, 8.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(0.0f, 1.0f)
				.uv2(pCombinedLight)
				.endVertex()
			ivertexbuilder.vertex(pMatrixStack.last().pose(), 8.0f, 8.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(1.0f, 1.0f)
				.uv2(pCombinedLight)
				.endVertex()
			ivertexbuilder.vertex(pMatrixStack.last().pose(), 8.0f + italic, 0.0f, 0.0f)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(1.0f, 0.0f)
				.uv2(pCombinedLight)
				.endVertex()

//		builder.vertex(a.x().toDouble(), a.y().toDouble(), a.z().toDouble()).color(0xFF, 0x00, 0x00, 0xFF)
//		builder.vertex(b.x().toDouble(), b.y().toDouble(), b.z().toDouble()).color(0x00, 0xFF, 0x00, 0xFF)
//		builder.vertex(c.x().toDouble(), c.y().toDouble(), c.z().toDouble()).color(0x00, 0x00, 0xFF, 0xFF)
//		builder.vertex(d.x().toDouble(), d.y().toDouble(), d.z().toDouble()).color(0x00, 0x00, 0x00, 0xFF)

//		glTranslatef(0.0f, 1.0f, 0.0f)
//		glBegin(GL_QUADS)
//		glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
//		//glRotatef((Util.getMillis() / 3).toFloat(), 0f, 1f, 0f)
//		glVertex2f(0.0f, 0.0f)
//		glVertex2f(1.0f, 0.0f)
//		glVertex2f(1.0f, 1.0f)
//		glVertex2f(0.0f, 1.0f)
//
//		glEnd()
			glEnable(GL_CULL_FACE)

			/*run {
			glTranslatef(0.0f, 1.0f, 0.0f)
			// Quad with solid color
			run {
				val red = 0.6f
				val green = 0.7f
				val blue = 0.8f
				val alpha = 0.5f
				
				RenderSystem.bindTexture(0)
				
				glBegin(GL_QUADS)
				glColor4f(red, green, blue, alpha)
				
				glVertex2f(0.0f, 0.0f)
				
				glVertex2f(1.0f, 0.0f)
				
				glVertex2f(1.0f, 1.0f)
				
				glVertex2f(0.0f, 1.0f)
				
				glEnd()
			}
			
			glTranslatef(0.0f, 1.0f, 0.0f)
			// Quad with texture
			run {
				Minecraft.getInstance().textureManager.bind(PlayerContainer.BLOCK_ATLAS)
				
				glBegin(GL_QUADS)
				glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
				
				glTexCoord2f(0.0f, 0.0f)
				glVertex2f(0.0f, 0.0f)
				
				glTexCoord2f(1.0f, 0.0f)
				glVertex2f(1.0f, 0.0f)
				
				glTexCoord2f(1.0f, 1.0f)
				glVertex2f(1.0f, 1.0f)
				
				glTexCoord2f(0.0f, 1.0f)
				glVertex2f(0.0f, 1.0f)
				
				glEnd()
			}
		}*/

//		RenderSystem.disableBlend()
//		RenderSystem.disableDepthTest()
//		glPopMatrix()
		} catch (t: Throwable) {
			Wireframe.LOGGER.error("Failed rendering TestBlock!", t)
		}
	}

//	private fun oldRenderFunc() {
//		runCatching { TestRenderer.render(pBlockEntity, pBuffer, pMatrixStack.wfWrapped) }
//
//		val ir = Minecraft.getInstance().itemRenderer
//
//		ir.render(
//			Items.WET_SPONGE.defaultInstance, ItemCameraTransforms.TransformType.GROUND, true,
//			pMatrixStack, pBuffer, 15, pCombinedLight, ir.getModel(
//				Items.WET_SPONGE.defaultInstance, null, null
//			)
//		)
//
//		val ctx = RenderingContext(
//			position = Vector3d.ZERO, rotation = Vector3d.ZERO, scale = Vector3d.ONE,
//			blockLight = 15, skyLight = 15,
//			matrixStack = pMatrixStack.wfWrapped, renderBuffer = pBuffer
//		)
//
//		NativeRenderHelper3D.renderItemStack(ctx, Items.ACACIA_DOOR.defaultInstance.wfWrapped)
//	}
}