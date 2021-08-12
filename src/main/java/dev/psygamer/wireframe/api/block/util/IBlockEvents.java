package dev.psygamer.wireframe.api.block.util;

import dev.psygamer.wireframe.api.block.state.BlockPropertyContainer;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;

import java.util.Random;

public interface IBlockEvents {
	
	default boolean onBlockPlaced(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world
	) { return false; }
	
	default boolean onBlockPlacedByPlayer(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) { return false; }
	
	default boolean onBlockRemoved(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world
	) { return false; }
	
	default boolean onBlockRemovedByPlayer(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) { return false; }
	
	default boolean onTick(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world
	) { return false; }
	
	default boolean onRandomTick(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world, final Random random
	) { return false; }
	
	default boolean onEntityStepOnBlock(
			final BlockPos blockPosition, final World world, final Entity entity
	) { return false; }
	
	default boolean onEntityFallOnBlock(
			final BlockPos blockPosition, final World world, final Entity entity
	) { return false; }
	
	default boolean onUsedByPlayer(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) { return false; }
	
	default boolean onAttackedByPlayer(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) { return false; }
	
	default boolean onHitByProjectile(
			final BlockPropertyContainer blockState,
			final World world, final ProjectileEntity projectile
	) { return false; }
}
