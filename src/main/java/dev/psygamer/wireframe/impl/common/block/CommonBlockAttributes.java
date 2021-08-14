package dev.psygamer.wireframe.impl.common.block;

import dev.psygamer.wireframe.api.block.BlockAttributes;
import dev.psygamer.wireframe.api.block.attributes.HarvestLevel;

import dev.psygamer.wireframe.core.impl.MinecraftVersion;
import dev.psygamer.wireframe.core.impl.ImplementationVersion;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

import net.minecraftforge.common.ToolType;

@ImplementationVersion(MinecraftVersion.COMMON)
public abstract class CommonBlockAttributes extends BlockAttributes {
	
	protected Material material;
	protected SoundType sound;
	protected ItemGroup group;
	
	protected float blastResistance;
	protected float hardness;
	
	protected ToolType correctTool;
	protected int harvestLevel;
	
	protected boolean fullBlock;
	
	protected CommonBlockAttributes() {
	}
	
	@Override
	public BlockAttributes copy() {
		return BlockAttributes.create()
				.material(this.material)
				.group(this.group)
				.hardness(this.hardness)
				.blastResistance(this.blastResistance)
				.requiredTool(this.correctTool)
				.harvestLevel(this.harvestLevel)
				.fullBlock(this.fullBlock);
	}
	
	@Override
	public BlockAttributes material(final Material material) {
		this.material = material;
		
		return this;
	}
	
	@Override
	public BlockAttributes group(final ItemGroup group) {
		this.group = group;
		
		return this;
	}
	
	@Override
	public BlockAttributes hardness(final float hardness) {
		this.hardness = hardness;
		
		return this;
	}
	
	@Override
	public BlockAttributes blastResistance(final float blastResistance) {
		this.blastResistance = blastResistance;
		
		return this;
	}
	
	@Override
	public BlockAttributes sound(final SoundType sound) {
		this.sound = sound;
		
		return this;
	}
	
	@Override
	public BlockAttributes requiredTool(final ToolType tool) {
		this.correctTool = tool;
		
		return this;
	}
	
	@Override
	public BlockAttributes harvestLevel(final int level) {
		this.harvestLevel = level;
		
		return this;
	}
	
	@Override
	public BlockAttributes harvestLevel(final HarvestLevel harvestLevel) {
		this.harvestLevel = harvestLevel.getHarvestLevel();
		
		return this;
	}
	
	@Override
	public BlockAttributes fullBlock(final boolean fullBlock) {
		this.fullBlock = fullBlock;
		
		return this;
	}
}
