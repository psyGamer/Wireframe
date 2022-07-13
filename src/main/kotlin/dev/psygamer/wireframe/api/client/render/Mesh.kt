package dev.psygamer.wireframe.api.client.render

import dev.psygamer.wireframe.nativeapi.client.render.RenderManager

class Mesh(private val renderType: RenderType, private val vertices: MutableCollection<Vertex> = mutableListOf()) {

	fun add(vertex: Vertex): Mesh {
		vertices.add(vertex)
		return this
	}

	fun render(poseStack: PoseStack) {
		val renderBuffer = RenderManager.currentContext.getRenderBuffer(renderType)
		renderType.format.renderVertices(renderBuffer, vertices.map { it.transform(poseStack) })
	}

	fun renderAndClear(poseStack: PoseStack) {
		render(poseStack)
		this.vertices.clear()
	}
}