package dev.psygamer.wireframe.api.client.render

import dev.psygamer.wireframe.nativeapi.client.render.RenderManager

class Mesh(private val vertices: Collection<Vertex>, private val renderType: RenderType) {

	fun render() {
		val renderBuffer = RenderManager.currentContext.getRenderBuffer(renderType)
		renderType.format.renderVertices(renderBuffer, vertices)
	}
}

class MeshBuilder(val renderType: RenderType) {

	private val vertices = mutableListOf<Vertex>()

	fun add(vertex: Vertex): MeshBuilder {
		val transform = RenderManager.currentContext.poseStack.mcNative.last()
		vertices.add(vertex.transform(transform))
		return this
	}

	fun build() = Mesh(vertices, renderType)
}