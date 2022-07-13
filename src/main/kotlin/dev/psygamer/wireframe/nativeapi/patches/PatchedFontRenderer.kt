package dev.psygamer.wireframe.nativeapi.patches

import net.minecraft.client.gui.FontRenderer
import net.minecraft.client.renderer.IRenderTypeBuffer
import net.minecraft.util.math.vector.Matrix4f

/** This provides some patches for the [FontRenderer] */
interface PatchedFontRenderer {

	/** Fixes issues related to text with shadows when using [FontRenderer.drawInBatch] in 3D space. */
	fun drawInBatchWithShadowFix(
		text: String, x: Float, y: Float, color: Int, drawShadow: Boolean,
		matrix: Matrix4f, buffer: IRenderTypeBuffer, transparent: Boolean, colorBackground: Int, packedLight: Int, bidiFlag: Boolean,
	): Int
}