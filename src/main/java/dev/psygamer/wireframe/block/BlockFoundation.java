package dev.psygamer.wireframe.block;

import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.entity.Entity;
import dev.psygamer.wireframe.entity.Player;
import dev.psygamer.wireframe.entity.ProjectileEntity;
import dev.psygamer.wireframe.internal.block.InternalBlockFoundation;
import dev.psygamer.wireframe.item.ItemStack;
import dev.psygamer.wireframe.item.util.ClickResult;
import dev.psygamer.wireframe.registry.BlockRegistry;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.world.BlockReader;
import dev.psygamer.wireframe.world.World;

import net.minecraft.loot.LootContext;
import net.minecraft.tileentity.TileEntity;

import java.util.List;
import java.util.Random;

public class BlockFoundation {
	
	protected final InternalBlockFoundation internal;
	
	protected final Identifier identifier;
	
	protected final BlockAttributes attributes;
	protected final BlockState defaultBlockState;
	
	public BlockFoundation(final Identifier identifier, final BlockAttributes attributes) {
		this.identifier = identifier;
		this.attributes = attributes;
		
		this.defaultBlockState = new BlockState(this);
		
		this.internal = new InternalBlockFoundation(this, attributes);
		
		BlockRegistry.register(this);
	}
	
	public Identifier getIdentifier() {
		return this.identifier;
	}
	
	public BlockAttributes getAttributes() {
		return this.attributes;
	}
	
	public BlockState getDefaultBlockState() {
		return this.defaultBlockState.copy();
	}
	
	protected <T extends Comparable<T>> void registerBlockProperty(final BlockProperty<T> property) {
		if (this.defaultBlockState.containsProperty(property))
			return;
		
		this.defaultBlockState.setProperty(property, property.getDefaultValue());
	}
	
	public InternalBlockFoundation getInternal() {
		return this.internal;
	}
	
	public boolean onBlockPlaced(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world
	) {
		return false;
	}
	
	public boolean onBlockPlacedByPlayer(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
		return false;
	}
	
	public boolean onBlockRemoved(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world
	) {
		return false;
	}
	
	public boolean onBlockRemovedByPlayer(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
		return false;
	}
	
	public boolean onTick(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world
	) {
		return false;
	}
	
	public boolean onRandomTick(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world, final Random random
	) {
		return false;
	}
	
	public boolean onEntityStepOnBlock(
			final BlockPosition blockPosition, final World world, final Entity entity
	) {
		return false;
	}
	
	public boolean onEntityFallOnBlock(
			final BlockPosition blockPosition, final World world, final Entity entity, final float fallDistance
	) {
		return false;
	}
	
	public ClickResult onUsedByPlayer(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
		return ClickResult.PASS;
	}
	
	public boolean onAttackedByPlayer(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
		return false;
	}
	
	public boolean onHitByProjectile(
			final BlockState blockState,
			final World world, final ProjectileEntity projectile
	) {
		return false;
	}
	
	public TileEntity createBlockEntity(
			final BlockState blockState, final BlockReader blockReader
	) {
		return null;
	}
	
	public ItemStack createPickBlockStack(
			final BlockState blockState, final BlockPosition pos, final BlockReader blockReader
	) {
		return null;
	}
	
	public List<ItemStack> createBlockDrops(
			final BlockState blockState, final LootContext.Builder lootContextBuilder
	) {
		return null;
	}
}
