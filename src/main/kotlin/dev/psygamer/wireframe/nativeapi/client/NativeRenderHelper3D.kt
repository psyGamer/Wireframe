package dev.psygamer.wireframe.nativeapi.client

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.model.ItemCameraTransforms
import net.minecraft.client.renderer.texture.OverlayTexture
import dev.psygamer.wireframe.api.client.render.PoseStack
import dev.psygamer.wireframe.api.item.ItemStack
import dev.psygamer.wireframe.nativeapi.client.render.RenderManager
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.Color

object NativeRenderHelper3D {

	fun renderText(
		poseStack: PoseStack, text: String, color: Color, backgroundColor: Color,
		dropShadow: Boolean, centered: Boolean, rightToLeft: Boolean,
	) {
		val font = Minecraft.getInstance().font

		val transparent = color.alpha < 1.0f
		val xOffset = if (centered) font.width(text) / -2.0f else 0.0f

		font.drawInBatch(
			text, xOffset, 0.0f, color.mcNative, dropShadow, poseStack.mcNative.last().pose(),
			RenderManager.currentContext.renderTypeBuffer, transparent, backgroundColor.mcNative, RenderManager.currentContext.packedLightmap,
			rightToLeft
		)
	}

	fun renderItemStack(poseStack: PoseStack, itemStack: ItemStack) {
		val itemRenderer = Minecraft.getInstance().itemRenderer
		val itemModel = itemRenderer.getModel(itemStack.mcNative, null, null)

		itemRenderer.render(
			itemStack.mcNative, ItemCameraTransforms.TransformType.GROUND, true, poseStack.mcNative,
			RenderManager.currentContext.renderTypeBuffer, RenderManager.currentContext.packedLightmap, OverlayTexture.NO_OVERLAY, itemModel
		)
	}
}
