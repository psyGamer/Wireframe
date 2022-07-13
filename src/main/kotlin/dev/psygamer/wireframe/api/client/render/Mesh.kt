package dev.psygamer.wireframe.api.client.render

import dev.psygamer.wireframe.nativeapi.client.render.RenderManager

class Mesh(private val vertices: Collection<Vertex>, private val renderType: RenderBuffer.Type) {

	fun render() {
		val renderBuffer = RenderManager.currentContext.getRenderBuffer(renderType)
		renderType.format.renderVertices(renderBuffer, vertices)
	}
}

class MeshBuilder(val renderType: RenderBuffer.Type) {

	private val vertices = mutableListOf<Vertex>()

	fun add(vertex: Vertex) = this.also { vertices.add(vertex) }
	fun build() = Mesh(vertices, renderType)
}