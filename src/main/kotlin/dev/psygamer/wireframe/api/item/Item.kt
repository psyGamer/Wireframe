package dev.psygamer.wireframe.api.item

import dev.psygamer.wireframe.api.block.BlockState
import dev.psygamer.wireframe.api.entity.*
import dev.psygamer.wireframe.api.item.util.*
import dev.psygamer.wireframe.api.registry.ItemRegistry
import dev.psygamer.wireframe.api.world.World
import dev.psygamer.wireframe.nativeapi.item.NativeItem
import dev.psygamer.wireframe.nativeapi.wfWrapped
import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.math.BlockHitResult

open class Item {

	internal val mcNative: net.minecraft.item.Item

	val identifier: Identifier
	val itemAttributes: ItemAttributes?

	internal constructor(internal: net.minecraft.item.Item) {
		this.identifier = internal.registryName!!.wfWrapped
		this.itemAttributes = null

		this.mcNative = internal
	}

	protected constructor(identifier: Identifier, itemAttributes: ItemAttributes = ItemAttributes()) {
		this.identifier = identifier
		this.itemAttributes = itemAttributes

		this.mcNative = NativeItem(this, itemAttributes)

		ItemRegistry.register(this)
	}

	fun onItemUsed(
		itemStack: ItemStack, player: Player, hand: Hand,
		world: World,
	): ClickResultContainer<ItemStack> {
		return ClickResultContainer.pass(player.getHeldItem(hand))
	}

	open fun onItemUsedOnBlock(
		itemStack: ItemStack, player: Player, hand: Hand,
		world: World, hitResult: BlockHitResult,
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
		world: World,
	): Boolean {
		return false
	}
}