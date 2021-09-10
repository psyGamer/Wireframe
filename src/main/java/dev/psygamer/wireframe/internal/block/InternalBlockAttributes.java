package dev.psygamer.wireframe.internal.block;

import dev.psygamer.wireframe.block.BlockAttributes;
import dev.psygamer.wireframe.block.attributes.HarvestLevel;
import net.minecraft.block.AbstractBlock;

public class InternalBlockAttributes {
	
	private final BlockAttributes attributes;
	
	public InternalBlockAttributes(final BlockAttributes attributes) {
		this.attributes = attributes;
	}
	
	public AbstractBlock.Properties createProperties() {
		final AbstractBlock.Properties properties = AbstractBlock.Properties.of(this.attributes.getMaterial());
		
		if (this.attributes.getHardness() > 0 && this.attributes.getBlastResistance() > 0) {
			properties.strength(this.attributes.getHardness(), this.attributes.getBlastResistance());
		} else if (this.attributes.getHardness() > 0) {
			properties.strength(this.attributes.getHardness());
		}
		
		if (this.attributes.getCorrectTool() == null) {
			properties.instabreak();
		} else {
			properties.harvestTool(this.attributes.getCorrectTool());
		}
		
		properties.harvestLevel(this.attributes.getHarvestLevel());
		
		if (this.attributes.getHarvestLevel() == HarvestLevel.NONE.getHarvestLevel()) {
			properties.requiresCorrectToolForDrops();
		}
		
		if (this.attributes.getSound() != null) {
			properties.sound(this.attributes.getSound());
		}
		
		if (this.attributes.isFullBlock()) {
			properties.isValidSpawn(AbstractBlock.AbstractBlockState::isValidSpawn);
			properties.isSuffocating(AbstractBlock.AbstractBlockState::isSuffocating);
			properties.isViewBlocking(AbstractBlock.AbstractBlockState::isViewBlocking);
			properties.noCollission();
		}
		
		return properties;
	}
}
