package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.entity.LivingEntity;
import dev.psygamer.wireframe.entity.Player;
import dev.psygamer.wireframe.internal.item.InternalItem;
import dev.psygamer.wireframe.item.util.ClickResult;
import dev.psygamer.wireframe.item.util.ClickResultContainer;
import dev.psygamer.wireframe.item.util.Hand;
import dev.psygamer.wireframe.registry.ItemRegistry;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.util.math.BlockHitResult;
import dev.psygamer.wireframe.world.World;

public class Item {
	
	protected final InternalItem internal;
	
	protected final Identifier identifier;
	
	protected final ItemAttributes itemAttributes;
	
	public Item(final Identifier identifier, final ItemAttributes itemAttributes) {
		this.identifier = identifier;
		this.itemAttributes = itemAttributes;
		
		this.internal = new InternalItem(this, itemAttributes);
		
		ItemRegistry.register(this);
	}
	
	public ClickResultContainer<ItemStack> onItemUsed(
			final ItemStack usedItemStack,
			final World world, final Player player, final Hand hand
	) {
		return ClickResultContainer.pass(player.getHeldItem(hand));
	}
	
	public ClickResult onItemUsedOnBlock(
			final ItemStack usedItemStack,
			final World world, final Player player, final Hand hand, final BlockHitResult hitResult
	) {
		return ClickResult.PASS;
	}
	
	public ClickResult onItemUsedOnEntity(
			final ItemStack usedItemStack,
			final World world, final Player player, final LivingEntity entity, final Hand hand
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
			final BlockState blockState,
			final BlockPosition blockPosition, final World world, final LivingEntity entity
	) {
		return false;
	}
	
	public boolean onItemCrafted(
			final ItemStack usedItemStack, final World world, final Player player
	) {
		return false;
	}
	
	public Identifier getIdentifier() {
		return this.identifier;
	}
	
	public ItemAttributes getItemAttributes() {
		return this.itemAttributes;
	}
	
	public net.minecraft.item.Item getInternal() {
		return this.internal;
	}
}
