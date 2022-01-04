package dev.psygamer.wireframe.item

import dev.psygamer.wireframe.block.state.BlockState
import dev.psygamer.wireframe.entity.LivingEntity
import dev.psygamer.wireframe.entity.Player
import dev.psygamer.wireframe.internal.item.InternalItem
import dev.psygamer.wireframe.item.util.ClickResult
import dev.psygamer.wireframe.item.util.ClickResultContainer
import dev.psygamer.wireframe.item.util.Hand
import dev.psygamer.wireframe.registry.ItemRegistry
import dev.psygamer.wireframe.util.BlockPosition
import dev.psygamer.wireframe.util.Identifier
import dev.psygamer.wireframe.util.math.BlockHitResult
import dev.psygamer.wireframe.wfWrapped
import dev.psygamer.wireframe.world.World

open class Item {
	
	internal val mcNative: net.minecraft.item.Item
	
	val identifier: Identifier
	val itemAttributes: ItemAttributes?
	
	internal constructor(internal: net.minecraft.item.Item) {
		this.identifier = internal.registryName!!.wfWrapped
		this.itemAttributes = null
		
		this.mcNative = internal
	}
	
	protected constructor(identifier: Identifier, itemAttributes: ItemAttributes?) {
		this.identifier = identifier
		this.itemAttributes = itemAttributes
		
		this.mcNative = InternalItem(this, itemAttributes)
		
		ItemRegistry.register(this)
	}
	
	fun onItemUsed(
		itemStack: ItemStack, player: Player, hand: Hand,
		world: World
	): ClickResultContainer<ItemStack> {
		return ClickResultContainer.pass(player.getHeldItem(hand))
	}
	
	open fun onItemUsedOnBlock(
		itemStack: ItemStack, player: Player, hand: Hand,
		world: World, hitResult: BlockHitResult
	): ClickResult {
		return ClickResult.PASS
	}
	
	fun onItemUsedOnEntity(
		itemStack: ItemStack, player: Player, hand: Hand,
		world: World, entity: LivingEntity,
	): ClickResult {
		return ClickResult.PASS
	}
	
	fun onEntityAttacked(
		itemStack: ItemStack, entity: LivingEntity,
		world: World, attacker: LivingEntity,
	): Boolean {
		return false
	}
	
	fun onBlockMined(
		itemStack: ItemStack, entity: LivingEntity,
		world: World, blockPosition: BlockPosition, blockState: BlockState,
	): Boolean {
		return false
	}
	
	fun onItemCrafted(
		usedItemStack: ItemStack, player: Player,
		world: World
	): Boolean {
		return false
	}
}