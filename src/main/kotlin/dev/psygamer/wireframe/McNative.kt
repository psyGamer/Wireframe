package dev.psygamer.wireframe

import dev.psygamer.wireframe.util.BlockPosition
import dev.psygamer.wireframe.util.Direction
import dev.psygamer.wireframe.util.Identifier
import dev.psygamer.wireframe.util.math.vector.Vector2f
import dev.psygamer.wireframe.util.math.vector.Vector3d
import dev.psygamer.wireframe.util.math.vector.Vector3f
import dev.psygamer.wireframe.util.math.vector.Vector3i

internal val Identifier.mcNative: net.minecraft.util.ResourceLocation
	get() = net.minecraft.util.ResourceLocation(this.namespace, this.path)

internal val Direction.mcNative: net.minecraft.util.Direction
	get() = when (this) {
		Direction.SOUTH -> net.minecraft.util.Direction.SOUTH
		Direction.NORTH -> net.minecraft.util.Direction.NORTH
		Direction.EAST -> net.minecraft.util.Direction.EAST
		Direction.WEST -> net.minecraft.util.Direction.WEST
		Direction.UP -> net.minecraft.util.Direction.UP
		Direction.DOWN -> net.minecraft.util.Direction.DOWN
	}

internal val BlockPosition.mcNative: net.minecraft.util.math.BlockPos
	get() = net.minecraft.util.math.BlockPos(this.x, this.y, this.z)

internal val Vector2f.mcNative: net.minecraft.util.math.vector.Vector2f
	get() = net.minecraft.util.math.vector.Vector2f(this.x, this.y)

internal val Vector3d.mcNative: net.minecraft.util.math.vector.Vector3d
	get() = net.minecraft.util.math.vector.Vector3d(this.x, this.y, this.z)

internal val Vector3f.mcNative: net.minecraft.util.math.vector.Vector3f
	get() = net.minecraft.util.math.vector.Vector3f(this.x, this.y, this.z)

internal val Vector3i.mcNative: net.minecraft.util.math.vector.Vector3i
	get() = net.minecraft.util.math.vector.Vector3i(this.x, this.y, this.z)