package dev.psyGamer.anvil.impl.v16.block;

import dev.psyGamer.anvil.lib.block.BlockFactory;
import dev.psyGamer.anvil.lib.block.properties.HarvestLevel;
import dev.psyGamer.anvil.util.reflection.FieldUtil;
import lombok.Getter;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

@Getter
public class BlockFactoryImpl16 extends BlockFactory {
	
	public BlockFactoryImpl16(final String registryName) {
		super();
	}
	
	public static BlockFactory create(final String registryName) {
		return new BlockFactoryImpl16(registryName);
	}
	
	@Override
	public BlockFactory inheritFromBlock(final Block block) {
		final AbstractBlock.Properties properties = (AbstractBlock.Properties) FieldUtil.getField(block, "properties");
		
		setMaterial((Material) FieldUtil.getField(properties, "material"));
		setSound((SoundType) FieldUtil.getField(properties, "soundType"));
		setGroup(block.asItem().getItemCategory());
		
		setHardness((Float) FieldUtil.getField(properties, "destroyTime"));
		setBlastResistance((Float) FieldUtil.getField(properties, "explosionResistance"));
		
		setRequiredTool(properties.getHarvestTool());
		setHarvestLevel(properties.getHarvestLevel());
		
		isBreakableByHand((Boolean) FieldUtil.getField(properties, "requiresCorrectToolForDrops"));
		
		return this;
	}
	
	@Override
	public Block build() {
		return new Block(createProperties());
	}
	
	private AbstractBlock.Properties createProperties() {
		final AbstractBlock.Properties properties = AbstractBlock.Properties.of(this.material);
		
		if (this.hardness > 0 && this.blastResistance > 0) {
			properties.strength(this.hardness, this.blastResistance);
		} else if (this.hardness > 0) {
			properties.strength(this.hardness);
		}
		
		if (this.tool == null) {
			properties.instabreak();
		} else {
			properties.harvestTool(this.tool);
		}
		
		if (this.harvestLevel != HarvestLevel.HAND) {
			properties.harvestLevel(this.harvestLevel.getLevel());
			
			if (this.requiresToolForDrop) {
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
