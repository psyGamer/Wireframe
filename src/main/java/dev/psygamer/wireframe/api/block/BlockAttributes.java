package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.util.ICloneable;

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
public abstract class BlockAttributes implements ICloneable<BlockAttributes> {
	
	private static final BlockAttributes INSTANCE = Instancer.createInstance();
	
	public enum HarvestLevel {
		HAND(-1),
		WOOD(0),
		STONE(1),
		IRON(2),
		DIAMOND(3),
		NETHERITE(4);
		
		private int level;
		
		HarvestLevel(final int level) {
			this.level = level;
		}
		
		public int getLevel() {
			return this.level;
		}
	}
	
	public static BlockAttributes create() {
		return INSTANCE.createInstance();
	}
	
	/**
	 * @param material For sound, hardness, etc.
	 * @return A new instance of a {@link BlockAttributes}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static BlockAttributes create(final Material material) {
		return INSTANCE.createInstance(material);
	}
	
	/**
	 * @param material For sound, hardness, etc.
	 * @param group    Creative Tab for the Block
	 * @return A new instance of a {@link BlockAttributes}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static BlockAttributes create(final Material material, final ItemGroup group) {
		return INSTANCE.createInstance(material, group);
	}
	
	protected abstract BlockAttributes createInstance();
	
	protected abstract BlockAttributes createInstance(final Material material);
	
	protected abstract BlockAttributes createInstance(final Material material, final ItemGroup group);
	
	public abstract BlockAttributes multiplyHardness(final float factor);
	
	public abstract BlockAttributes multiplyBlastResistance(final float factor);
	
	public abstract BlockAttributes multiplyStrength(final float factor);
	
	public abstract Material getMaterial();
	
	public abstract BlockAttributes setMaterial(final Material material);
	
	public abstract ItemGroup getGroup();
	
	public abstract BlockAttributes setGroup(final ItemGroup group);
	
	public abstract float getHardness();
	
	public abstract BlockAttributes setHardness(final float hardness);
	
	public abstract float getBlastResistance();
	
	public abstract BlockAttributes setBlastResistance(final float blastResistance);
	
	public abstract BlockAttributes setStrength(final float strength);
	
	public abstract SoundType getSound();
	
	public abstract BlockAttributes setSound(final SoundType sound);
	
	public abstract ToolType getRequiredTool();
	
	public abstract BlockAttributes setRequiredTool(final ToolType tool);
	
	public abstract HarvestLevel getHarvestLevel();
	
	public abstract BlockAttributes setHarvestLevel(final int level);
	
	public abstract BlockAttributes setHarvestLevel(final HarvestLevel harvestLevel);
	
	public abstract BlockAttributes increaseHarvestLevel();
	
	public abstract BlockAttributes decreaseHarvestLevel();
	
	public abstract boolean isBreakableByHand();
	
	public abstract BlockAttributes setBreakableByHand(final boolean breakableByHand);
	
	public abstract boolean isFullBlock();
	
	public abstract BlockAttributes setFullBlock(final boolean fullBlock);
	
	public abstract boolean isOpaque();
	
	public abstract BlockAttributes setOpaque(final boolean opaque);
	
	//	TODO BOUNDING BOX
	
	public abstract BlockAttributes addBlockStateProperty(final Property<?> property);
	
	public abstract BlockAttributes inheritFromBlock(final Block block);
	
	public abstract Block createBlock();
	
}
