package dev.psyGamer.anvil.impl.v16.block;

import dev.psyGamer.anvil.impl.common.block.CommonBlockFactory;
import dev.psyGamer.anvil.lib.block.BlockFactory;
import dev.psyGamer.anvil.lib.block.BlockWrapper;
import dev.psyGamer.anvil.lib.block.properties.HarvestLevel;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class BlockFactoryImpl16 extends CommonBlockFactory {
	
	public BlockFactoryImpl16(final String registryName) {
		super();
	}
	
	public static BlockFactory create(final String registryName) {
		return new BlockFactoryImpl16(registryName);
	}
	
	@Override
	@SuppressWarnings("ConstantConditions")
	public BlockFactory inheritFromBlock(final Block block) {
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
	public Block build() {
		final BlockWrapper wrapper = BlockWrapper.create(this);
		
		return new Block(createProperties());
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
