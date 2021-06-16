package dev.psygamer.ferrus.impl.common.block;

import dev.psygamer.ferrus.lib.block.BlockFactory;
import dev.psygamer.ferrus.lib.block.properties.HarvestLevel;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.Property;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommonBlockFactory implements BlockFactory {
	
	protected String registryName;
	
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
	
	@Override
	public BlockFactory inheritFromBlock(final Block block) {
		return null;
	}
	
	@Override
	public BlockFactory copy() {
		return BlockFactory.create(this.registryName)
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
	public BlockFactory setMaterial(final Material material) {
		this.material = material;
		
		return this;
	}
	
	@Override
	public BlockFactory setGroup(final ItemGroup group) {
		this.group = group;
		
		return this;
	}
	
	@Override
	public BlockFactory setHardness(final float hardness) {
		this.hardness = hardness;
		
		return this;
	}
	
	@Override
	public BlockFactory multiplyHardness(final float factor) {
		this.hardness *= factor;
		
		return this;
	}
	
	@Override
	public BlockFactory setBlastResistance(final float blastResistance) {
		this.blastResistance = blastResistance;
		
		return this;
	}
	
	@Override
	public BlockFactory multiplyBlastResistance(final float factor) {
		this.blastResistance *= factor;
		
		return this;
	}
	
	@Override
	public BlockFactory setStrength(final float strength) {
		this.hardness = strength;
		this.blastResistance = strength;
		
		return this;
	}
	
	@Override
	public BlockFactory multiplyStrength(final float factor) {
		this.hardness *= factor;
		this.blastResistance *= factor;
		
		return this;
	}
	
	@Override
	public BlockFactory setSound(final SoundType sound) {
		this.sound = sound;
		
		return this;
	}
	
	@Override
	public BlockFactory setRequiredTool(final ToolType tool) {
		this.requiredTool = tool;
		
		return this;
	}
	
	@Override
	public BlockFactory setHarvestLevel(final int level) {
		this.harvestLevel = HarvestLevel.values()[Math.max(0, Math.min(level - 1, HarvestLevel.values().length))];
		
		return this;
	}
	
	@Override
	public BlockFactory setHarvestLevel(final HarvestLevel harvestLevel) {
		this.harvestLevel = harvestLevel;
		
		return this;
	}
	
	@Override
	public BlockFactory increaseHarvestLevel() {
		this.harvestLevel = HarvestLevel.values()[Math.max(HarvestLevel.values().length, this.harvestLevel.getLevel() + 1)];
		
		return this;
	}
	
	@Override
	public BlockFactory decreaseHarvestLevel() {
		this.harvestLevel = HarvestLevel.values()[Math.max(0, this.harvestLevel.getLevel() - 1)];
		
		return this;
	}
	
	@Override
	public BlockFactory setBreakableByHand(final boolean breakableByHand) {
		this.breakableByHand = !breakableByHand;
		
		return this;
	}
	
	@Override
	public BlockFactory setFullBlock(final boolean fullBlock) {
		this.fullBlock = fullBlock;
		
		return this;
	}
	
	
	@Override
	public BlockFactory setOpaque(final boolean opaque) {
		this.opaque = opaque;
		
		return this;
	}
	
	
	@Override
	public BlockFactory addBlockStateProperty(final Property<?> property) {
		this.blockStateProperties.add(property);
		
		return this;
	}
	
	@Override
	public Block build() {
		return null;
	}
}
