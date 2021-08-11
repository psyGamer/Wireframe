package dev.psygamer.wireframe.impl.v16.block;

import dev.psygamer.wireframe.api.block.BlockAttributes;
import dev.psygamer.wireframe.core.impl.ImplementationVersion;
import dev.psygamer.wireframe.core.impl.MinecraftVersion;

import dev.psygamer.wireframe.impl.common.block.CommonBlockAttributes;

import net.minecraft.item.ItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@ImplementationVersion(MinecraftVersion.v16)
public class BlockAttributesImpl16 extends CommonBlockAttributes {
	
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
	
	@Override
	@SuppressWarnings("ConstantConditions")
	public BlockAttributes inheritFromBlock(final Block block) {
		final AbstractBlock.Properties properties = ObfuscationReflectionHelper.getPrivateValue(AbstractBlock.class, block, "field_235684_aB_");
		final Class<AbstractBlock.Properties> propertiesClass = AbstractBlock.Properties.class;
		
		assert properties != null;
		
		setMaterial(ObfuscationReflectionHelper.getPrivateValue(propertiesClass, properties, "field_200953_a"));
		setSound(ObfuscationReflectionHelper.getPrivateValue(propertiesClass, properties, "field_200956_d"));
		setGroup(block.asItem().getItemCategory());
		
		setHardness(ObfuscationReflectionHelper.getPrivateValue(propertiesClass, properties, "field_200959_g"));
		setBlastResistance(ObfuscationReflectionHelper.getPrivateValue(propertiesClass, properties, "field_200958_f"));
		
		setRequiredTool(properties.getHarvestTool());
		setHarvestLevel(properties.getHarvestLevel());
		
		setBreakableByHand(!(Boolean) ObfuscationReflectionHelper.getPrivateValue(propertiesClass, properties, "field_235806_h_"));
		
		return this;
	}
	
	@Override
	public Block createBlock() {
		return new Block(createProperties()) {
		
		};
	}
	
	private AbstractBlock.Properties createProperties() {
		final AbstractBlock.Properties properties = AbstractBlock.Properties.of(this.material);
		
		if (this.hardness > 0 && this.blastResistance > 0) {
			properties.strength(this.hardness, this.blastResistance);
		} else if (this.hardness > 0) {
			properties.strength(this.hardness);
		}
		
		if (this.requiredTool == null) {
			properties.instabreak();
		} else {
			properties.harvestTool(this.requiredTool);
		}
		
		if (this.harvestLevel != HarvestLevel.HAND) {
			properties.harvestLevel(this.harvestLevel.getLevel());
			
			if (this.breakableByHand) {
				properties.requiresCorrectToolForDrops();
			}
		}
		
		if (this.sound != null) {
			properties.sound(this.sound);
		}
		
		if (this.fullBlock && !this.opaque) {
			properties.isValidSpawn(AbstractBlock.AbstractBlockState::isValidSpawn);
			properties.isSuffocating(AbstractBlock.AbstractBlockState::isSuffocating);
			properties.isViewBlocking(AbstractBlock.AbstractBlockState::isViewBlocking);
			properties.noCollission();
		}
		
		return properties;
	}
}
