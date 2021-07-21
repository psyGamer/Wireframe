package dev.psygamer.wireframecore.impl.common.block;

import dev.psygamer.wireframecore.impl.ImplementationVersion;
import dev.psygamer.wireframecore.impl.MinecraftVersion;
import dev.psygamer.wireframe.block.BlockFactory;
import dev.psygamer.wireframe.block.properties.HarvestLevel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.Property;

import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

@ImplementationVersion(MinecraftVersion.COMMON)
public abstract class CommonBlockFactory implements BlockFactory {
	
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
	
	public CommonBlockFactory(final String registryName) {
		this.registryName = registryName;
	}
	
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
	public String getRegistryName() {
		return this.registryName;
	}
	
	@Override
	public BlockFactory setMaterial(final Material material) {
		this.material = material;
		
		return this;
	}
	
	@Override
	public Material getMaterial() {
		return this.material;
	}
	
	@Override
	public BlockFactory setGroup(final ItemGroup group) {
		this.group = group;
		
		return this;
	}
	
	@Override
	public ItemGroup getGroup() {
		return this.group;
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
	public float getHardness() {
		return this.hardness;
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
	public float getBlastResistance() {
		return this.blastResistance;
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
	public SoundType getSound() {
		return this.sound;
	}
	
	@Override
	public BlockFactory setRequiredTool(final ToolType tool) {
		this.requiredTool = tool;
		
		return this;
	}
	
	@Override
	public ToolType getRequiredTool() {
		return this.requiredTool;
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
	public HarvestLevel getHarvestLevel() {
		return this.harvestLevel;
	}
	
	@Override
	public BlockFactory setBreakableByHand(final boolean breakableByHand) {
		this.breakableByHand = !breakableByHand;
		
		return this;
	}
	
	@Override
	public boolean isBreakableByHand() {
		return this.breakableByHand;
	}
	
	@Override
	public BlockFactory setFullBlock(final boolean fullBlock) {
		this.fullBlock = fullBlock;
		
		return this;
	}
	
	@Override
	public boolean isFullBlock() {
		return this.fullBlock;
	}
	
	@Override
	public BlockFactory setOpaque(final boolean opaque) {
		this.opaque = opaque;
		
		return this;
	}
	
	@Override
	public boolean isOpaque() {
		return this.opaque;
	}
	
	@Override
	public BlockFactory addBlockStateProperty(final Property<?> property) {
		this.blockStateProperties.add(property);
		
		return this;
	}
}
