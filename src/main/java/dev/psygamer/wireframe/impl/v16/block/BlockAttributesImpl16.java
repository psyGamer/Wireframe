package dev.psygamer.wireframe.impl.v16.block;

import dev.psygamer.wireframe.api.block.BlockAttributes;
import dev.psygamer.wireframe.api.block.attributes.HarvestLevel;
import dev.psygamer.wireframe.core.impl.ImplementationVersion;
import dev.psygamer.wireframe.core.impl.InstanceConstructor;
import dev.psygamer.wireframe.core.impl.MinecraftVersion;

import dev.psygamer.wireframe.impl.common.block.CommonBlockAttributes;

import net.minecraft.item.ItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;

@ImplementationVersion(MinecraftVersion.v16)
public class BlockAttributesImpl16 extends CommonBlockAttributes {
	
	@InstanceConstructor
	protected BlockAttributesImpl16() {
		super();
	}
	
	protected BlockAttributesImpl16(final Material material) {
		this();
		
		setMaterial(material);
	}
	
	protected BlockAttributesImpl16(final Material material, final ItemGroup group) {
		this(material);
		
		setGroup(group);
	}
	
	@Override
	protected BlockAttributes createInstance() {
		return new BlockAttributesImpl16();
	}
	
	@Override
	protected BlockAttributes createInstance(final Material material) {
		return new BlockAttributesImpl16(material);
	}
	
	@Override
	protected BlockAttributes createInstance(final Material material, final ItemGroup group) {
		return new BlockAttributesImpl16(material, group);
	}
	
	public AbstractBlock.Properties createProperties() {
		final AbstractBlock.Properties properties = AbstractBlock.Properties.of(this.material);
		
		if (this.hardness > 0 && this.blastResistance > 0) {
			properties.strength(this.hardness, this.blastResistance);
		} else if (this.hardness > 0) {
			properties.strength(this.hardness);
		}
		
		if (this.correctTool == null) {
			properties.instabreak();
		} else {
			properties.harvestTool(this.correctTool);
		}
		
		properties.harvestLevel(this.harvestLevel);
		
		if (this.harvestLevel == HarvestLevel.NONE.getHarvestLevel()) {
			properties.requiresCorrectToolForDrops();
		}
		
		if (this.sound != null) {
			properties.sound(this.sound);
		}
		
		if (this.fullBlock) {
			properties.isValidSpawn(AbstractBlock.AbstractBlockState::isValidSpawn);
			properties.isSuffocating(AbstractBlock.AbstractBlockState::isSuffocating);
			properties.isViewBlocking(AbstractBlock.AbstractBlockState::isViewBlocking);
			properties.noCollission();
		}
		
		return properties;
	}
}
