package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.internal.item.InternalItemFoundation;
import net.minecraft.nbt.CompoundNBT;

public class ItemStack {
	
	protected int count;
	
	protected ItemFoundation item;
	protected CompoundNBT tagData;
	
	public ItemStack(final ItemFoundation item) {
		this(item, 1);
	}
	
	public ItemStack(final ItemFoundation item, final int count) {
	
	}
	
	public ItemStack(final ItemFoundation item, final int count, final CompoundNBT tagData) {
		this.item = item;
		this.count = count;
		
		this.tagData = tagData;
	}
	
	public static ItemStack fromInternal(final net.minecraft.item.ItemStack internalStack) {
		return new ItemStack(
				InternalItemFoundation.convertItem(internalStack.getItem()),
				internalStack.getCount(),
				internalStack.getTag()
		);
	}
	
	public net.minecraft.item.ItemStack toInternal() {
		return new net.minecraft.item.ItemStack(this.item.getInternal(), this.count, this.tagData);
	}
}
