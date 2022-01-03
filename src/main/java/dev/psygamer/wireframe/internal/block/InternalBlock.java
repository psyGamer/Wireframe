package dev.psygamer.wireframe.internal.block;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.BlockAttributes;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.entity.Player;
import dev.psygamer.wireframe.internal.item.InternalItem;
import dev.psygamer.wireframe.util.math.BlockHitResult;
import dev.psygamer.wireframe.world.BlockReader;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class InternalBlock extends net.minecraft.block.Block {
	
	private static Field stateDefinitionField;
	
	static {
		try {
			stateDefinitionField = net.minecraft.block.Block.class.getDeclaredField("stateDefinition");
			stateDefinitionField.setAccessible(true);
		} catch (final NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	private final Block block;
	
	public InternalBlock(final Block block, final BlockAttributes attributes) {
		super(attributes.getMcNative$wireframe()
						.createProperties()
		);
		
		this.block = block;
		
		this.setRegistryName(
				block.getIdentifier()
					 .getNamespace(),
				block.getIdentifier()
					 .getPath()
		);
	}
	
	public void setDefaultBlockState(final BlockState blockState) {
		registerDefaultState(blockState.getInternal());
	}
	
	public void registerBlockProperties(final BlockProperty<? extends Comparable<?>>[] blockProperties) {
		final StateContainer.Builder<net.minecraft.block.Block, net.minecraft.block.BlockState>
				builder = new StateContainer.Builder<>(this);
		
		Arrays.stream(blockProperties)
			  .map(BlockProperty::getInternal)
			  .forEach(builder::add);
		
		try {
			stateDefinitionField
					.set(this, builder.create(net.minecraft.block.Block::defaultBlockState,
											  net.minecraft.block.BlockState::new
						 )
					);
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
		
		net.minecraft.block.BlockState defaultState = this.stateDefinition.any();
		
		for (final BlockProperty<?> blockProperty : blockProperties) {
			defaultState = setValue(blockProperty, defaultState);
		}
		
		registerDefaultState(defaultState);
	}
	
	private <T extends Comparable<T>> net.minecraft.block.BlockState setValue(
			final BlockProperty<T> blockProperty, final net.minecraft.block.BlockState blockState
	) {
		return blockState.setValue(blockProperty.getInternal(), blockProperty.getDefaultValue());
	}
	
	/* Block Events */
	
	@Override
	public void onPlace(final net.minecraft.block.BlockState newBlockState, final World world, final BlockPos pos,
						final net.minecraft.block.BlockState oldBlockState, final boolean isMoving
	) {
		this.block.onBlockPlaced(
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(pos),
				
				BlockState.get(oldBlockState),
				BlockState.get(newBlockState)
		);
	}
	
	@Override
	public void onRemove(final net.minecraft.block.BlockState newBlockState, final World world, final BlockPos pos,
						 final net.minecraft.block.BlockState oldBlockState, final boolean isMoving
	) {
		this.block.onBlockRemoved(
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(pos),
				
				BlockState.get(oldBlockState),
				BlockState.get(newBlockState)
		);
		
		super.onRemove(newBlockState, world, pos, oldBlockState, isMoving);
	}
	
	@Override
	public ActionResultType use(final net.minecraft.block.BlockState state, final World world, final BlockPos pos,
								final PlayerEntity player, final Hand hand, final BlockRayTraceResult p_225533_6_
	) {
		return this.block.onUsedByPlayer(
						   dev.psygamer.wireframe.world.World.get(world),
						   dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(pos),
				
						   BlockState.get(state), Player.get(player)
				   )
						 .getInternal();
	}
	
	@Override
	public List<ItemStack> getDrops(final net.minecraft.block.BlockState state, final LootContext.Builder builder) {
		return this.block.createBlockDrops(
						   BlockState.get(state)
				   )
						 .stream()
						 .map(dev.psygamer.wireframe.item.ItemStack::getInternal)
						 .collect(Collectors.toList());
	}
	
	@Override
	public void randomTick(final net.minecraft.block.BlockState state,
						   final ServerWorld world, final BlockPos pos, final Random random
	) {
		this.block.onRandomTick(
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(pos),
				
				BlockState.get(state), random
		);
	}
	
	@Override
	public void tick(final net.minecraft.block.BlockState state,
					 final ServerWorld world, final BlockPos pos, final Random random
	) {
		this.block.onTick(
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(pos),
				
				BlockState.get(state)
		);
	}
	
	@Override
	public void attack(final net.minecraft.block.BlockState state,
					   final World world, final BlockPos pos, final PlayerEntity player
	) {
		this.block.onAttackedByPlayer(
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(pos),
				
				BlockState.get(state), Player.get(player)
		);
	}
	
	@Override
	public void onProjectileHit(final World world, final net.minecraft.block.BlockState state,
								final BlockRayTraceResult hitResult, final ProjectileEntity projectile
	) {
		this.block.onHitByProjectile(
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(hitResult.getBlockPos()),
				
				BlockState.get(state), dev.psygamer.wireframe.entity.ProjectileEntity.get(projectile)
		);
	}
	
	@Override
	public void stepOn(final World world, final BlockPos pos, final Entity entity) {
		this.block.onEntityStepOnBlock(
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(pos),
				
				BlockState.get(world.getBlockState(pos)), dev.psygamer.wireframe.entity.Entity.get(entity)
		);
	}
	
	@Nullable
	@Override
	public net.minecraft.block.BlockState getStateForPlacement(final BlockItemUseContext context) {
		try {
			return this.block.getPlacementState(
							   dev.psygamer.wireframe.world.World.get(context.getLevel()),
							   Player.get(context.getPlayer()),
							   dev.psygamer.wireframe.item.util.Hand.get(context.getHand()),
							   dev.psygamer.wireframe.item.ItemStack.get(context.getItemInHand()),
							   BlockHitResult.get((BlockRayTraceResult) InternalItem.hitResultField.get(context))
					   )
							 .getInternal();
			
		} catch (final IllegalAccessException e) {
			return this.block.getMcNative$wireframe()
							 .defaultBlockState();
		}
	}
	
	@Override
	public void setPlacedBy(final World world, final BlockPos pos, final net.minecraft.block.BlockState blockState,
							@Nullable final LivingEntity placer, final ItemStack itemStack
	) {
		if (placer instanceof PlayerEntity) {
			this.block.onBlockPlacedByPlayer(
					dev.psygamer.wireframe.world.World.get(world),
					dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(pos),
					
					BlockState.get(world.getBlockState(pos)),
					BlockState.get(blockState), Player.get((PlayerEntity) placer)
			);
		}
	}
	
	@Override
	public void fallOn(final World world, final BlockPos pos, final Entity entity, final float distance) {
		super.fallOn(world, pos, entity, distance);
		
		this.block.onEntityFallOnBlock(
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(pos),
				
				BlockState.get(world.getBlockState(pos)), dev.psygamer.wireframe.entity.Entity.get(entity), distance
		);
	}
	
	@Override
	public boolean hasTileEntity(final net.minecraft.block.BlockState state) {
		return this.block.hasBlockEntity();
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(final net.minecraft.block.BlockState state, final IBlockReader blockReader) {
		return this.block.createBlockEntity().getInternal();
	}
	
	@Override
	public boolean removedByPlayer(final net.minecraft.block.BlockState state, final World world, final BlockPos pos,
								   final PlayerEntity player, final boolean willHarvest, final FluidState fluid
	) {
		this.block.onBlockRemovedByPlayer(
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(pos),
				
				BlockState.get(world.getBlockState(pos)),
				BlockState.get(state), Player.get(player)
		);
		
		return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
	}
	
	@Override
	public ItemStack getPickBlock(final net.minecraft.block.BlockState state, final RayTraceResult target,
								  final IBlockReader blockReader, final BlockPos pos, final PlayerEntity player
	) {
		final dev.psygamer.wireframe.item.ItemStack pickBlock = this.block.createPickBlockStack(
				BlockReader.get(blockReader),
				dev.psygamer.wireframe.util.BlockPositionKt.getWfWrapped(pos),
				BlockState.get(state)
		);
		
		return pickBlock == null ? ItemStack.EMPTY : pickBlock.getInternal();
	}
}
