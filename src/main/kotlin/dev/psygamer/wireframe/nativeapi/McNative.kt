package dev.psygamer.wireframe.nativeapi

import kotlin.math.roundToInt
import dev.psygamer.wireframe.api.entity.*
import dev.psygamer.wireframe.api.item.util.*
import dev.psygamer.wireframe.nativeapi.entity.*
import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.math.clamp
import dev.psygamer.wireframe.util.math.vector.*

// Item
internal val ClickResult.mcNative: net.minecraft.util.ActionResultType
	get() = getNative(true)

internal fun ClickResult.getNative(clientSide: Boolean): net.minecraft.util.ActionResultType {
	return when (this) {
		ClickResult.ACCEPTED ->
			if (clientSide)
				net.minecraft.util.ActionResultType.SUCCESS
			else
				net.minecraft.util.ActionResultType.CONSUME
		ClickResult.REJECTED -> net.minecraft.util.ActionResultType.FAIL
		ClickResult.PASS -> net.minecraft.util.ActionResultType.PASS
	}
}

internal val <T> ClickResultContainer<T>.mcNative: net.minecraft.util.ActionResult<T>
	get() = getNative(true)

internal fun <T> ClickResultContainer<T>.getNative(clientSide: Boolean): net.minecraft.util.ActionResult<T> {
	return net.minecraft.util.ActionResult(this.result.getNative(clientSide), this.obj)
}

internal val Hand.mcNative: net.minecraft.util.Hand
	get() = when (this) {
		Hand.MAIN_HAND -> net.minecraft.util.Hand.MAIN_HAND
		Hand.OFF_HAND -> net.minecraft.util.Hand.OFF_HAND
	}

internal val Rarity.mcNative: net.minecraft.item.Rarity
	get() = when (this) {
		Rarity.COMMON -> net.minecraft.item.Rarity.COMMON
		Rarity.UNCOMMON -> net.minecraft.item.Rarity.UNCOMMON
		Rarity.RARE -> net.minecraft.item.Rarity.RARE
		Rarity.EPIC -> net.minecraft.item.Rarity.EPIC
	}

// Entity
internal val Entity.mcNative: net.minecraft.entity.Entity
	get() = (this as NativeEntity).mcNative // This SHOULD be safe since NativeEntity SHOULD be the only implementor

internal val LivingEntity.mcNative: net.minecraft.entity.LivingEntity
	get() = (this as NativeLivingEntity).mcNative

internal val Player.mcNative: net.minecraft.entity.player.PlayerEntity
	get() = (this as NativePlayer).mcNative

internal val ProjectileEntity.mcNative: net.minecraft.entity.projectile.ProjectileEntity
	get() = (this as NativeProjectileEntity).mcNative

// Util
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

internal val Color.mcNative: Int
	get() {
		// Minecraft's Format: ARGB

		var color = 0x00000000

		color = color shl 0 or (alpha * 255).roundToInt().clamp(max = 255)
		color = color shl 8 or (red * 255).roundToInt().clamp(max = 255)
		color = color shl 8 or (green * 255).roundToInt().clamp(max = 255)
		color = color shl 8 or (blue * 255).roundToInt().clamp(max = 255)

		return color
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