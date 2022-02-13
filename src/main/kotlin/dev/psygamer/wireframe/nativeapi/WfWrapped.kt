package dev.psygamer.wireframe.nativeapi

import net.minecraft.util.math.RayTraceResult
import dev.psygamer.wireframe.api.block.*
import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.api.entity.*
import dev.psygamer.wireframe.api.item.*
import dev.psygamer.wireframe.api.item.util.*
import dev.psygamer.wireframe.api.network.PacketBuffer
import dev.psygamer.wireframe.api.world.*
import dev.psygamer.wireframe.nativeapi.block.*
import dev.psygamer.wireframe.nativeapi.entity.*
import dev.psygamer.wireframe.nativeapi.world.*
import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.math.BlockHitResult
import dev.psygamer.wireframe.util.math.vector.*

// Block
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

// Item
internal val net.minecraft.item.Item.wfWrapped: Item
	get() = Item(this)

internal val net.minecraft.item.ItemStack.wfWrapped: ItemStack
	get() = ItemStack(this)

internal val net.minecraft.util.ActionResultType.wfWrapped: ClickResult
	get() = when (this) {
		net.minecraft.util.ActionResultType.SUCCESS, net.minecraft.util.ActionResultType.CONSUME -> ClickResult.ACCEPTED
		net.minecraft.util.ActionResultType.FAIL -> ClickResult.REJECTED
		net.minecraft.util.ActionResultType.PASS -> ClickResult.PASS
	}

internal val net.minecraft.util.Hand.wfWrapped: Hand
	get() = when (this) {
		net.minecraft.util.Hand.MAIN_HAND -> Hand.MAIN_HAND
		net.minecraft.util.Hand.OFF_HAND -> Hand.OFF_HAND
	}

internal val net.minecraft.item.Rarity.wfWrapped: Rarity
	get() = when (this) {
		net.minecraft.item.Rarity.COMMON -> Rarity.COMMON
		net.minecraft.item.Rarity.UNCOMMON -> Rarity.UNCOMMON
		net.minecraft.item.Rarity.RARE -> Rarity.RARE
		net.minecraft.item.Rarity.EPIC -> Rarity.EPIC
	}

// Entity
internal val net.minecraft.entity.Entity.wfWrapped: Entity
	get() = NativeEntity(this)

internal val net.minecraft.entity.LivingEntity.wfWrapped: LivingEntity
	get() = NativeLivingEntity(this)

internal val net.minecraft.entity.player.PlayerEntity.wfWrapped: Player
	get() = NativePlayer(this)

internal val net.minecraft.entity.projectile.ProjectileEntity.wfWrapped: ProjectileEntity
	get() = NativeProjectileEntity(this)

// World
internal val net.minecraft.world.World.wfWrapped: World
	get() = NativeWorld(this)

internal val net.minecraft.world.IBlockReader.wfWrapped: BlockReader
	get() = NativeBlockReader(this)

// Event
internal val net.minecraft.network.PacketBuffer.wfWrapped: PacketBuffer
	get() = PacketBuffer(this)

// Util
val net.minecraft.nbt.CompoundNBT.wfWrapped: TagCompound
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

internal val net.minecraft.util.math.BlockRayTraceResult.wfWrapped: BlockHitResult
	get() = BlockHitResult(
		this.location.wfWrapped,
		this.direction.wfWrapped,
		this.blockPos.wfWrapped,
		this.type == RayTraceResult.Type.MISS,
		this.isInside
	)

val net.minecraft.util.math.BlockPos.wfWrapped: BlockPosition
	get() = BlockPosition(this.x, this.y, this.z)

val net.minecraft.util.math.vector.Vector2f.wfWrapped: Vector2f
	get() = Vector2f(this.x, this.y)

val net.minecraft.util.math.vector.Vector3d.wfWrapped: Vector3d
	get() = Vector3d(this.x, this.y, this.z)

val net.minecraft.util.math.vector.Vector3f.wfWrapped: Vector3f
	get() = Vector3f(this.x(), this.y(), this.z())

val net.minecraft.util.math.vector.Vector3i.wfWrapped: Vector3i
	get() = Vector3i(this.x, this.y, this.z)

val net.minecraft.util.math.vector.Quaternion.wfWrapped: Quaternion
	get() = Quaternion(this.i(), this.j(), this.k(), this.r())