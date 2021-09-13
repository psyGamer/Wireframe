package dev.psygamer.wireframe.block;

import dev.psygamer.wireframe.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.block.state.BlockPropertySet;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.block.util.BlockUtilityMethods;
import dev.psygamer.wireframe.internal.block.InternalBlockFoundation;
import dev.psygamer.wireframe.item.ClickResult;
import dev.psygamer.wireframe.registry.BlockRegistry;
import dev.psygamer.wireframe.util.IFreezable;
import dev.psygamer.wireframe.util.Identifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockFoundation extends BlockUtilityMethods implements IFreezable {
	
	protected final InternalBlockFoundation internal;
	
	protected final Identifier identifier;
	
	protected final BlockAttributes attributes;
	protected final BlockPropertySet propertySet;
	protected final BlockPropertyContainer defaultPropertyContainer;
	
	public BlockFoundation(final Identifier identifier, final BlockAttributes attributes) {
		this.identifier = identifier;
		this.attributes = attributes;
		
		this.propertySet = new BlockPropertySet();
		this.defaultPropertyContainer = new BlockPropertyContainer(this.propertySet);
		
		this.internal = new InternalBlockFoundation(this, attributes);
		
		BlockRegistry.register(this);
	}
	
	public Identifier getIdentifier() {
		return this.identifier;
	}
	
	public BlockAttributes getAttributes() {
		return this.attributes;
	}
	
	public BlockPropertyContainer getDefaultBlockPropertyContainer() {
		return this.defaultPropertyContainer.copy();
	}
	
	protected <T> void registerBlockStateProperty(final BlockProperty<T> property) {
		if (this.defaultPropertyContainer.containsProperty(property))
			return;
		
		this.defaultPropertyContainer.setProperty(property, property.getDefaultValue());
	}
	
	public InternalBlockFoundation getInternal() {
		return this.internal;
	}
	
	public boolean onBlockPlaced(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world
	) {
		return false;
	}
	
	public boolean onBlockPlacedByPlayer(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) {
		return false;
	}
	
	public boolean onBlockRemoved(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world
	) {
		return false;
	}
	
	public boolean onBlockRemovedByPlayer(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) {
		return false;
	}
	
	public boolean onTick(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world
	) {
		return false;
	}
	
	public boolean onRandomTick(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world, final Random random
	) {
		return false;
	}
	
	public boolean onEntityStepOnBlock(
			final BlockPos blockPosition, final World world, final Entity entity
	) {
		return false;
	}
	
	public boolean onEntityFallOnBlock(
			final BlockPos blockPosition, final World world, final Entity entity, final float fallDistance
	) {
		return false;
	}
	
	public ClickResult onUsedByPlayer(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) {
		return ClickResult.PASS;
	}
	
	public boolean onAttackedByPlayer(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) {
		return false;
	}
	
	public boolean onHitByProjectile(
			final BlockPropertyContainer blockState,
			final World world, final ProjectileEntity projectile
	) {
		return false;
	}
	
	public TileEntity createBlockEntity(
			final BlockPropertyContainer blockState, final IBlockReader world
	) {
		return null;
	}
	
	public ItemStack createPickBlockStack(
			final BlockPropertyContainer blockState, final BlockPos pos, final IBlockReader world
	) {
		return null;
	}
	
	public List<ItemStack> createBlockDrops(
			final BlockPropertyContainer blockState, final LootContext.Builder lootContextBuilder
	) {
		return null;
	}
	
	public INamedContainerProvider createMenuProvider(
			final BlockPropertyContainer blockState, final BlockPos pos, final World world
	) {
		return null;
	}
	
	@Override
	public void freeze() {
		this.propertySet.freeze();
		this.defaultPropertyContainer.freeze();
	}
	
	@Override
	public boolean isFrozen() {
		return this.propertySet.isFrozen() || this.defaultPropertyContainer.isFrozen();
	}
}
