package dev.psygamer.wireframe.nativeapi.mixin;

import dev.psygamer.wireframe.nativeapi.patches.PatchedFontRenderer;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.math.vector.Matrix4f;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FontRenderer.class)
public abstract class FontRendererMixin implements PatchedFontRenderer {
	
	@Override
	public int drawInBatchWithShadowFix(String text, float x, float y, int color, boolean drawShadow,
										Matrix4f matrix, IRenderTypeBuffer buffer, boolean transparent,
										int colorBackground, int packedLight, boolean bidiFlag
	) {
		if (bidiFlag) {
			text = this.bidirectionalShaping(text);
		}
		
		color = adjustColor(color);
		Matrix4f foregroundMatrix = matrix.copy();
		
		if (drawShadow) {
			// Using a translation matrix is a better solution than using [Matrix4f#translate].
			foregroundMatrix.multiply(Matrix4f.createTranslateMatrix(0f, 0f, -0.001f));
			renderText(text, x, y, color, true, matrix, buffer, transparent, colorBackground, packedLight);
		}
		
		x = renderText(text, x, y, color, false, foregroundMatrix, buffer, transparent, colorBackground, packedLight);
		return (int) x + (drawShadow ? 1 : 0);
	}
	
	@Shadow
	public abstract String bidirectionalShaping(final String pText);
	
	@Shadow
	private static int adjustColor(final int pColor) {
		return 0;
	}
	
	@Shadow
	protected abstract float renderText(final String pText, final float pX, final float pY, final int pColor,
										final boolean pIsShadow, final Matrix4f pMatrix, final IRenderTypeBuffer pBuffer,
										final boolean pIsTransparent, final int pColorBackground, final int pPackedLight
	);
}
