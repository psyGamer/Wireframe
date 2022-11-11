package dev.psygamer.wireframe.nativeapi.entity

import net.minecraft.entity.player.PlayerEntity
import org.joml.Vector3d
import dev.psygamer.wireframe.api.entity.Player
import dev.psygamer.wireframe.api.item.ItemStack
import dev.psygamer.wireframe.api.item.util.Hand
import dev.psygamer.wireframe.nativeapi.*

class NativePlayer(override val mcNative: PlayerEntity) : NativeLivingEntity(mcNative), Player {

	override var foodLevel: Int
		get() = mcNative.foodData.foodLevel
		set(value) {
			mcNative.foodData.foodLevel = value
		}
	override var saturationLevel: Float
		get() = mcNative.foodData.saturationLevel
		set(value) {
			mcNative.foodData.setSaturation(value)
		}

	override val movementVector: Vector3d
		get() = Vector3d(
			mcNative.xxa * if (mcNative.isSprinting) 0.4 else 0.2,
			mcNative.yya * if (mcNative.isSprinting) 0.4 else 0.2,
			mcNative.zza * if (mcNative.isSprinting) 0.4 else 0.2
		)
	override val isCrouching: Boolean
		get() = mcNative.isShiftKeyDown
	override val isCreative: Boolean
		get() = mcNative.isCreative

	override fun getHeldItem(hand: Hand): ItemStack {
		return mcNative.getItemInHand(hand.mcNative).wfWrapped
	}

	override fun setHeldItem(item: ItemStack, hand: Hand) {
		mcNative.setItemInHand(hand.mcNative, item.mcNative)
	}
}