package dev.psygamer.wireframe.util.math

import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.math.vector.Vector3d

class BlockHitResult(
	location: Vector3d, val direction: Direction, val blockPosition: BlockPosition,
	miss: Boolean = false, val isInside: Boolean,
) : HitResult(location) {

	val type: Type = if (miss) Type.MISS else Type.BLOCK
}