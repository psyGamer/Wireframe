package dev.psygamer.wireframe.impl.v16.item;

import dev.psygamer.wireframe.item.ItemAttributes;
import dev.psygamer.wireframe.impl.common.item.CommonItemAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemAttributesImpl16 extends CommonItemAttributes {
	
	protected ItemGroup group;
	
	protected ItemAttributesImpl16() {
	}
	
	protected ItemAttributesImpl16(final ItemGroup group) {
		this.group = group;
	}
	
	public Item.Properties createProperties() {
		final Item.Properties properties = new Item.Properties()
				.rarity(this.rarity)
				.stacksTo(this.maxStackSize);
		
		if (this.group != null)
			properties.tab(this.group);
		
		if (this.fireResistant)
			properties.fireResistant();
		
		if (!this.repairable)
			properties.setNoRepair();
		
		return properties;
	}
	
	@Override
	protected ItemAttributes createInstance() {
		return new ItemAttributesImpl16();
	}
	
	@Override
	protected ItemAttributes createInstance(final ItemGroup group) {
		return new ItemAttributesImpl16(group);
	}
}
