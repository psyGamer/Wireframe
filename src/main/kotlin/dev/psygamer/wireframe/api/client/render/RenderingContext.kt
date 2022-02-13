package dev.psygamer.wireframe.api.client.render

import dev.psygamer.wireframe.util.math.vector.Vector3d

data class RenderingContext(
	val position: Vector3d,
	val rotation: Vector3d,
	val scale: Vector3d,
	
	val skyLight: Int,
	val blockLight: Int,
	
	val matrixStack: MatrixStack,
	val renderBuffer: net.minecraft.client.renderer.IRenderTypeBuffer,
)
