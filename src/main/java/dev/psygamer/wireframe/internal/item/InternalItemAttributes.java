package dev.psygamer.wireframe.internal.item;

import dev.psygamer.wireframe.item.ItemAttributes;
import net.minecraft.item.Item;

public class InternalItemAttributes {
	
	private final ItemAttributes attributes;
	
	public InternalItemAttributes(final ItemAttributes attributes) {
		this.attributes = attributes;
	}
	
	public Item.Properties createProperties() {
		final Item.Properties properties = new Item.Properties()
				.rarity(this.attributes.getRarity().getInternal())
				.stacksTo(this.attributes.getMaxStackSize());
		
		if (this.attributes.getItemGroup() != null)
			properties.tab(this.attributes.getItemGroup());
		
		if (this.attributes.isFireResistant())
			properties.fireResistant();
		
		if (!this.attributes.isRepairable())
			properties.setNoRepair();
		
		return properties;
	}
}
