package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.api.util.ICloneable;
import dev.psygamer.wireframe.api.block.properties.HarvestLevel;

import dev.psygamer.wireframe.core.impl.Instancer;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.Property;
import net.minecraftforge.common.ToolType;


/**
 * A factory class for easily creating {@link Block Blocks} and registering automatically.
 * <p>
 *
 * @author psyGamer
 * @version 1.0 | Minecraft 1.16 +
 * @see Block
 * @since 1.0 | Minecraft 1.16
 */
public abstract class BlockProperties implements ICloneable<BlockProperties> {
	
	private static final BlockProperties INSTANCE = Instancer.createInstance();
	
	/**
	 * @param blockName Used to register and localize it.
	 *                  <p> - Registered under: modid:blockname</p>
	 *                  <p> - Localized under: block.modid.blockname</p>
	 * @return A new instance of a {@link BlockProperties}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static BlockProperties create(final String blockName) {
		return INSTANCE.createInstance(blockName);
	}
	
	/**
	 * @param blockName Used to register and localize it.
	 *                  <p> - Registered under: modid:blockname</p>
	 *                  <p> - Localized under: block.modid.blockname</p>
	 * @param material  For sound, hardness, etc.
	 * @return A new instance of a {@link BlockProperties}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static BlockProperties create(final String blockName, final Material material) {
		return INSTANCE.createInstance(blockName, material);
	}
	
	/**
	 * @param blockName Used to register and localize it.
	 *                  <p> - Registered under: modid:blockname</p>
	 *                  <p> - Localized under: block.modid.blockname</p>
	 * @param material  For sound, hardness, etc.
	 * @param group     Creative Tab for the Block
	 * @return A new instance of a {@link BlockProperties}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static BlockProperties create(final String blockName, final Material material, final ItemGroup group) {
		return INSTANCE.createInstance(blockName, material, group);
	}
	
	protected abstract BlockProperties createInstance(final String blockName);
	
	protected abstract BlockProperties createInstance(final String blockName, final Material material);
	
	protected abstract BlockProperties createInstance(final String blockName, final Material material, final ItemGroup group);
	
	public abstract BlockProperties multiplyHardness(final float factor);
	
	public abstract BlockProperties multiplyBlastResistance(final float factor);
	
	public abstract BlockProperties multiplyStrength(final float factor);
	
	public abstract String getRegistryName();
	
	public abstract Material getMaterial();
	
	public abstract BlockProperties setMaterial(final Material material);
	
	public abstract ItemGroup getGroup();
	
	public abstract BlockProperties setGroup(final ItemGroup group);
	
	public abstract float getHardness();
	
	public abstract BlockProperties setHardness(final float hardness);
	
	public abstract float getBlastResistance();
	
	public abstract BlockProperties setBlastResistance(final float blastResistance);
	
	public abstract BlockProperties setStrength(final float strength);
	
	public abstract SoundType getSound();
	
	public abstract BlockProperties setSound(final SoundType sound);
	
	public abstract ToolType getRequiredTool();
	
	public abstract BlockProperties setRequiredTool(final ToolType tool);
	
	public abstract HarvestLevel getHarvestLevel();
	
	public abstract BlockProperties setHarvestLevel(final int level);
	
	public abstract BlockProperties setHarvestLevel(final HarvestLevel harvestLevel);
	
	public abstract BlockProperties increaseHarvestLevel();
	
	public abstract BlockProperties decreaseHarvestLevel();
	
	public abstract boolean isBreakableByHand();
	
	public abstract BlockProperties setBreakableByHand(final boolean breakableByHand);
	
	public abstract boolean isFullBlock();
	
	public abstract BlockProperties setFullBlock(final boolean fullBlock);
	
	public abstract boolean isOpaque();
	
	public abstract BlockProperties setOpaque(final boolean opaque);
	
	public abstract BlockProperties addBlockStateProperty(final Property<?> property);
	
	//	TODO BOUNDING BOX
	
	public abstract BlockProperties inheritFromBlock(final Block block);
	
	public abstract Block createBlock();
}
