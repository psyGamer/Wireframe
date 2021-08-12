package dev.psygamer.wireframe.api.block;

import dev.psygamer.wireframe.api.block.state.BlockPropertyContainer;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;

import java.util.Random;

public class BlockEvents {
	
	public void onBlockPlaced(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world
	) { }
	
	public void onBlockPlacedByPlayer(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) { }
	
	public void onBlockRemoved(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world
	) { }
	
	public void onBlockRemovedByPlayer(
			final BlockPropertyContainer oldBlockState,
			final BlockPropertyContainer newBlockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) { }
	
	public void onTick(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world
	) { }
	
	public void onRandomTick(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world, final Random random
	) { }
	
	public void onEntityStepOnBlock(
			final BlockPos blockPosition, final World world, final Entity entity
	) { }
	
	public void onEntityFallOnBlock(
			final BlockPos blockPosition, final World world, final Entity entity
	) { }
	
	public void onUsedByPlayer(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) { }
	
	public void onAttackedByPlayer(
			final BlockPropertyContainer blockState,
			final BlockPos blockPosition, final World world, final PlayerEntity player
	) { }
	
	public void onHitByProjectile(
			final BlockPropertyContainer blockState,
			final World world, final ProjectileEntity projectile
	) { }
}
