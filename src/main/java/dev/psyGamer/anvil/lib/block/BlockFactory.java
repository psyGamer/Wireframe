package dev.psyGamer.anvil.lib.block;

import dev.psyGamer.anvil.core.version.MinecraftVersion;
import dev.psyGamer.anvil.core.version.SupportedOnlyIn;
import dev.psyGamer.anvil.core.version.ImplementationHandler;
import dev.psyGamer.anvil.lib.util.IFactory;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;


/**
 * A factory class for easily creating {@link Block Blocks} and registering automatically.
 * <p>
 *
 * @author psyGamer
 * @version 1.0
 * @see Block
 * @since 1.0
 */
@SupportedOnlyIn(MinecraftVersion.v16)
public abstract class BlockFactory implements IFactory<Block> {
	
	/**
	 * @param blockName   Used for registering and localize it.
	 *                    <p> -> Registered under: modid:blockname</p>
	 *                    <p> -> Localized under: tile.modid.blockname</p>
	 * @param material    For sound, hardness, etc.
	 * @param creativeTab Creative Tab for the Block
	 * @return A new instance of a {@link BlockFactory}
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public static BlockFactory create(final String blockName, final Material material, final ItemGroup creativeTab) {
		return (BlockFactory) ImplementationHandler.executeImplementation(blockName, material, creativeTab);
	}
	
	/*
	
	public abstract BlockFactory setBoundingBox(int bottomX, int bottomY, int bottomZ, int topX, int topY, int topZ);
	
	public abstract BlockFactory setDirectional(EnumFacing.Plane... planes);
	
	public abstract BlockFactory setDirectional(EnumFacing... directions);
	
	public abstract BlockFactory setCausesSuffocation(boolean causeSuffocation);
	
	public abstract BlockFactory setOpaqueCube(boolean opaqueCube);
	
	public abstract BlockFactory setRenderType(EnumBlockRenderType renderType);
	
	public abstract BlockFactory onRandomTick(QuadConsumer<World, BlockPos, IBlockState, Random> randomTickFunction);
	
	public abstract BlockFactory onUpdateTick(QuadConsumer<World, BlockPos, IBlockState, Random> updateTickFunction);
	
	public abstract BlockFactory addBlockState(PropertyHelper<?> blockState);
	
	public abstract BlockFactory addTileEntity(Function<TileEntity, Void> tileEntityFactoryFunction);
	
	*/
}
