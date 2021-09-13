package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.internal.item.InternalItemFoundation;
import dev.psygamer.wireframe.item.util.IItemEvents;
import dev.psygamer.wireframe.registry.ItemRegistry;
import dev.psygamer.wireframe.util.Identifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFoundation implements IItemEvents {
	
	protected final InternalItemFoundation internal;
	
	protected final Identifier identifier;
	
	protected final ItemAttributes attributes;
	
	public ItemFoundation(final Identifier identifier, final ItemAttributes attributes) {
		this.identifier = identifier;
		this.attributes = attributes;
		
		this.internal = new InternalItemFoundation(this, attributes);
		
		ItemRegistry.register(this);
	}
	
	public ClickResultContainer<ItemStack> onItemUsed(
			final ItemStack usedItemStack,
			final World world, final PlayerEntity player, final Hand hand
	) {
		return ClickResultContainer.pass(player.getItemInHand(hand));
	}
	
	public ClickResult onItemUsedOnBlock(
			final ItemUseContext context
	) {
		return ClickResult.PASS;
	}
	
	public ClickResult onItemUsedOnEntity(
			final ItemStack usedItemStack,
			final World world, final PlayerEntity player, final LivingEntity entity, final Hand hand
	) {
		return ClickResult.PASS;
	}
	
	public boolean onEntityAttacked(
			final ItemStack usedItemStack,
			final World world, final LivingEntity attacker, final LivingEntity entity
	) {
		return false;
	}
	
	public boolean onBlockMined(
			final ItemStack usedItemStack,
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world, final LivingEntity entity
	) {
		return false;
	}
	
	public boolean onItemCrafted(
			final ItemStack usedItemStack, final World world, final PlayerEntity player
	) {
		return false;
	}
	
	public Identifier getIdentifier() {
		return this.identifier;
	}
	
	public ItemAttributes getAttributes() {
		return this.attributes;
	}
	
	public InternalItemFoundation getInternal() {
		return this.internal;
	}
}
