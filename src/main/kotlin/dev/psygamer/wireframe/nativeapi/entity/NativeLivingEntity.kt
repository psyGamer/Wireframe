package dev.psygamer.wireframe.nativeapi.entity

import net.minecraft.entity.MobEntity
import net.minecraft.entity.player.PlayerEntity
import dev.psygamer.wireframe.api.entity.*
import dev.psygamer.wireframe.nativeapi.*

open class NativeLivingEntity(override val mcNative: net.minecraft.entity.LivingEntity) :
	NativeEntity(mcNative), LivingEntity {

	override val maxHealth: Int
		get() = (mcNative.maxHealth * 2).toInt()
	override var health: Int
		get() = (mcNative.health * 2).toInt()
		set(healthLevel) {
			mcNative.health = healthLevel / 2f
		}

	override fun canBeLeashedTo(player: Player): Boolean {
		return mcNative is MobEntity &&
			   (mcNative as MobEntity).canBeLeashed(player.mcNative)
	}

	override fun isLeashedTo(player: Player): Boolean {
		return mcNative is MobEntity &&
			   (mcNative as MobEntity).isLeashed &&
			   (mcNative as MobEntity).leashHolder?.uuid == player.uuid
	}

	override var leashHolder: Player?
		get() =
			if (mcNative is MobEntity &&
				(mcNative as MobEntity).leashHolder is PlayerEntity
			)
				((mcNative as MobEntity).leashHolder as PlayerEntity).wfWrapped
			else
				null
		set(value) {
			if (mcNative !is MobEntity)
				return

			if (value == null)
				unleash()
			else
				(mcNative as MobEntity).setLeashedTo(value.mcNative, true)

		}

	override fun unleash(dropLeashItem: Boolean) {
		if (mcNative is MobEntity) {
			(mcNative as MobEntity).dropLeash(true, !(leashHolder?.isCreative ?: false))
		}
	}
}