package dev.psygamer.wireframe.api.client.model

class Vertex(
	val x: Float, val y: Float, val z: Float,
) {
	
	var hasNormal = false
		private set
	var nx = 0.0f
		private set
	var ny = 0.0f
		private set
	var nz = 0.0f
		private set
	
	var hasTexCoord = false
		private set
	var u = 0.0f
		private set
	var v = 0.0f
		private set
	
	var hasColor = false
		private set
	var r = 0.0f
		private set
	var g = 0.0f
		private set
	var b = 0.0f
		private set
	
	fun withNormal(x: Float, y: Float, z: Float): Vertex {
		this.nx = x
		this.ny = y
		this.nz = z
		return this
	}
	
	fun withTexCoord(u: Float, v: Float): Vertex {
		this.u = u
		this.v = v
		return this
	}
	
	fun withColor(r: Float, g: Float, b: Float): Vertex {
		this.r = r
		this.g = g
		this.b = b
		return this
	}
}