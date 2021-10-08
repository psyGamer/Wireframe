package dev.psygamer.wireframe.test.item_display;

import dev.psygamer.wireframe.item.BlockItem;
import dev.psygamer.wireframe.item.ItemAttributes;
import dev.psygamer.wireframe.util.Identifier;

public class ItemDisplayItem extends BlockItem {
	
	public ItemDisplayItem() {
		super(new Identifier("wireframe", "item_display"),
		
			  new ItemAttributes()
					  .maxStackSize(15),
		
			  new ItemDisplayBlock()
		);
	}
}
