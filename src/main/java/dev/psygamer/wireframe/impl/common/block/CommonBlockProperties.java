package dev.psygamer.wireframe.impl.common.block;

import dev.psygamer.wireframe.api.block.BlockAttributes;

import dev.psygamer.wireframe.core.impl.MinecraftVersion;
import dev.psygamer.wireframe.core.impl.ImplementationVersion;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.Property;

import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

@ImplementationVersion(MinecraftVersion.COMMON)
public abstract class CommonBlockProperties extends BlockAttributes {
	
	protected Material material;
	protected SoundType sound;
	protected ItemGroup group;
	
	protected float blastResistance;
	protected float hardness;
	
	protected ToolType requiredTool;
	protected HarvestLevel harvestLevel;
	
	protected boolean breakableByHand;
	protected boolean fullBlock;
	protected boolean opaque;
	
	protected List<Property<?>> blockStateProperties = new ArrayList<>();
	
	protected CommonBlockProperties() {
	}
	
	@Override
	public BlockAttributes inheritFromBlock(final Block block) {
		return null;
	}
	
	@Override
	public BlockAttributes copy() {
		return BlockAttributes.create()
				.setMaterial(this.material)
				.setGroup(this.group)
				.setHardness(this.hardness)
				.setBlastResistance(this.blastResistance)
				.setRequiredTool(this.requiredTool)
				.setHarvestLevel(this.harvestLevel)
				.setBreakableByHand(!this.breakableByHand)
				.setFullBlock(this.fullBlock)
				.setOpaque(this.opaque);
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
	public BlockAttributes multiplyHardness(final float factor) {
		this.hardness *= factor;
		
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
	public BlockAttributes multiplyBlastResistance(final float factor) {
		this.blastResistance *= factor;
		
		return this;
	}
	
	@Override
	public float getBlastResistance() {
		return this.blastResistance;
	}
	
	@Override
	public BlockAttributes setStrength(final float strength) {
		this.hardness = strength;
		this.blastResistance = strength;
		
		return this;
	}
	
	@Override
	public BlockAttributes multiplyStrength(final float factor) {
		this.hardness *= factor;
		this.blastResistance *= factor;
		
		return this;
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
		this.requiredTool = tool;
		
		return this;
	}
	
	@Override
	public ToolType getRequiredTool() {
		return this.requiredTool;
	}
	
	@Override
	public BlockAttributes setHarvestLevel(final int level) {
		this.harvestLevel = HarvestLevel.values()[Math.max(0, Math.min(level - 1, HarvestLevel.values().length))];
		
		return this;
	}
	
	@Override
	public BlockAttributes setHarvestLevel(final HarvestLevel harvestLevel) {
		this.harvestLevel = harvestLevel;
		
		return this;
	}
	
	@Override
	public BlockAttributes increaseHarvestLevel() {
		this.harvestLevel = HarvestLevel.values()[Math.max(HarvestLevel.values().length, this.harvestLevel.getLevel() + 1)];
		
		return this;
	}
	
	@Override
	public BlockAttributes decreaseHarvestLevel() {
		this.harvestLevel = HarvestLevel.values()[Math.max(0, this.harvestLevel.getLevel() - 1)];
		
		return this;
	}
	
	@Override
	public HarvestLevel getHarvestLevel() {
		return this.harvestLevel;
	}
	
	@Override
	public BlockAttributes setBreakableByHand(final boolean breakableByHand) {
		this.breakableByHand = !breakableByHand;
		
		return this;
	}
	
	@Override
	public boolean isBreakableByHand() {
		return this.breakableByHand;
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
	
	@Override
	public BlockAttributes setOpaque(final boolean opaque) {
		this.opaque = opaque;
		
		return this;
	}
	
	@Override
	public boolean isOpaque() {
		return this.opaque;
	}
	
	@Override
	public BlockAttributes addBlockStateProperty(final Property<?> property) {
		this.blockStateProperties.add(property);
		
		return this;
	}
}
