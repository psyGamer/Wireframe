package dev.psygamer.wireframe.internal.item;

import dev.psygamer.wireframe.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.internal.block.InternalBlockFoundation;
import dev.psygamer.wireframe.item.ItemAttributes;
import dev.psygamer.wireframe.item.ItemFoundation;
import dev.psygamer.wireframe.item.util.IItemEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class InternalItemFoundation extends Item {
	private final ItemFoundation item;
	
	public InternalItemFoundation(final ItemFoundation item, final ItemAttributes attributes) {
		super(attributes.getInternal().createProperties());
		
		this.item = item;
		
		setRegistryName(item.getIdentifier().getNamespace(), item.getIdentifier().getPath());
	}
	
	/* Item Events */
	
	@Override
	public ActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand) {
		return this.item.onItemUsed(player.getItemInHand(hand), world, player, hand).toInternal();
	}
	
	@Override
	public ActionResultType useOn(final ItemUseContext context) {
		return this.item.onItemUsedOnBlock(context).getInternal();
	}
	
	@Override
	public ActionResultType interactLivingEntity(final ItemStack item, final PlayerEntity player, final LivingEntity entity, final Hand hand) {
		return this.item.onItemUsedOnEntity(
				player.getItemInHand(hand), player.level, player, entity, hand
		).getInternal();
	}
	
	@Override
	public boolean mineBlock(final ItemStack itemStack, final World world, final BlockState state, final BlockPos pos, final LivingEntity entity) {
		final BlockPropertyContainer propertyContainer = InternalBlockFoundation.convertBlockState(
				InternalBlockFoundation.convertBlock(state.getBlock()), state
		);
		
		return this.item.onBlockMined(
				itemStack, propertyContainer, pos, world, entity
		);
	}
	
	@Override
	public void onCraftedBy(final ItemStack itemStack, final World world, final PlayerEntity player) {
		this.item.onItemCrafted(
				itemStack, world, player
		);
	}
}
