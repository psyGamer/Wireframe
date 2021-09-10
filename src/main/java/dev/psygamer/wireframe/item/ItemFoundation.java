package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.impl.v16.block.CompiledBlockFoundationImpl16;
import dev.psygamer.wireframe.impl.v16.item.ItemAttributesImpl16;
import dev.psygamer.wireframe.item.util.IItemEvents;
import dev.psygamer.wireframe.core.dependant.Namespace;
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

public class ItemFoundation implements IItemEvents {
	
	protected final Internal internal;
	
	protected final String registryName;
	protected final Namespace namespace;
	protected final ItemAttributes attributes;
	
	public ItemFoundation(final String registryName, final ItemAttributes attributes) {
		this.registryName = registryName;
		this.namespace = Namespace.getCurrent();
		this.attributes = attributes;
		
		this.internal = new Internal(attributes);
	}
	
	public String getRegistryName() {
		return this.registryName;
	}
	
	public Namespace getNamespace() {
		return this.namespace;
	}
	
	public ItemAttributes getAttributes() {
		return this.attributes;
	}
	
	protected class Internal extends Item {
		private final List<IItemEvents> itemEvents = new ArrayList<>();
		
		public Internal(final ItemAttributes attributes) {
			super(((ItemAttributesImpl16) attributes).createProperties());
		}
		
		/* Item Events */
		
		@Override
		public ActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand) {
			this.itemEvents.forEach(event -> event.onItemUsed(player.getItemInHand(hand), world, player, hand));
			
			return onItemUsed(player.getItemInHand(hand), world, player, hand).toInternal();
		}
		
		@Override
		public ActionResultType useOn(final ItemUseContext context) {
			this.itemEvents.forEach(event -> event.onItemUsedOnBlock(context));
			
			return onItemUsedOnBlock(context).getInternal();
		}
		
		@Override
		public ActionResultType interactLivingEntity(final ItemStack item, final PlayerEntity player, final LivingEntity entity, final Hand hand) {
			this.itemEvents.forEach(event -> event.onItemUsedOnEntity(
					player.getItemInHand(hand), player.level, player, entity, hand
			));
			
			return onItemUsedOnEntity(
					player.getItemInHand(hand), player.level, player, entity, hand
			).getInternal();
		}
		
		@Override
		public boolean mineBlock(final ItemStack itemStack, final World world, final BlockState state, final BlockPos pos, final LivingEntity entity) {
			final BlockPropertyContainer propertyContainer = CompiledBlockFoundationImpl16.convertBlockState(
					CompiledBlockFoundationImpl16.convertBlock(state.getBlock()), state
			);
			
			this.itemEvents.forEach(event -> event.onBlockMined(
					itemStack, propertyContainer, pos, world, entity
			));
			
			return onBlockMined(
					itemStack, propertyContainer, pos, world, entity
			);
		}
		
		@Override
		public void onCraftedBy(final ItemStack itemStack, final World world, final PlayerEntity player) {
			this.itemEvents.forEach(event -> event.onItemCrafted(
					itemStack, world, player
			));
			
			onItemCrafted(
					itemStack, world, player
			);
		}
	}
}
