package dev.psygamer.wireframe.api.client.render

import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.util.math.vector.*

class Vertex internal constructor(
	val x: Float, val y: Float, val z: Float = 0.0f,
) {

	constructor(x: Double, y: Double, z: Double = 0.0) : this(x.toFloat(), y.toFloat(), z.toFloat())

	constructor(x: Float, y: Float, z: Float, nx: Float, ny: Float, nz: Float, u: Float, v: Float, r: Float, g: Float, b: Float, a: Float)
			: this(x, y, z) {
		this.nx = nx
		this.ny = ny
		this.nz = nz
		this.u = u
		this.v = v
		this.r = r
		this.g = g
		this.b = b
		this.a = a
	}

	var nx = 0.0f
		private set
	var ny = 0.0f
		private set
	var nz = 0.0f
		private set

	var u = 0.0f
		private set
	var v = 0.0f
		private set

	var r = 1.0f
		private set
	var g = 1.0f
		private set
	var b = 1.0f
		private set
	var a = 1.0f
		private set

	fun normal(x: Float, y: Float, z: Float): Vertex {
		this.nx = x
		this.ny = y
		this.nz = z
		return this
	}

	fun uv(u: Float, v: Float): Vertex {
		this.u = u
		this.v = v
		return this
	}

	fun color(r: Float, g: Float, b: Float): Vertex {
		this.r = r
		this.g = g
		this.b = b
		return this
	}

	internal fun transform(transform: MatrixStack.Entry): Vertex {
		val position = Vector4f(x, y, z, 1.0f)
		val normal = Vector3f(nx, ny, nz)
		position.transform(transform.pose())
		normal.transform(transform.normal())
		return Vertex(position.x(), position.y(), position.z(), normal.x(), normal.y(), normal.z(), u, v, r, g, b, a)
	}
}