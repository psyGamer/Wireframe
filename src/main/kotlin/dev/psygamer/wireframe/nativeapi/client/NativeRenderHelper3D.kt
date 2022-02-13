package dev.psygamer.wireframe.nativeapi.client

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.model.ItemCameraTransforms
import net.minecraft.client.renderer.texture.OverlayTexture
import dev.psygamer.wireframe.api.client.render.RenderingContext
import dev.psygamer.wireframe.api.item.ItemStack
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.Color
import dev.psygamer.wireframe.util.using

object NativeRenderHelper3D {
	
	fun renderText(
		context: RenderingContext, text: String, textColor: Color, backgroundColor: Color,
		dropShadow: Boolean, centered: Boolean, rightToLeft: Boolean,
	) {
		val font = Minecraft.getInstance().font
		
		using(context.matrixStack.push()) {
			context.matrixStack.scale(-1 / 16.0f, -1 / 16.0f, -1 / 16.0f) // Ensure that 1 font pixel = 1 block pixel
			
			context.matrixStack.rotate(context.rotation)
			context.matrixStack.scale(context.scale)
			
			val transparent = textColor.alpha < 1.0f
			val xOffset = if (centered) font.width(text) / -2.0f else 0.0f
			// FIXME: Ba
			font.drawInBatch(text, xOffset, 0.0f, textColor.mcNative, dropShadow, context.matrixStack.mcNative.last().pose(),
							 context.renderBuffer, transparent, backgroundColor.mcNative, context.packedLight, rightToLeft)
		}
	}
	
	fun renderItemStack(context: RenderingContext, itemStack: ItemStack) {
		val itemRenderer = Minecraft.getInstance().itemRenderer
		val itemModel = itemRenderer.getModel(itemStack.mcNative, null, null)
		
		using(context.matrixStack.push()) {
			context.matrixStack.rotate(context.rotation)
			context.matrixStack.scale(context.scale)
			
			itemRenderer.render(itemStack.mcNative, ItemCameraTransforms.TransformType.GROUND, true, context.matrixStack.mcNative,
								context.renderBuffer, context.packedLight, OverlayTexture.NO_OVERLAY, itemModel)
		}
	}
	
	/**
	 * Packs sky and block light into a single integer.
	 * ```
	 * Format: 00000000 11110000 00000000 11110000
	 *                     ^ Sky             ^ Block
	 * ```
	 */
	private val RenderingContext.packedLight
		get() = this.skyLight shr 20 or this.blockLight shr 4
}
