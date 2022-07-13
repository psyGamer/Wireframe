package dev.psygamer.wireframe.nativeapi.client.render

import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import dev.psygamer.wireframe.api.client.model.Vertex
import dev.psygamer.wireframe.api.client.render.RenderBuffer

enum class VertexFormat(
	internal val mcNative: net.minecraft.client.renderer.vertex.VertexFormat,
	val renderVertices: (RenderBuffer, Iterable<Vertex>) -> Unit,
) {

	BLOCK(DefaultVertexFormats.BLOCK, ::renderBlock),
	ENTITY(DefaultVertexFormats.NEW_ENTITY, ::renderEntity),

	POSITION(DefaultVertexFormats.POSITION, ::renderPosition),
	POSITION_UV(DefaultVertexFormats.POSITION_TEX, ::renderPositionUV),
	POSITION_COLOR(DefaultVertexFormats.POSITION_COLOR, ::renderPositionColor),
	POSITION_COLOR_UV(DefaultVertexFormats.POSITION_COLOR_TEX, ::renderPositionColorUV),
	POSITION_COLOR_LIGHTMAP(DefaultVertexFormats.POSITION_COLOR_LIGHTMAP, ::renderPositionColorLightmap),
	POSITION_COLOR_UV_LIGHTMAP(DefaultVertexFormats.POSITION_COLOR_TEX_LIGHTMAP, ::renderPositionColorUVLightmap),
}

private val packedLightmap: Int get() = RenderManager.currentContext.packedLightmap
private val packedOverlay: Int get() = RenderManager.currentContext.packedOverlay

private fun renderBlock(renderBuffer: RenderBuffer, vertices: Iterable<Vertex>) {
	vertices.forEach {
		renderBuffer.vertex(it.x, it.y, it.z)
			.color(it.r, it.g, it.b, 1.0f)
			.uv(it.u, it.v)
			.lightmapUV(packedLightmap)
			.normal(it.nx, it.ny, it.nz)
			.finish()
	}
}

private fun renderEntity(renderBuffer: RenderBuffer, vertices: Iterable<Vertex>) {
	vertices.forEach {
		renderBuffer.vertex(it.x, it.y, it.z)
			.color(it.r, it.g, it.b, 1.0f)
			.uv(it.u, it.v)
			.overlayUV(packedOverlay)
			.lightmapUV(packedLightmap)
			.normal(it.nx, it.ny, it.nz)
			.finish()
	}
}

private fun renderPosition(renderBuffer: RenderBuffer, vertices: Iterable<Vertex>) {
	vertices.forEach {
		renderBuffer.vertex(it.x, it.y, it.z)
	}
}

private fun renderPositionUV(renderBuffer: RenderBuffer, vertices: Iterable<Vertex>) {
	vertices.forEach {
		renderBuffer.vertex(it.x, it.y, it.z)
			.uv(it.u, it.v)
	}
}

private fun renderPositionColor(renderBuffer: RenderBuffer, vertices: Iterable<Vertex>) {
	vertices.forEach {
		renderBuffer.vertex(it.x, it.y, it.z)
			.color(it.r, it.g, it.b, 1.0f)
	}
}

private fun renderPositionColorUV(renderBuffer: RenderBuffer, vertices: Iterable<Vertex>) {
	vertices.forEach {
		renderBuffer.vertex(it.x, it.y, it.z)
			.color(it.r, it.g, it.b, 1.0f)
			.uv(it.u, it.v)
	}
}

private fun renderPositionColorLightmap(renderBuffer: RenderBuffer, vertices: Iterable<Vertex>) {
	vertices.forEach {
		renderBuffer.vertex(it.x, it.y, it.z)
			.color(it.r, it.g, it.b, 1.0f)
			.lightmapUV(packedLightmap)
	}
}

private fun renderPositionColorUVLightmap(renderBuffer: RenderBuffer, vertices: Iterable<Vertex>) {
	vertices.forEach {
		renderBuffer.vertex(it.x, it.y, it.z)
			.color(it.r, it.g, it.b, 1.0f)
			.uv(it.u, it.v)
			.lightmapUV(packedLightmap)
	}
}