package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.internal.item.InternalItem;
import dev.psygamer.wireframe.util.TagCompound;
import dev.psygamer.wireframe.util.TagCompoundKt;

public class ItemStack {
	
	private final net.minecraft.item.ItemStack internal;
	
	private Item item;
	
	public ItemStack(final Item item) {
		this(item, 1);
	}
	
	public ItemStack(final Item item, final int count) {
		this(item, count, null);
	}
	
	public ItemStack(final Item item, final int count, final TagCompound tagData) {
		this.item = item;
		
		this.internal = new net.minecraft.item.ItemStack(item.getInternal(), count, tagData.getMcNative$wireframe());
	}
	
	private ItemStack(final net.minecraft.item.ItemStack internal) {
		this.item = InternalItem.convertItem(internal.getItem());
		
		this.internal = internal;
	}
	
	public static ItemStack get(final net.minecraft.item.ItemStack internalStack) {
		if (internalStack == null)
			return null;
		
		return new ItemStack(internalStack);
	}
	
	public void grow(final int amount) {
		this.internal.grow(amount);
	}
	
	public void shrink(final int amount) {
		this.internal.shrink(amount);
	}
	
	public int getCount() {
		return this.internal.getCount();
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public TagCompound getTag() {
		return TagCompoundKt.getWfWrapped(this.internal.getTag());
	}
	
	public net.minecraft.item.ItemStack getInternal() {
		return this.internal;
	}
}
