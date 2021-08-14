package dev.psygamer.wireframe.api.item.util;

import dev.psygamer.wireframe.api.block.state.BlockPropertyContainer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;

public interface IItemEvents {
	
	default boolean onItemUsed(
			final World world, final PlayerEntity player, final Hand hand
	) { return false; }
	
	default boolean onItemUsedOnBlock(
			final ItemUseContext context
	) { return false; }
	
	default boolean onItemUsedOnEntity(
			final World world, final PlayerEntity player, final LivingEntity entity, final Hand hand
	) { return false; }
	
	default boolean onEntityAttacked(
			final ItemStack usedItemStack,
			final LivingEntity usingEntity, final LivingEntity attackedEntity
	) { return false; }
	
	default boolean onBlockMined(
			final ItemStack usedItemStack,
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world
	) { return false; }
	
	default boolean onItemCrafted(
			final ItemStack usedItemStack, final World world, final PlayerEntity player
	) { return false; }
}
