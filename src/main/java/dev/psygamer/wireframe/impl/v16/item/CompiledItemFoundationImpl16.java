package dev.psygamer.wireframe.impl.v16.item;

import dev.psygamer.wireframe.api.block.BlockFoundation;
import dev.psygamer.wireframe.api.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.api.block.state.property.BlockProperty;
import dev.psygamer.wireframe.api.item.ItemAttributes;
import dev.psygamer.wireframe.api.item.ItemFoundation;
import dev.psygamer.wireframe.api.item.util.IItemEvents;
import dev.psygamer.wireframe.impl.v16.block.CompiledBlockFoundationImpl16;
import dev.psygamer.wireframe.impl.v16.block.CompiledBlockPropertyImpl16;
import net.minecraft.block.Block;
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
import java.util.concurrent.atomic.AtomicReference;

public class CompiledItemFoundationImpl16 extends Item {
	
	private final List<IItemEvents> itemEvents = new ArrayList<>();
	private final ItemFoundation item;
	
	public CompiledItemFoundationImpl16(final ItemFoundation item, final ItemAttributes attributes) {
		super(((ItemAttributesImpl16) attributes).createProperties());
		
		this.item = item;
		this.itemEvents.add(item);
	}
	
	/* Item Events */
	
	@Override
	public ActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand) {
		this.itemEvents.forEach(event -> event.onItemUsed(player.getItemInHand(hand), world, player, hand));
		
		return super.use(world, player, hand);
	}
	
	@Override
	public ActionResultType useOn(final ItemUseContext context) {
		this.itemEvents.forEach(event -> event.onItemUsedOnBlock(context));
		
		return super.useOn(context);
	}
	
	@Override
	public ActionResultType interactLivingEntity(final ItemStack item, final PlayerEntity player, final LivingEntity entity, final Hand hand) {
		this.itemEvents.forEach(event -> event.onItemUsedOnEntity(
				player.getItemInHand(hand), player.level, player, entity, hand
		));
		
		return super.interactLivingEntity(item, player, entity, hand);
	}
	
	@Override
	public boolean mineBlock(final ItemStack itemStack, final World world, final BlockState state, final BlockPos pos, final LivingEntity entity) {
		final BlockPropertyContainer propertyContainer = CompiledBlockFoundationImpl16.convertBlockState(
				CompiledBlockFoundationImpl16.convertBlock(state.getBlock()), state
		);
		
		this.itemEvents.forEach(event -> event.onBlockMined(
				itemStack, propertyContainer, pos, world, entity
		));
		
		return super.mineBlock(itemStack, world, state, pos, entity);
	}
	
	@Override
	public void onCraftedBy(final ItemStack itemStack, final World world, final PlayerEntity player) {
		this.itemEvents.forEach(event -> event.onItemCrafted(
				itemStack, world, player
		));
		
		super.onCraftedBy(itemStack, world, player);
	}
}
