package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.internal.item.InternalItemFoundation;
import dev.psygamer.wireframe.util.TagCompound;

public class ItemStack {
	
	protected int count;
	
	protected ItemFoundation item;
	protected TagCompound tagData;
	
	public ItemStack(final ItemFoundation item) {
		this(item, 1);
	}
	
	public ItemStack(final ItemFoundation item, final int count) {
	
	}
	
	public ItemStack(final ItemFoundation item, final int count, final TagCompound tagData) {
		this.item = item;
		this.count = count;
		
		this.tagData = tagData;
	}
	
	public static ItemStack get(final net.minecraft.item.ItemStack internalStack) {
		return new ItemStack(
				InternalItemFoundation.convertItem(internalStack.getItem()),
				
				internalStack.getCount(), TagCompound.get(internalStack.getTag())
		);
	}
	
	public net.minecraft.item.ItemStack toInternal() {
		return new net.minecraft.item.ItemStack(this.item.getInternal(), this.count, this.tagData.getInternal());
	}
}
