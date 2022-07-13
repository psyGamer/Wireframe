package dev.psygamer.wireframe.api.client.render

import net.minecraft.client.renderer.Atlases
import net.minecraftforge.client.ForgeRenderTypes
import dev.psygamer.wireframe.nativeapi.client.render.VertexFormat
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.Identifier

class RenderType(internal val mcNative: net.minecraft.client.renderer.RenderType, val format: VertexFormat) {

	companion object {

		val SOLID_QUADS = RenderType(Atlases.solidBlockSheet(), VertexFormat.ENTITY)
		val TRANSLUCENT_LINES = RenderType(net.minecraft.client.renderer.RenderType.lines(), VertexFormat.ENTITY)

		fun solidQuadsWithTexture(texture: Identifier): RenderType {
			return RenderType(ForgeRenderTypes.getItemLayeredSolid(texture.mcNative), VertexFormat.ENTITY)
		}
	}
}