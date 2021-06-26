package dev.psygamer.construct.lib.block;

import dev.psygamer.construct.core.version.ImplementationHandler;
import dev.psygamer.construct.lib.block.properties.HarvestLevel;
import dev.psygamer.construct.lib.util.ICloneable;
import dev.psygamer.construct.core.version.MinecraftVersion;
import dev.psygamer.construct.core.version.SupportedSince;
import dev.psygamer.construct.lib.util.IFactory;
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
 * @version 1.0 | 1.16 +
 * @see Block
 * @since 1.0
 */
@SupportedSince(MinecraftVersion.v16)
public interface BlockFactory extends IFactory<Block>, ICloneable<BlockFactory> {
	
	/**
	 * @param blockName Used to register and localize it.
	 *                  <p> - Registered under: modid:blockname</p>
	 *                  <p> - Localized under: block.modid.blockname</p>
	 * @return A new instance of a {@link BlockFactory}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	static BlockFactory create(final String blockName) {
		return ImplementationHandler.executeImplementation(blockName);
	}
	
	/**
	 * @param blockName Used to register and localize it.
	 *                  <p> - Registered under: modid:blockname</p>
	 *                  <p> - Localized under: block.modid.blockname</p>
	 * @param material  For sound, hardness, etc.
	 * @return A new instance of a {@link BlockFactory}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	static BlockFactory create(final String blockName, final Material material) {
		return ImplementationHandler.executeImplementation(blockName, material);
	}
	
	/**
	 * @param blockName Used to register and localize it.
	 *                  <p> - Registered under: modid:blockname</p>
	 *                  <p> - Localized under: block.modid.blockname</p>
	 * @param material  For sound, hardness, etc.
	 * @param group     Creative Tab for the Block
	 * @return A new instance of a {@link BlockFactory}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	static BlockFactory create(final String blockName, final Material material, final ItemGroup group) {
		return ImplementationHandler.executeImplementation(blockName, material, group);
	}
	
	BlockFactory multiplyHardness(final float factor);
	
	BlockFactory multiplyBlastResistance(final float factor);
	
	BlockFactory multiplyStrength(final float factor);
	
	String getRegistryName();
	
	Material getMaterial();
	
	BlockFactory setMaterial(final Material material);
	
	ItemGroup getGroup();
	
	BlockFactory setGroup(final ItemGroup group);
	
	float getHardness();
	
	BlockFactory setHardness(final float hardness);
	
	float getBlastResistance();
	
	BlockFactory setBlastResistance(final float blastResistance);
	
	BlockFactory setStrength(final float strength);
	
	SoundType getSound();
	
	BlockFactory setSound(final SoundType sound);
	
	ToolType getRequiredTool();
	
	BlockFactory setRequiredTool(final ToolType tool);
	
	HarvestLevel getHarvestLevel();
	
	BlockFactory setHarvestLevel(final int level);
	
	BlockFactory setHarvestLevel(final HarvestLevel harvestLevel);
	
	BlockFactory increaseHarvestLevel();
	
	BlockFactory decreaseHarvestLevel();
	
	boolean isBreakableByHand();
	
	BlockFactory setBreakableByHand(final boolean breakableByHand);
	
	boolean isFullBlock();
	
	BlockFactory setFullBlock(final boolean fullBlock);
	
	boolean isOpaque();
	
	BlockFactory setOpaque(final boolean opaque);
	
	BlockFactory addBlockStateProperty(final Property<?> property);
	
	//	TODO BOUNDING BOX
	
	BlockFactory inheritFromBlock(final Block block);
	
	Block createBlock();
}
