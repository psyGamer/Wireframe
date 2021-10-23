package dev.psygamer.wireframe.test.item_display;

import dev.psygamer.wireframe.block.entity.BlockEntity;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.util.TagCompound;

public class ItemDisplayBlockEntity extends BlockEntity {
	
	private int clicks = 0;
	
	public ItemDisplayBlockEntity() {
		super(new Identifier("wireframe", "item_display"));
	}
	
	public void addClick() {
		this.clicks++;
		
		this.markChanged();
	}
	
	@Override
	public void saveNBT(final TagCompound tagCompound) {
		tagCompound.putInt("clicks", this.clicks);
	}
	
	@Override
	public void loadNBT(final TagCompound tagCompound) {
		this.clicks = tagCompound.getInt("clicks");
	}
}