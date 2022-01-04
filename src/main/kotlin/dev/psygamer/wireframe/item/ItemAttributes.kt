package dev.psygamer.wireframe.item

import dev.psygamer.wireframe.internal.item.InternalItemAttributes
import dev.psygamer.wireframe.item.util.Rarity
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

/**
 * A factory class for easily creating attributes related to items.
 *
 * @param itemGroup Creative Tab for the Block
 * @see Item
 */
class ItemAttributes @JvmOverloads constructor(var itemGroup: ItemGroup? = null) {
	
	var maxStackSize = 64
		private set
	var isFireResistant = false
		private set
	var isRepairable = false
		private set
	var craftingRemainder: Item? = null
		private set
	var rarity = Rarity.COMMON
		private set
	
	internal val internal: InternalItemAttributes = InternalItemAttributes(this)
	
	fun maxStackSize(maxStackSize: Int): ItemAttributes {
		this.maxStackSize = maxStackSize
		return this
	}
	
	fun fireResistant(fireResistant: Boolean): ItemAttributes {
		isFireResistant = fireResistant
		return this
	}
	
	fun repairable(repairable: Boolean): ItemAttributes {
		isRepairable = repairable
		return this
	}
	
	fun craftingRemainder(craftingRemainder: Item?): ItemAttributes {
		this.craftingRemainder = craftingRemainder
		return this
	}
	
	fun rarity(rarity: Rarity): ItemAttributes {
		this.rarity = rarity
		return this
	}
}