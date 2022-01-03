package dev.psygamer.wireframe.util

import dev.psygamer.wireframe.util.math.vector.Vector3i

class BlockPosition(x: Int, y: Int, z: Int) : Vector3i(x, y, z) {
	
	@JvmOverloads
	fun offset(direction: Direction, distance: Int = 1): BlockPosition {
		return offset(
			direction.unitVector.x * distance,
			direction.unitVector.y * distance,
			direction.unitVector.z * distance
		)
	}
	
	fun offset(xOffset: Int, yOffset: Int, zOffset: Int): BlockPosition {
		return BlockPosition(
			x + xOffset,
			y + yOffset,
			z + zOffset
		)
	}
}

internal val net.minecraft.util.math.BlockPos.wfWrapped: BlockPosition
	get() = BlockPosition(this.x, this.y, this.z)

internal val BlockPosition.mcNative: net.minecraft.util.math.BlockPos
	get() = net.minecraft.util.math.BlockPos(this.x, this.y, this.z)