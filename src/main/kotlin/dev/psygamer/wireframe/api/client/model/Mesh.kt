package dev.psygamer.wireframe.api.client.model

import dev.psygamer.wireframe.nativeapi.client.render.*

class Mesh(data: FloatArray?, val size: Int, val hasNormals: Boolean, val hasTexCoords: Boolean, val hasColors: Boolean) {
	
	val stride: Int =
		3 + // Position
		(if (hasNormals) 3 else 0) +
		(if (hasTexCoords) 2 else 0) +
		(if (hasColors) 3 else 0)
	
	val data = run {
		if (data != null)
			return@run data
		return@run FloatArray(size * this.stride)
	}
	
	val vertexOffset: Int = 0
	val normalOffset: Int = if (hasNormals) vertexOffset + 3 else vertexOffset
	val texCoordOffset: Int = if (hasTexCoords) normalOffset + 2 else normalOffset
	val colorOffset: Int = if (hasColors) texCoordOffset + 3 else texCoordOffset
	
	fun render() {
		val renderBuffer = RenderManager.getRenderBuffer(RenderBuffer.Type.SOLID)
		for (i in 0..this.data.size step stride) {
			val vertexBuilder = renderBuffer.vertex(
				this.data[vertexOffset + i + 0],
				this.data[vertexOffset + i + 1],
				this.data[vertexOffset + i + 2],
			)
			if (hasNormals) vertexBuilder.normal(
				this.data[normalOffset + i + 0],
				this.data[normalOffset + i + 1],
				this.data[normalOffset + i + 2],
			)
			if (hasTexCoords) vertexBuilder.uv(
				this.data[texCoordOffset + i + 0],
				this.data[texCoordOffset + i + 1],
			)
			if (hasColors) vertexBuilder.color(
				this.data[colorOffset + i + 0],
				this.data[colorOffset + i + 1],
				this.data[colorOffset + i + 2],
			)
		}
	}
	
	class Builder {
		
		private val vertices = emptyList<Vertex>().toMutableList()
		
		fun add(vertex: Vertex): Builder {
			vertices.add(vertex)
			return this
		}
		
		fun build(): Mesh {
			var hasNormals = false
			var hasTexCoords = false
			var hasColors = false
			
			for (vertex in vertices) {
				hasNormals = hasNormals or vertex.hasNormal
				hasTexCoords = hasTexCoords or vertex.hasTexCoord
				hasColors = hasColors or vertex.hasColor
			}
			
			val buffer = Mesh(data = null, size = this.vertices.size,
							  hasNormals, hasTexCoords, hasColors)
			
			for (i in 0 until this.vertices.size) {
				val vertex = vertices[i]
				
				buffer.data[i * buffer.stride + buffer.vertexOffset + 0] = vertex.x
				buffer.data[i * buffer.stride + buffer.vertexOffset + 1] = vertex.y
				buffer.data[i * buffer.stride + buffer.vertexOffset + 2] = vertex.z
				
				if (hasNormals) {
					buffer.data[i * buffer.stride + buffer.normalOffset + 0] = vertex.nx
					buffer.data[i * buffer.stride + buffer.normalOffset + 1] = vertex.ny
					buffer.data[i * buffer.stride + buffer.normalOffset + 2] = vertex.nz
				}
				if (hasTexCoords) {
					buffer.data[i * buffer.stride + buffer.texCoordOffset + 0] = vertex.u
					buffer.data[i * buffer.stride + buffer.texCoordOffset + 1] = vertex.v
				}
				if (hasColors) {
					buffer.data[i * buffer.stride + buffer.colorOffset + 0] = vertex.r
					buffer.data[i * buffer.stride + buffer.colorOffset + 1] = vertex.g
					buffer.data[i * buffer.stride + buffer.colorOffset + 2] = vertex.b
				}
			}
			
			return buffer
		}
	}
}