package dev.psygamer.wireframe.api.entity

import org.joml.Vector3d
import dev.psygamer.wireframe.api.item.ItemStack
import dev.psygamer.wireframe.api.item.util.Hand

interface Player : LivingEntity {

	var foodLevel: Int
	var saturationLevel: Float

	val movementVector: Vector3d?
	val isCrouching: Boolean
	val isCreative: Boolean

	fun getHeldItem(hand: Hand): ItemStack
	fun setHeldItem(item: ItemStack, hand: Hand)
}