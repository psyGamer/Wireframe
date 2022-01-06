package dev.psygamer.wireframe.nativeapi.item

import dev.psygamer.wireframe.api.item.ItemAttributes
import dev.psygamer.wireframe.nativeapi.mcNative
import net.minecraft.item.Item

class NativeItemAttributes(private val attributes: ItemAttributes) {
	
	fun createProperties(): Item.Properties {
		val properties = Item.Properties()
			.rarity(attributes.rarity.mcNative)
			.stacksTo(attributes.maxStackSize)
		
		if (attributes.itemGroup != null)
			attributes.itemGroup?.let { properties.tab(it) }
		
		if (attributes.isFireResistant)
			properties.fireResistant()
		if (!attributes.isRepairable)
			properties.setNoRepair()
		
		return properties
	}
}