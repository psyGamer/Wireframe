package dev.psygamer.wireframe

import dev.psygamer.wireframe.block.Block
import dev.psygamer.wireframe.block.entity.BlockEntity
import dev.psygamer.wireframe.block.state.BlockState
import dev.psygamer.wireframe.block.state.BlockStateDefinition
import dev.psygamer.wireframe.block.state.property.BlockProperty
import dev.psygamer.wireframe.internal.block.NativeBlockState
import dev.psygamer.wireframe.internal.block.NativeBlockStateDefinition
import dev.psygamer.wireframe.internal.block.state.BlockPropertyWrapper
import dev.psygamer.wireframe.util.BlockPosition
import dev.psygamer.wireframe.util.Direction
import dev.psygamer.wireframe.util.Identifier
import dev.psygamer.wireframe.util.TagCompound
import dev.psygamer.wireframe.util.math.vector.Vector2f
import dev.psygamer.wireframe.util.math.vector.Vector3d
import dev.psygamer.wireframe.util.math.vector.Vector3f
import dev.psygamer.wireframe.util.math.vector.Vector3i

internal val net.minecraft.block.Block.wfWrapped: Block
	get() = Block(this)

internal val net.minecraft.block.BlockState.wfWrapped: BlockState
	get() = NativeBlockState(this)

internal val net.minecraft.state.StateContainer<net.minecraft.block.Block, net.minecraft.block.BlockState>.wfWrapped: BlockStateDefinition
	get() = NativeBlockStateDefinition(this)

internal val net.minecraft.state.Property<*>.wfWrapped: BlockProperty<*>
	get() = BlockPropertyWrapper(this)

internal val net.minecraft.tileentity.TileEntity.wfWrapped: BlockEntity
	get() = BlockEntity(this)

internal val net.minecraft.nbt.CompoundNBT.wfWrapped: TagCompound
	get() = TagCompound(this)

internal val net.minecraft.util.ResourceLocation.wfWrapped: Identifier
	get() = Identifier(this.namespace, this.path)

internal val net.minecraft.util.Direction.wfWrapped: Direction
	get() = when (this) {
		net.minecraft.util.Direction.SOUTH -> Direction.SOUTH
		net.minecraft.util.Direction.NORTH -> Direction.NORTH
		net.minecraft.util.Direction.EAST -> Direction.EAST
		net.minecraft.util.Direction.WEST -> Direction.WEST
		net.minecraft.util.Direction.UP -> Direction.UP
		net.minecraft.util.Direction.DOWN -> Direction.DOWN
	}

internal val net.minecraft.util.math.BlockPos.wfWrapped: BlockPosition
	get() = BlockPosition(this.x, this.y, this.z)

val net.minecraft.util.math.vector.Vector2f.wfWrapped: Vector2f
	get() = Vector2f(this.x, this.y)

val net.minecraft.util.math.vector.Vector3d.wfWrapped: Vector3d
	get() = Vector3d(this.x, this.y, this.z)

val net.minecraft.util.math.vector.Vector3f.wfWrapped: Vector3f
	get() = Vector3f(this.x(), this.y(), this.z())

val net.minecraft.util.math.vector.Vector3i.wfWrapped: Vector3i
	get() = Vector3i(this.x, this.y, this.z)