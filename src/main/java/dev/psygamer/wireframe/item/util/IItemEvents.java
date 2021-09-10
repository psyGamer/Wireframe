package dev.psygamer.wireframe.item.util;

import dev.psygamer.wireframe.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.item.ClickResult;
import dev.psygamer.wireframe.item.ClickResultContainer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;

public interface IItemEvents {
	
	default ClickResultContainer<ItemStack> onItemUsed(
			final ItemStack usedItemStack,
			final World world, final PlayerEntity player, final Hand hand
	) {
		return ClickResultContainer.pass(player.getItemInHand(hand));
	}
	
	default ClickResult onItemUsedOnBlock(
			final ItemUseContext context
	) { return ClickResult.PASS; }
	
	default ClickResult onItemUsedOnEntity(
			final ItemStack usedItemStack,
			final World world, final PlayerEntity player, final LivingEntity entity, final Hand hand
	) { return ClickResult.PASS; }
	
	default boolean onEntityAttacked(
			final ItemStack usedItemStack,
			final World world, final LivingEntity attacker, final LivingEntity entity
	) { return false; }
	
	default boolean onBlockMined(
			final ItemStack usedItemStack,
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world, final LivingEntity entity
	) { return false; }
	
	default boolean onItemCrafted(
			final ItemStack usedItemStack, final World world, final PlayerEntity player
	) { return false; }
}
