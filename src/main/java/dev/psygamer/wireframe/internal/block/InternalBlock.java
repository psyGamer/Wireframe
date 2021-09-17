package dev.psygamer.wireframe.internal.block;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.BlockAttributes;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.entity.Player;

import dev.psygamer.wireframe.world.BlockReader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
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
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class InternalBlock extends net.minecraft.block.Block {
	
	private final Block block;
	
	public InternalBlock(final Block block, final BlockAttributes attributes) {
		super(attributes.getInternal().createProperties());
		
		this.block = block;
		this.setRegistryName(block.getIdentifier().getNamespace(), block.getIdentifier().getPath());
	}
	
	public static BlockState convertBlockState(final net.minecraft.block.BlockState blockState) {
		return convertBlockState(Block.get(blockState.getBlock()), blockState);
	}
	
	public static BlockState convertBlockState(final Block block, final net.minecraft.block.BlockState blockState) {
		final AtomicReference<BlockState> blockStateReference = new AtomicReference<>(
				block.getDefaultBlockState()
		);
		
		blockState.getProperties().forEach(property -> {
			final BlockProperty<?> blockProperty = InternalBlockProperty.getCachedBlockProperty(property);
			final Object value = blockState.getValue(property);
			
			blockStateReference.set(blockStateReference.get().withObjectValue(blockProperty, value));
		});
		
		return blockStateReference.get();
	}
	
	/* Block Events */
	
	@Override
	public void onPlace(final net.minecraft.block.BlockState newBlockState, final World world, final BlockPos pos, final net.minecraft.block.BlockState oldBlockState, final boolean isMoving) {
		this.block.onBlockPlaced(
				convertBlockState(oldBlockState),
				convertBlockState(newBlockState),
				
				dev.psygamer.wireframe.util.BlockPosition.get(pos),
				dev.psygamer.wireframe.world.World.get(world)
		);
	}
	
	@Override
	public void setPlacedBy(final World world, final BlockPos pos, final net.minecraft.block.BlockState blockState, @Nullable final LivingEntity placer, final ItemStack itemStack) {
		if (placer instanceof PlayerEntity) {
			this.block.onBlockPlacedByPlayer(
					convertBlockState(blockState),
					convertBlockState(blockState),
					
					dev.psygamer.wireframe.util.BlockPosition.get(pos),
					dev.psygamer.wireframe.world.World.get(world), Player.get((PlayerEntity) placer)
			);
		}
	}
	
	@Override
	public void onRemove(final net.minecraft.block.BlockState newBlockState, final World world, final BlockPos pos, final net.minecraft.block.BlockState oldBlockState, final boolean isMoving) {
		super.onRemove(newBlockState, world, pos, oldBlockState, isMoving);
		
		this.block.onBlockRemoved(
				convertBlockState(oldBlockState),
				convertBlockState(newBlockState),
				
				dev.psygamer.wireframe.util.BlockPosition.get(pos),
				dev.psygamer.wireframe.world.World.get(world)
		);
	}
	
	@Override
	public boolean removedByPlayer(final net.minecraft.block.BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final boolean willHarvest, final FluidState fluid) {
		super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
		
		return this.block.onBlockRemovedByPlayer(
				convertBlockState(state),
				convertBlockState(state),
				
				dev.psygamer.wireframe.util.BlockPosition.get(pos),
				dev.psygamer.wireframe.world.World.get(world), Player.get(player)
		);
	}
	
	@Override
	public void tick(final net.minecraft.block.BlockState state, final ServerWorld world, final BlockPos pos, final Random random) {
		this.block.onTick(
				convertBlockState(state),
				
				dev.psygamer.wireframe.util.BlockPosition.get(pos),
				dev.psygamer.wireframe.world.World.get(world)
		);
	}
	
	@Override
	public void randomTick(final net.minecraft.block.BlockState state, final ServerWorld world, final BlockPos pos, final Random random) {
		this.block.onRandomTick(
				convertBlockState(state),
				
				dev.psygamer.wireframe.util.BlockPosition.get(pos),
				dev.psygamer.wireframe.world.World.get(world), random
		);
	}
	
	@Override
	public void stepOn(final World world, final BlockPos pos, final Entity entity) {
		this.block.onEntityStepOnBlock(
				dev.psygamer.wireframe.util.BlockPosition.get(pos),
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.entity.Entity.get(entity)
		);
	}
	
	@Override
	public void fallOn(final World world, final BlockPos pos, final Entity entity, final float distance) {
		super.fallOn(world, pos, entity, distance);
		
		this.block.onEntityFallOnBlock(
				dev.psygamer.wireframe.util.BlockPosition.get(pos),
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.entity.Entity.get(entity), distance
		);
	}
	
	@Override
	public ActionResultType use(final net.minecraft.block.BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult p_225533_6_) {
		return this.block.onUsedByPlayer(
				convertBlockState(state),
				
				dev.psygamer.wireframe.util.BlockPosition.get(pos),
				dev.psygamer.wireframe.world.World.get(world), Player.get(player)
		).getInternal();
	}
	
	@Override
	public void attack(final net.minecraft.block.BlockState state, final World world, final BlockPos pos, final PlayerEntity player) {
		this.block.onAttackedByPlayer(
				convertBlockState(state),
				
				dev.psygamer.wireframe.util.BlockPosition.get(pos),
				dev.psygamer.wireframe.world.World.get(world), Player.get(player)
		);
	}
	
	@Override
	public void onProjectileHit(final World world, final net.minecraft.block.BlockState state, final BlockRayTraceResult hitResult, final ProjectileEntity projectile) {
		this.block.onHitByProjectile(
				convertBlockState(state),
				
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.entity.ProjectileEntity.get(projectile)
		);
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(final net.minecraft.block.BlockState state, final IBlockReader blockReader) {
		return this.block.createBlockEntity(
				convertBlockState(state), BlockReader.get(blockReader)
		);
	}
	
	@Override
	public ItemStack getPickBlock(final net.minecraft.block.BlockState state, final RayTraceResult target, final IBlockReader blockReader, final BlockPos pos, final PlayerEntity player) {
		return this.block.createPickBlockStack(
				convertBlockState(state),
				dev.psygamer.wireframe.util.BlockPosition.get(pos),
				BlockReader.get(blockReader)
		).toInternal();
	}
	
	@Override
	public List<ItemStack> getDrops(final net.minecraft.block.BlockState state, final LootContext.Builder builder) {
		return this.block.createBlockDrops(
						convertBlockState(state)
				).stream()
				.map(dev.psygamer.wireframe.item.ItemStack::toInternal)
				.collect(Collectors.toList());
	}
}
