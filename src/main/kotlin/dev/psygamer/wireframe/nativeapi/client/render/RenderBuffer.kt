package dev.psygamer.wireframe.nativeapi.client.render

import com.mojang.blaze3d.vertex.IVertexBuilder
import net.minecraft.client.renderer.RenderType
import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.debug.*

class RenderBuffer(internal val mcNative: IVertexBuilder) {
	
	private var currentVertex: VertexBuilder? = null
	
	fun vertex(x: Float, y: Float, z: Float = 0.0f) =
		vertex(x.toDouble(), y.toDouble(), z.toDouble())
	
	fun vertex(x: Double, y: Double, z: Double = 0.0): VertexBuilder {
		if (currentVertex?.done == false) {
			val ex = IllegalStateException("Must finish previous vertex before starting next!")
			debug { currentVertex!!.finish(); Wireframe.LOGGER.warn(ex) }
			nonDebug { throw ex }
		}
		
		this.mcNative.vertex(x, y, z)
		return VertexBuilder()
	}
	
	inner class VertexBuilder {
		
		var done = false
			private set
		
		fun color(r: Float, g: Float, b: Float, a: Float = 1.0f): VertexBuilder {
			mcNative.color(r, g, b, a)
			return this
		}
		
		fun uv(u: Float, v: Float): VertexBuilder {
			mcNative.uv(u, v)
			return this
		}
		
		fun normal(x: Float, y: Float, z: Float): VertexBuilder {
			mcNative.normal(x, y, z)
			return this
		}
		
		fun finish() {
			mcNative.endVertex()
		}
	}
	
	enum class Type(internal val mcNative: RenderType) {
		SOLID(RenderType.solid())
	}
}