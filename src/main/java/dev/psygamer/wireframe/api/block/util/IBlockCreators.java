package dev.psygamer.wireframe.api.block.util;

import dev.psygamer.wireframe.api.block.state.BlockPropertyContainer;

import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.inventory.container.INamedContainerProvider;

import java.util.List;

public interface IBlockCreators {
	
	default TileEntity createBlockEntity(
			final BlockPropertyContainer blockState, final IBlockReader world
	) { return null; }
	
	default ItemStack createPickBlockStack(
			final BlockPropertyContainer blockState, final BlockPos pos, final IBlockReader world
	) { return null; }
	
	default List<ItemStack> createBlockDrops(
			final BlockPropertyContainer blockState, final LootContext.Builder lootContextBuilder
	) { return null; }
	
	default INamedContainerProvider createMenuProvider(
			final BlockPropertyContainer blockState, final BlockPos pos, final World world
	) { return null; }
}
