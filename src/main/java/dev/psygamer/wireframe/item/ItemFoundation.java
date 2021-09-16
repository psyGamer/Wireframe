package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.internal.item.InternalItemFoundation;
import dev.psygamer.wireframe.item.util.ClickResult;
import dev.psygamer.wireframe.item.util.ClickResultContainer;
import dev.psygamer.wireframe.item.util.Hand;
import dev.psygamer.wireframe.registry.ItemRegistry;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.util.math.BlockHitResult;
import dev.psygamer.wireframe.world.World;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

/*

net.minecraft.item.Item;
net.minecraft.item.ItemGroup;
net.minecraft.item.ItemUseContext;
net.minecraft.block.SoundType;
net.minecraft.block.material.Material;

net.minecraft.entity.Entity;
net.minecraft.entity.player.PlayerEntity;
net.minecraft.entity.projectile.ProjectileEntity;
net.minecraft.entity.LivingEntity;
net.minecraft.entity.player.PlayerEntity;
net.minecraft.inventory.container.INamedContainerProvider;
net.minecraft.loot.LootContext;
net.minecraft.tileentity.TileEntity;
net.minecraft.world.IBlockReader;

 */

public class ItemFoundation {
	
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
		return ClickResultContainer.pass(ItemStack.get(player.getItemInHand(hand.getInternal())));
	}
	
	public ClickResult onItemUsedOnBlock(
			final ItemStack usedItemStack,
			final World world, final PlayerEntity player, final Hand hand, final BlockHitResult rayTraceResult
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
			final BlockState blockState,
			final BlockPosition blockPosition, final World world, final LivingEntity entity
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
