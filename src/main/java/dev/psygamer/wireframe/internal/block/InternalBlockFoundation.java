package dev.psygamer.wireframe.internal.block;

import dev.psygamer.wireframe.block.BlockAttributes;
import dev.psygamer.wireframe.block.BlockFoundation;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.util.BlockPosition;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.container.INamedContainerProvider;
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

@SuppressWarnings("deprecation")
public class InternalBlockFoundation extends Block {
	
	private static final Map<Block, BlockFoundation> cachedBlocks = new HashMap<>();
	
	private final BlockFoundation block;
	
	public InternalBlockFoundation(final BlockFoundation block, final BlockAttributes attributes) {
		super(attributes.getInternal().createProperties());
		
		this.block = block;
		this.setRegistryName(block.getIdentifier().getNamespace(), block.getIdentifier().getPath());
		
		cachedBlocks.put(this, block);
	}
	
	public static BlockFoundation convertBlock(final Block block) {
		return cachedBlocks.get(block);
	}
	
	public static BlockState convertBlockState(final net.minecraft.block.BlockState blockState) {
		return convertBlockState(convertBlock(blockState.getBlock()), blockState);
	}
	
	public static BlockState convertBlockState(final BlockFoundation block, final net.minecraft.block.BlockState blockState) {
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
				
				new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world
		);
	}
	
	@Override
	public void setPlacedBy(final World world, final BlockPos pos, final net.minecraft.block.BlockState blockState, @Nullable final LivingEntity placer, final ItemStack itemStack) {
		if (placer instanceof PlayerEntity) {
			this.block.onBlockPlacedByPlayer(
					convertBlockState(blockState),
					convertBlockState(blockState),
					
					new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world, (PlayerEntity) placer
			);
		}
	}
	
	@Override
	public void onRemove(final net.minecraft.block.BlockState newBlockState, final World world, final BlockPos pos, final net.minecraft.block.BlockState oldBlockState, final boolean isMoving) {
		this.block.onBlockRemoved(
				convertBlockState(oldBlockState),
				convertBlockState(newBlockState),
				
				new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world
		);
	}
	
	@Override
	public boolean removedByPlayer(final net.minecraft.block.BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final boolean willHarvest, final FluidState fluid) {
		return this.block.onBlockRemovedByPlayer(
				convertBlockState(state),
				convertBlockState(state),
				
				new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world, player
		);
	}
	
	@Override
	public void tick(final net.minecraft.block.BlockState state, final ServerWorld world, final BlockPos pos, final Random random) {
		this.block.onTick(
				convertBlockState(state),
				
				new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world
		);
	}
	
	@Override
	public void randomTick(final net.minecraft.block.BlockState state, final ServerWorld world, final BlockPos pos, final Random random) {
		this.block.onRandomTick(
				convertBlockState(state),
				
				new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world, random
		);
	}
	
	@Override
	public void stepOn(final World world, final BlockPos pos, final Entity entity) {
		this.block.onEntityStepOnBlock(
				new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world, entity
		);
	}
	
	@Override
	public void fallOn(final World world, final BlockPos pos, final Entity entity, final float distance) {
		this.block.onEntityFallOnBlock(
				new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world, entity, distance
		);
	}
	
	@Override
	public ActionResultType use(final net.minecraft.block.BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult p_225533_6_) {
		return this.block.onUsedByPlayer(
				convertBlockState(state),
				
				new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world, player
		).getInternal();
	}
	
	@Override
	public void attack(final net.minecraft.block.BlockState state, final World world, final BlockPos pos, final PlayerEntity plaer) {
		this.block.onAttackedByPlayer(
				convertBlockState(state),
				
				new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world, plaer
		);
	}
	
	@Override
	public void onProjectileHit(final World world, final net.minecraft.block.BlockState state, final BlockRayTraceResult p_220066_3_, final ProjectileEntity projctile) {
		this.block.onHitByProjectile(
				convertBlockState(state),
				
				world, projctile
		);
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(final net.minecraft.block.BlockState state, final IBlockReader world) {
		return this.block.createBlockEntity(
				convertBlockState(state), world
		);
	}
	
	@Override
	public ItemStack getPickBlock(final net.minecraft.block.BlockState state, final RayTraceResult target, final IBlockReader world, final BlockPos pos, final PlayerEntity player) {
		return this.block.createPickBlockStack(
				convertBlockState(state), new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world
		);
	}
	
	@Override
	public List<ItemStack> getDrops(final net.minecraft.block.BlockState state, final LootContext.Builder builder) {
		return this.block.createBlockDrops(
				convertBlockState(state), builder
		);
	}
	
	@Override
	public INamedContainerProvider getMenuProvider(final net.minecraft.block.BlockState state, final World world, final BlockPos pos) {
		return this.block.createMenuProvider(
				convertBlockState(state), new BlockPosition(pos.getX(), pos.getY(), pos.getZ()), world
		);
	}
}
