package dev.psygamer.wireframe.internal.block;

import dev.psygamer.wireframe.block.BlockAttributes;
import dev.psygamer.wireframe.block.attributes.HarvestLevel;
import dev.psygamer.wireframe.block.attributes.Material;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class InternalBlockAttributes {
	
	private final BlockAttributes attributes;
	
	public InternalBlockAttributes(final BlockAttributes attributes) {
		this.attributes = attributes;
	}
	
	private static boolean always(BlockState blockState, IBlockReader blockReader, BlockPos pos) {
		return true;
	}
	
	private static boolean always(BlockState blockState, IBlockReader blockReader, BlockPos pos, EntityType<?> entityType) {
		return true;
	}
	
	private static boolean never(BlockState blockState, IBlockReader blockReader, BlockPos pos) {
		return false;
	}
	
	private static boolean never(BlockState blockState, IBlockReader blockReader, BlockPos pos, EntityType<?> entityType) {
		return false;
	}
	
	public AbstractBlock.Properties createProperties() {
		final AbstractBlock.Properties properties = AbstractBlock.Properties.of(
				this.attributes.getMaterial()
							   .getInternal()
		);
		
		if (this.attributes.getHardness() >= 0 && this.attributes.getBlastResistance() >= 0)
			properties.strength(this.attributes.getHardness(), this.attributes.getBlastResistance());
		else if (this.attributes.getHardness() >= 0)
			properties.strength(this.attributes.getHardness());
		else
			properties.instabreak();
		
		if (this.attributes.getCorrectTool() != null)
			properties.harvestTool(this.attributes.getCorrectTool());
		
		properties.harvestLevel(this.attributes.getHarvestLevel());
		
		if (this.attributes.getHarvestLevel() != HarvestLevel.NONE.getHarvestLevel())
			properties.requiresCorrectToolForDrops();
		
		if (this.attributes.getSound() != null)
			properties.sound(this.attributes.getSound());
		
		if (this.attributes.isFullBlock() && !this.attributes.isOpaque()) {
			properties.isValidSpawn(InternalBlockAttributes::always);
			properties.isSuffocating(InternalBlockAttributes::always);
			properties.isViewBlocking(InternalBlockAttributes::always);
		} else {
			properties.isValidSpawn(InternalBlockAttributes::never);
			properties.isSuffocating(InternalBlockAttributes::never);
			properties.isViewBlocking(InternalBlockAttributes::never);
		}
		
		if (this.attributes.getMaterial() == Material.AIR)
			properties.air();
		
		return properties;
	}
}
