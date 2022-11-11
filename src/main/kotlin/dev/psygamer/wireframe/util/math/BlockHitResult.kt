package dev.psygamer.wireframe.util.math

import org.joml.Vector3d
import dev.psygamer.wireframe.util.*

class BlockHitResult(
	location: Vector3d, val direction: Direction, val blockPosition: BlockPosition,
	miss: Boolean = false, val isInside: Boolean,
) : HitResult(location) {

	val type: Type = if (miss) Type.MISS else Type.BLOCK
}