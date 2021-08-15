package dev.psygamer.wireframe.impl.v16.block;

import dev.psygamer.wireframe.api.block.BlockAttributes;
import dev.psygamer.wireframe.api.block.BlockFoundation;
import dev.psygamer.wireframe.api.block.util.IBlockEvents;
import dev.psygamer.wireframe.api.block.util.IBlockCreators;
import dev.psygamer.wireframe.api.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.api.block.state.property.BlockProperty;

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
public class CompiledBlockFoundationImpl16 extends Block {
	
	private static final Map<Block, BlockFoundation> compiledBlocks = new HashMap<>();
	
	private final BlockFoundation block;
	
	private final List<IBlockEvents> blockEvents = new ArrayList<>();
	private final List<IBlockCreators> blockCreators = new ArrayList<>();
	
	public CompiledBlockFoundationImpl16(final BlockFoundation block, final BlockAttributes attributes) {
		super(((BlockAttributesImpl16) attributes).createProperties());
		
		this.block = block;
		
		this.blockEvents.add(block);
		this.blockCreators.add(block);
		
		this.setRegistryName(block.getNamespace().evaluate(), block.getRegistryName());
		
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
			final BlockProperty<?> blockProperty = CompiledBlockPropertyImpl16.getCachedBlockProperty(property);
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
	@SuppressWarnings("deprecation")
	public void onPlace(final BlockState newBlockState, final World world, final BlockPos blockPos, final BlockState oldBlockState, final boolean isMoving) {
		this.blockEvents.forEach(event -> event.onBlockPlaced(
				convertBlockState(oldBlockState),
				convertBlockState(newBlockState),
				
				blockPos, world
		));
		
		super.onPlace(newBlockState, world, blockPos, oldBlockState, isMoving);
	}
	
	@Override
	public void setPlacedBy(final World world, final BlockPos pos, final BlockState blockState, @Nullable final LivingEntity placer, final ItemStack itemStack) {
		if (placer instanceof PlayerEntity) {
			this.blockEvents.forEach(event -> event.onBlockPlacedByPlayer(
					convertBlockState(blockState),
					convertBlockState(blockState),
					
					pos, world, (PlayerEntity) placer
			));
		}
		
		super.setPlacedBy(world, pos, blockState, placer, itemStack);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void onRemove(final BlockState newBlockState, final World world, final BlockPos blockPos, final BlockState oldBlockState, final boolean isMoving) {
		this.blockEvents.forEach(event -> event.onBlockRemoved(
				convertBlockState(oldBlockState),
				convertBlockState(newBlockState),
				
				blockPos, world
		));
		
		super.onRemove(newBlockState, world, blockPos, oldBlockState, isMoving);
	}
	
	@Override
	public boolean removedByPlayer(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final boolean willHarvest, final FluidState fluid) {
		this.blockEvents.forEach(event -> event.onBlockRemovedByPlayer(
				convertBlockState(state),
				convertBlockState(state),
				
				pos, world, player
		));
		
		return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
	}
	
	@Override
	public void tick(final BlockState p_225534_1_, final ServerWorld p_225534_2_, final BlockPos p_225534_3_, final Random p_225534_4_) {
		this.blockEvents.forEach(event -> event.onTick(
				convertBlockState(p_225534_1_),
				
				p_225534_3_, p_225534_2_
		));
		
		super.tick(p_225534_1_, p_225534_2_, p_225534_3_, p_225534_4_);
	}
	
	@Override
	public void randomTick(final BlockState p_225534_1_, final ServerWorld p_225534_2_, final BlockPos p_225534_3_, final Random p_225534_4_) {
		this.blockEvents.forEach(event -> event.onRandomTick(
				convertBlockState(p_225534_1_),
				
				p_225534_3_, p_225534_2_, p_225534_4_
		));
		
		super.tick(p_225534_1_, p_225534_2_, p_225534_3_, p_225534_4_);
	}
	
	@Override
	public void stepOn(final World p_176199_1_, final BlockPos p_176199_2_, final Entity p_176199_3_) {
		this.blockEvents.forEach(event -> event.onEntityStepOnBlock(
				p_176199_2_, p_176199_1_, p_176199_3_
		));
		
		super.stepOn(p_176199_1_, p_176199_2_, p_176199_3_);
	}
	
	@Override
	public void fallOn(final World p_180658_1_, final BlockPos p_180658_2_, final Entity p_180658_3_, final float p_180658_4_) {
		this.blockEvents.forEach(event -> event.onEntityStepOnBlock(
				p_180658_2_, p_180658_1_, p_180658_3_
		));
		
		super.fallOn(p_180658_1_, p_180658_2_, p_180658_3_, p_180658_4_);
	}
	
	@Override
	public ActionResultType use(final BlockState p_225533_1_, final World p_225533_2_, final BlockPos p_225533_3_, final PlayerEntity p_225533_4_, final Hand p_225533_5_, final BlockRayTraceResult p_225533_6_) {
		this.blockEvents.forEach(event -> event.onUsedByPlayer(
				convertBlockState(p_225533_1_),
				
				p_225533_3_, p_225533_2_, p_225533_4_
		));
		
		return super.use(p_225533_1_, p_225533_2_, p_225533_3_, p_225533_4_, p_225533_5_, p_225533_6_);
	}
	
	@Override
	public void attack(final BlockState p_196270_1_, final World p_196270_2_, final BlockPos p_196270_3_, final PlayerEntity p_196270_4_) {
		this.blockEvents.forEach(event -> event.onAttackedByPlayer(
				convertBlockState(p_196270_1_),
				
				p_196270_3_, p_196270_2_, p_196270_4_
		));
		
		super.attack(p_196270_1_, p_196270_2_, p_196270_3_, p_196270_4_);
	}
	
	@Override
	public void onProjectileHit(final World p_220066_1_, final BlockState p_220066_2_, final BlockRayTraceResult p_220066_3_, final ProjectileEntity p_220066_4_) {
		this.blockEvents.forEach(event -> event.onHitByProjectile(
				convertBlockState(p_220066_2_),
				
				p_220066_1_, p_220066_4_
		));
		
		super.onProjectileHit(p_220066_1_, p_220066_2_, p_220066_3_, p_220066_4_);
	}
	
	/* Block Creators */
	
	@Nullable
	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
		this.blockCreators.forEach(event -> event.createBlockEntity(
				convertBlockState(state), world
		));
		
		return super.createTileEntity(state, world);
	}
	
	@Override
	public ItemStack getPickBlock(final BlockState state, final RayTraceResult target, final IBlockReader world, final BlockPos pos, final PlayerEntity player) {
		this.blockCreators.forEach(event -> event.createPickBlockStack(
				convertBlockState(state), pos, world
		));
		
		return super.getPickBlock(state, target, world, pos, player);
	}
	
	@Override
	public List<ItemStack> getDrops(final BlockState p_220076_1_, final LootContext.Builder p_220076_2_) {
		this.blockCreators.forEach(event -> event.createBlockDrops(
				convertBlockState(p_220076_1_), p_220076_2_
		));
		
		return super.getDrops(p_220076_1_, p_220076_2_);
	}
	
	@Override
	public INamedContainerProvider getMenuProvider(final BlockState p_220052_1_, final World p_220052_2_, final BlockPos p_220052_3_) {
		this.blockCreators.forEach(event -> event.createMenuProvider(
				convertBlockState(p_220052_1_), p_220052_3_, p_220052_2_
		));
		
		return super.getMenuProvider(p_220052_1_, p_220052_2_, p_220052_3_);
	}
}
