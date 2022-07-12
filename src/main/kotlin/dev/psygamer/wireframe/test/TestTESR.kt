package dev.psygamer.wireframe.test

import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.*
import net.minecraft.client.renderer.tileentity.*
import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.api.client.model.*
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.nativeapi.block.entity.NativeBlockEntity
import dev.psygamer.wireframe.nativeapi.wfWrapped
import dev.psygamer.wireframe.util.helper.using

class TestTESR(dispatcher: TileEntityRendererDispatcher) : TileEntityRenderer<NativeBlockEntity>(dispatcher) {

	var quadMesh = Mesh.Builder()
		.add(Vertex(0.0f, 0.0f))
		.add(Vertex(0.0f, 1.0f))
		.add(Vertex(1.0f, 0.0f))

		.add(Vertex(1.0f, 0.0f))
		.add(Vertex(0.0f, 1.0f))
		.add(Vertex(1.0f, 1.0f))
		.build()

	fun rend(blockEntity: BlockEntity, poseStack: PoseStack, partialTicks: Float) {
		using(poseStack.translate(0, 1, 0)) {
			quadMesh.render()
		}
	}

	override fun render(
		// MatrixStack Location = Block Location
		pBlockEntity: NativeBlockEntity, pPartialTicks: Float, pMatrixStack: MatrixStack, pBuffer: IRenderTypeBuffer,
		pCombinedLight: Int, pCombinedOverlay: Int,
	) {
		val poseStack = pMatrixStack.wfWrapped

//		RenderManager.startContext(poseStack, pBuffer)
//		rend(pBlockEntity.blockEntity, poseStack, pPartialTicks)
//		RenderManager.endBatch()

		return
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

//		glDisable(GL_CULL_FACE)
//		glTranslated(-5.0, -5.0, -5.0)
//		glTranslatef(0.0f, 1.0f, 0.0f)
//		val builder = Tessellator.getInstance().builder

		val builder = pBuffer.getBuffer(RenderType.solid())
		fun vertex(x: Float, y: Float, z: Float, r: Float, g: Float, b: Float, a: Float) {
			builder.vertex(pMatrixStack.last().pose(), x, y, z)
				.color(r, g, b, a)
				.uv(x, y)
				.uv2(pCombinedLight)
				.overlayCoords(pCombinedOverlay)
				.normal(pMatrixStack.last().normal(), 1f, 0f, 0f)
				.endVertex()
		}
		pMatrixStack.translate(0.0, 1.0, 0.0)
		Minecraft.getInstance().player?.chat("Render: $pPartialTicks")
		vertex(0f, 1f, 0f, 1f, 0f, 1f, 0.25f)
		vertex(1f, 1f, 0f, 0f, 0f, 1f, 0.50f)
		vertex(1f, 0f, 0f, 0f, 1f, 1f, 0.75f)
		vertex(0f, 0f, 0f, 1f, 1f, 1f, 1.00f)
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
//		glEnable(GL_CULL_FACE)

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
//		RenderManager.endBatch()
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