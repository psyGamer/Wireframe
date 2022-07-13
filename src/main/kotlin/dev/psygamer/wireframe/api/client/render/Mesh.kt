package dev.psygamer.wireframe.api.client.render

import dev.psygamer.wireframe.nativeapi.client.render.RenderManager

class Mesh(private val renderType: RenderType, private val vertices: MutableCollection<Vertex> = mutableListOf()) {

	fun add(vertex: Vertex): Mesh {
		val transform = RenderManager.currentContext.poseStack.mcNative.last()
		vertices.add(vertex.transform(transform))
		return this
	}

	fun render() {
		val renderBuffer = RenderManager.currentContext.getRenderBuffer(renderType)
		renderType.format.renderVertices(renderBuffer, vertices)
	}
}