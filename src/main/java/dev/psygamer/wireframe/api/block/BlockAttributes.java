package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.api.block.attributes.HarvestLevel;
import dev.psygamer.wireframe.util.ICloneable;

import dev.psygamer.wireframe.core.impl.Instancer;

import net.minecraft.item.ItemGroup;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import net.minecraftforge.common.ToolType;

/**
 * <p>A factory class for easily creating attributes related to blocks.</p>
 *
 * @author psyGamer
 * @version 1.0 | Minecraft 1.16 +
 * @see BlockFoundation
 * @since 1.0 | Minecraft 1.16
 */
public abstract class BlockAttributes implements ICloneable<BlockAttributes> {
	
	private static final BlockAttributes INSTANCE = Instancer.createInstance();
	
	/**
	 * @return A new instance of a {@link BlockAttributes}
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public static BlockAttributes create() {
		return INSTANCE.createInstance();
	}
	
	/**
	 * @param material For sound, hardness, etc.
	 * @return A new instance of a {@link BlockAttributes}
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public static BlockAttributes create(final Material material) {
		return INSTANCE.createInstance(material);
	}
	
	/**
	 * @param material For sound, hardness, etc.
	 * @param group    Creative Tab for the Block
	 * @return A new instance of a {@link BlockAttributes}
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public static BlockAttributes create(final Material material, final ItemGroup group) {
		return INSTANCE.createInstance(material, group);
	}
	
	public abstract BlockAttributes setMaterial(final Material material);
	
	public abstract BlockAttributes setGroup(final ItemGroup group);
	
	public abstract BlockAttributes setHardness(final float hardness);
	
	public abstract BlockAttributes setBlastResistance(final float blastResistance);
	
	public abstract BlockAttributes setSound(final SoundType sound);
	
	public abstract BlockAttributes setRequiredTool(final ToolType tool);
	
	public abstract BlockAttributes setHarvestLevel(final int level);
	
	public abstract BlockAttributes setHarvestLevel(final HarvestLevel harvestLevel);
	
	public abstract BlockAttributes setFullBlock(final boolean fullBlock);
	
	//	TODO BOUNDING BOX
	
	protected abstract BlockAttributes createInstance();
	
	protected abstract BlockAttributes createInstance(final Material material);
	
	protected abstract BlockAttributes createInstance(final Material material, final ItemGroup group);
	
}
