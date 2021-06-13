package dev.psyGamer.anvil.lib.block;

import dev.psyGamer.anvil.core.version.MinecraftVersion;
import dev.psyGamer.anvil.core.version.ImplementationHandler;
import dev.psyGamer.anvil.core.version.SupportedSince;
import dev.psyGamer.anvil.lib.block.properties.HarvestLevel;
import dev.psyGamer.anvil.lib.util.ICloneable;
import dev.psyGamer.anvil.lib.util.IFactory;

import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;


/**
 * A factory class for easily creating {@link Block Blocks} and registering automatically.
 * <p>
 *
 * @author psyGamer
 * @version 1.0 | 1.16 +
 * @see Block
 * @since 1.0
 */
@Getter
@SupportedSince(MinecraftVersion.v16)
public abstract class BlockFactory implements IFactory<Block>, ICloneable<BlockFactory> {
	
	protected String registryName;
	
	protected Material material;
	protected SoundType sound;
	protected ItemGroup group;
	
	protected float blastResistance;
	protected float hardness;
	
	protected ToolType tool;
	protected HarvestLevel harvestLevel;
	
	protected boolean requiresToolForDrop = true;
	protected boolean fullBlock;
	protected boolean opaque;
	
	protected List<Property<?>> blockStateProperties = new ArrayList<>();
	
	/**
	 * @param blockName Used to register and localize it.
	 *                  <p> -> Registered under: modid:blockname</p>
	 *                  <p> -> Localized under: block.modid.blockname</p>
	 * @return A new instance of a {@link BlockFactory}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static BlockFactory create(final String blockName) {
		return (BlockFactory) ImplementationHandler.executeImplementation(blockName);
	}
	
	/**
	 * @param blockName Used to register and localize it.
	 *                  <p> -> Registered under: modid:blockname</p>
	 *                  <p> -> Localized under: block.modid.blockname</p>
	 * @param material  For sound, hardness, etc.
	 * @return A new instance of a {@link BlockFactory}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static BlockFactory create(final String blockName, final Material material) {
		return (BlockFactory) ImplementationHandler.executeImplementation(blockName, material);
	}
	
	/**
	 * @param blockName Used to register and localize it.
	 *                  <p> -> Registered under: modid:blockname</p>
	 *                  <p> -> Localized under: block.modid.blockname</p>
	 * @param material  For sound, hardness, etc.
	 * @param group     Creative Tab for the Block
	 * @return A new instance of a {@link BlockFactory}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static BlockFactory create(final String blockName, final Material material, final ItemGroup group) {
		return (BlockFactory) ImplementationHandler.executeImplementation(blockName, material, group);
	}
	
	@Override
	public BlockFactory copy() {
		final BlockFactory copy = BlockFactory.create(this.registryName);
		
		copy.material = this.material;
		copy.sound = this.sound;
		copy.group = this.group;
		copy.blastResistance = this.blastResistance;
		copy.hardness = this.hardness;
		copy.tool = this.tool;
		copy.harvestLevel = this.harvestLevel;
		copy.requiresToolForDrop = this.requiresToolForDrop;
		copy.fullBlock = this.fullBlock;
		copy.opaque = this.opaque;
		
		return copy;
	}
	
	public BlockFactory setMaterial(final Material material) {
		this.material = material;
		return this;
	}
	
	public BlockFactory setGroup(final ItemGroup group) {
		this.group = group;
		
		return this;
	}
	
	public BlockFactory setHardness(final float hardness) {
		this.hardness = hardness;
		
		return this;
	}
	
	public BlockFactory multiplyHardness(final float factor) {
		this.hardness *= factor;
		
		return this;
	}
	
	public BlockFactory setBlastResistance(final float blastResistance) {
		this.blastResistance = blastResistance;
		
		return this;
	}
	
	public BlockFactory multiplyBlastResistance(final float factor) {
		this.blastResistance *= factor;
		
		return this;
	}
	
	public BlockFactory setStrength(final float strength) {
		this.hardness = strength;
		this.blastResistance = strength;
		
		return this;
	}
	
	public BlockFactory multiplyStrength(final float factor) {
		this.hardness *= factor;
		this.blastResistance *= factor;
		
		return this;
	}
	
	public BlockFactory setSound(final SoundType sound) {
		this.sound = sound;
		
		return this;
	}
	
	public BlockFactory setRequiredTool(final ToolType tool) {
		this.tool = tool;
		
		return this;
	}
	
	public BlockFactory setHarvestLevel(final int level) {
		this.harvestLevel = HarvestLevel.values()[Math.max(0, Math.min(level - 1, HarvestLevel.values().length))];
		
		return this;
	}
	
	public BlockFactory setHarvestLevel(final HarvestLevel harvestLevel) {
		this.harvestLevel = harvestLevel;
		
		return this;
	}
	
	public BlockFactory increaseHarvestLevel() {
		this.harvestLevel = HarvestLevel.values()[Math.max(HarvestLevel.values().length, this.harvestLevel.getLevel() + 1)];
		
		return this;
	}
	
	public BlockFactory decreaseHarvestLevel() {
		this.harvestLevel = HarvestLevel.values()[Math.max(0, this.harvestLevel.getLevel() - 1)];
		
		return this;
	}
	
	public BlockFactory isBreakableByHand(final boolean breakableByHand) {
		this.requiresToolForDrop = !breakableByHand;
		
		return this;
	}
	
	public BlockFactory isBreakableByHand() {
		this.requiresToolForDrop = false;
		
		return this;
	}
	
	public BlockFactory isNotBreakableByHand() {
		this.requiresToolForDrop = true;
		
		return this;
	}
	
	public BlockFactory isFullBlock(final boolean fullBlock) {
		this.fullBlock = fullBlock;
		
		return this;
	}
	
	public BlockFactory isFullBlock() {
		this.fullBlock = true;
		
		return this;
	}
	
	public BlockFactory isNotFullBlock() {
		this.fullBlock = false;
		
		return this;
	}
	
	public BlockFactory isOpaque(final boolean opaque) {
		this.opaque = opaque;
		
		return this;
	}
	
	public BlockFactory isOpaque() {
		this.opaque = true;
		
		return this;
	}
	
	public BlockFactory isNotOpaque() {
		this.opaque = false;
		
		return this;
	}
	
	public BlockFactory addBlockStateProperty(final Property<?> property) {
		this.blockStateProperties.add(property);
		
		return this;
	}
	
	//	TODO BOUNDING BOX
	
	public abstract BlockFactory inheritFromBlock(final Block block);
}
