package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.internal.item.InternalItem;
import dev.psygamer.wireframe.util.TagCompound;

public class ItemStack {
	
	protected int count;
	
	protected Item item;
	protected TagCompound tagData;
	
	public ItemStack(final Item item) {
		this(item, 1);
	}
	
	public ItemStack(final Item item, final int count) {
	
	}
	
	public ItemStack(final Item item, final int count, final TagCompound tagData) {
		this.item = item;
		this.count = count;
		
		this.tagData = tagData;
	}
	
	public static ItemStack get(final net.minecraft.item.ItemStack internalStack) {
		return new ItemStack(
				InternalItem.convertItem(internalStack.getItem()),
				
				internalStack.getCount(), TagCompound.get(internalStack.getTag())
		);
	}
	
	public int getCount() {
		return this.count;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public TagCompound getTag() {
		return this.tagData;
	}
	
	public net.minecraft.item.ItemStack toInternal() {
		return new net.minecraft.item.ItemStack(this.item.getInternal(), this.count, this.tagData.getInternal());
	}
}
