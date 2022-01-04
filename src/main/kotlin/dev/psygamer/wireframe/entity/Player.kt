package dev.psygamer.wireframe.entity

import dev.psygamer.wireframe.item.ItemStack
import dev.psygamer.wireframe.item.util.Hand
import dev.psygamer.wireframe.util.math.vector.Vector3d

interface Player : LivingEntity {
	
	var foodLevel: Int
	var saturationLevel: Float
	
	val movementVector: Vector3d?
	val isCrouching: Boolean
	val isCreative: Boolean
	
	fun getHeldItem(hand: Hand): ItemStack
	fun setHeldItem(item: ItemStack, hand: Hand)
}