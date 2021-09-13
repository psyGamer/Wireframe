package dev.psygamer.wireframe.internal.block;

import dev.psygamer.wireframe.block.BlockAttributes;
import dev.psygamer.wireframe.block.BlockFoundation;
import dev.psygamer.wireframe.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.block.util.IBlockCreators;
import dev.psygamer.wireframe.block.util.IBlockEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
	
	private static final Map<Block, BlockFoundation> compiledBlocks = new HashMap<>();
	
	private final BlockFoundation block;
	
	private final List<IBlockEvents> blockEvents = new ArrayList<>();
	private final List<IBlockCreators> blockCreators = new ArrayList<>();
	
	public InternalBlockFoundation(final BlockFoundation block, final BlockAttributes attributes) {
		super(attributes.getInternal().createProperties());
		
		this.block = block;
		
		this.blockEvents.add(block);
		this.blockCreators.add(block);
		
		this.setRegistryName(block.getIdentifier().getNamespace(), block.getIdentifier().getPath());
		
		compiledBlocks.put(this, block);
	}
	
	public static BlockFoundation convertBlock(final Block block) {
		return compiledBlocks.get(block);
	}
	
	public static BlockPropertyContainer convertBlockState(final BlockFoundation block, final BlockState blockState) {
		final AtomicReference<BlockPropertyContainer> propertyContainerReference = new AtomicReference<>(
				block.getDefaultBlockPropertyContainer()
		);
		
		blockState.getProperties().forEach(property -> {
			final BlockProperty<?> blockProperty = InternalBlockProperty.getCachedBlockProperty(property);
			final Object value = blockState.getValue(property);
			
			propertyContainerReference.set(propertyContainerReference.get().withObjectValue(blockProperty, value));
		});
		
		return propertyContainerReference.get();
	}
	
	private BlockPropertyContainer convertBlockState(final BlockState blockState) {
		return convertBlockState(this.block, blockState);
	}
	
	/* Block Events */
	
	@Override
	public void onPlace(final BlockState newBlockState, final World world, final BlockPos blockPos, final BlockState oldBlockState, final boolean isMoving) {
		this.block.onBlockPlaced(
				convertBlockState(oldBlockState),
				convertBlockState(newBlockState),
				
				blockPos, world
		);
	}
	
	@Override
	public void setPlacedBy(final World world, final BlockPos pos, final BlockState blockState, @Nullable final LivingEntity placer, final ItemStack itemStack) {
		if (placer instanceof PlayerEntity) {
			this.block.onBlockPlacedByPlayer(
					convertBlockState(blockState),
					convertBlockState(blockState),
					
					pos, world, (PlayerEntity) placer
			);
		}
	}
	
	@Override
	public void onRemove(final BlockState newBlockState, final World world, final BlockPos blockPos, final BlockState oldBlockState, final boolean isMoving) {
		this.block.onBlockRemoved(
				convertBlockState(oldBlockState),
				convertBlockState(newBlockState),
				
				blockPos, world
		);
	}
	
	@Override
	public boolean removedByPlayer(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final boolean willHarvest, final FluidState fluid) {
		return this.block.onBlockRemovedByPlayer(
				convertBlockState(state),
				convertBlockState(state),
				
				pos, world, player
		);
	}
	
	@Override
	public void tick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random) {
		this.block.onTick(
				convertBlockState(state),
				
				pos, world
		);
	}
	
	@Override
	public void randomTick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random) {
		this.block.onRandomTick(
				convertBlockState(state),
				
				pos, world, random
		);
	}
	
	@Override
	public void stepOn(final World world, final BlockPos pos, final Entity entity) {
		this.block.onEntityStepOnBlock(
				pos, world, entity
		);
	}
	
	@Override
	public void fallOn(final World world, final BlockPos pos, final Entity entity, final float distance) {
		this.block.onEntityFallOnBlock(
				pos, world, entity, distance
		);
	}
	
	@Override
	public ActionResultType use(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult p_225533_6_) {
		return this.block.onUsedByPlayer(
				convertBlockState(state),
				
				pos, world, player
		).getInternal();
	}
	
	@Override
	public void attack(final BlockState p_196270_1_, final World p_196270_2_, final BlockPos p_196270_3_, final PlayerEntity p_196270_4_) {
		this.block.onAttackedByPlayer(
				convertBlockState(p_196270_1_),
				
				p_196270_3_, p_196270_2_, p_196270_4_
		);
	}
	
	@Override
	public void onProjectileHit(final World p_220066_1_, final BlockState p_220066_2_, final BlockRayTraceResult p_220066_3_, final ProjectileEntity p_220066_4_) {
		this.block.onHitByProjectile(
				convertBlockState(p_220066_2_),
				
				p_220066_1_, p_220066_4_
		);
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
		return this.block.createBlockEntity(
				convertBlockState(state), world
		);
	}
	
	@Override
	public ItemStack getPickBlock(final BlockState state, final RayTraceResult target, final IBlockReader world, final BlockPos pos, final PlayerEntity player) {
		return this.block.createPickBlockStack(
				convertBlockState(state), pos, world
		);
	}
	
	@Override
	public List<ItemStack> getDrops(final BlockState state, final LootContext.Builder builder) {
		return this.block.createBlockDrops(
				convertBlockState(state), builder
		);
	}
	
	@Override
	public INamedContainerProvider getMenuProvider(final BlockState state, final World world, final BlockPos pos) {
		return this.block.createMenuProvider(
				convertBlockState(state), pos, world
		);
	}
}
