package dev.psygamer.wireframe.nativeapi.client

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.model.ItemCameraTransforms
import net.minecraft.client.renderer.texture.OverlayTexture
import dev.psygamer.wireframe.api.item.ItemStack
import dev.psygamer.wireframe.nativeapi.client.render.context.RenderingContext
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.Color

object NativeRenderHelper3D {

	fun renderText(
		context: RenderingContext, text: String, textColor: Color, backgroundColor: Color,
		dropShadow: Boolean, centered: Boolean, rightToLeft: Boolean,
	) {
		val font = Minecraft.getInstance().font

		val transparent = textColor.alpha < 1.0f
		val xOffset = if (centered) font.width(text) / -2.0f else 0.0f

		font.drawInBatch(
			text, xOffset, 0.0f, textColor.mcNative, dropShadow, context.poseStack.mcNative.last().pose(),
			context.renderTypeBuffer, transparent, backgroundColor.mcNative, context.packedLightmap, rightToLeft
		)
	}

	fun renderItemStack(context: RenderingContext, itemStack: ItemStack) {
		val itemRenderer = Minecraft.getInstance().itemRenderer
		val itemModel = itemRenderer.getModel(itemStack.mcNative, null, null)

		itemRenderer.render(
			itemStack.mcNative, ItemCameraTransforms.TransformType.GROUND, true, context.poseStack.mcNative,
			context.renderTypeBuffer, context.packedLightmap, OverlayTexture.NO_OVERLAY, itemModel
		)
	}

	fun packedLight(skyLight: Int, blockLight: Int) = skyLight shr 20 or blockLight shr 4
}
