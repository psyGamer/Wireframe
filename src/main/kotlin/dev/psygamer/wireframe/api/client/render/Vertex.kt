package dev.psygamer.wireframe.api.client.render

class Vertex internal constructor(
	val x: Double, val y: Double, val z: Double = 0.0,
) {

	constructor(x: Float, y: Float, z: Float = 0.0f) : this(x.toDouble(), y.toDouble(), z.toDouble())

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
		this.hasNormal = true
		this.nx = x
		this.ny = y
		this.nz = z
		return this
	}

	fun withTexCoord(u: Float, v: Float): Vertex {
		this.hasTexCoord = true
		this.u = u
		this.v = v
		return this
	}

	fun withColor(r: Float, g: Float, b: Float): Vertex {
		this.hasColor = true
		this.r = r
		this.g = g
		this.b = b
		return this
	}
}