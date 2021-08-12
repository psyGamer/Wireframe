package dev.psygamer.wireframe.impl.common.block;

import dev.psygamer.wireframe.api.block.BlockAttributes;
import dev.psygamer.wireframe.api.block.attributes.HarvestLevel;

import dev.psygamer.wireframe.core.impl.MinecraftVersion;
import dev.psygamer.wireframe.core.impl.ImplementationVersion;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.Property;

import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

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
	
	protected List<Property<?>> blockStateProperties = new ArrayList<>();
	
	protected CommonBlockAttributes() {
	}
	
	@Override
	public BlockAttributes copy() {
		return BlockAttributes.create()
				.setMaterial(this.material)
				.setGroup(this.group)
				.setHardness(this.hardness)
				.setBlastResistance(this.blastResistance)
				.setRequiredTool(this.correctTool)
				.setHarvestLevel(this.harvestLevel)
				.setFullBlock(this.fullBlock);
	}
	
	@Override
	public BlockAttributes setMaterial(final Material material) {
		this.material = material;
		
		return this;
	}
	
	@Override
	public Material getMaterial() {
		return this.material;
	}
	
	@Override
	public BlockAttributes setGroup(final ItemGroup group) {
		this.group = group;
		
		return this;
	}
	
	@Override
	public ItemGroup getGroup() {
		return this.group;
	}
	
	@Override
	public BlockAttributes setHardness(final float hardness) {
		this.hardness = hardness;
		
		return this;
	}
	
	@Override
	public float getHardness() {
		return this.hardness;
	}
	
	@Override
	public BlockAttributes setBlastResistance(final float blastResistance) {
		this.blastResistance = blastResistance;
		
		return this;
	}
	
	@Override
	public float getBlastResistance() {
		return this.blastResistance;
	}
	
	@Override
	public BlockAttributes setSound(final SoundType sound) {
		this.sound = sound;
		
		return this;
	}
	
	@Override
	public SoundType getSound() {
		return this.sound;
	}
	
	@Override
	public BlockAttributes setRequiredTool(final ToolType tool) {
		this.correctTool = tool;
		
		return this;
	}
	
	@Override
	public ToolType getCorrectTool() {
		return this.correctTool;
	}
	
	@Override
	public BlockAttributes setHarvestLevel(final int level) {
		this.harvestLevel = level;
		
		return this;
	}
	
	@Override
	public BlockAttributes setHarvestLevel(final HarvestLevel harvestLevel) {
		this.harvestLevel = harvestLevel.getHarvestLevel();
		
		return this;
	}
	
	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}
	
	@Override
	public BlockAttributes setFullBlock(final boolean fullBlock) {
		this.fullBlock = fullBlock;
		
		return this;
	}
	
	@Override
	public boolean isFullBlock() {
		return this.fullBlock;
	}
}
