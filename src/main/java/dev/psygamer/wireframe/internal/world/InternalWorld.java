package dev.psygamer.wireframe.internal.world;

import dev.psygamer.wireframe.block.BlockFoundation;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.internal.block.InternalBlockFoundation;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.world.IWorld;

import net.minecraft.block.*;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.Constants;

public class InternalWorld implements IWorld {
	
	private final World internal;
	
	public InternalWorld(final World internal) {
		this.internal = internal;
	}
	
	@Override
	public boolean isClientSide() {
		return true;
	}
	
	@Override
	public boolean isServerSide() {
		return false;
	}
	
	@Override
	public BlockFoundation getBlock(final BlockPosition position) {
		return getBlockState(position).getBlock();
	}
	
	@Override
	public void setBlock(final BlockFoundation block, final BlockPosition position) {
		setBlockState(block.getDefaultBlockState(), position);
	}
	
	@Override
	public BlockState getBlockState(final BlockPosition position) {
		return InternalBlockFoundation.convertBlockState(this.internal.getBlockState(position.toInternal()));
	}
	
	@Override
	public void setBlockState(final BlockState blockState, final BlockPosition position) {
		this.internal.setBlock(position.toInternal(), blockState.getInternal(), Constants.BlockFlags.BLOCK_UPDATE);
	}
	
	@Override
	public boolean isReplaceable(final BlockPosition position) {
		final net.minecraft.block.BlockState blockState = this.internal.getBlockState(position.toInternal());
		final Block block = blockState.getBlock();
		
		return blockState.getMaterial().isReplaceable() ||
				
				block instanceof IPlantable ||
				block instanceof LeavesBlock ||
				block instanceof FlowingFluidBlock ||
				block instanceof SnowBlock ||
				
				block == Blocks.SNOW_BLOCK;
	}
	
	@Override
	public void notifyNeighbours(final BlockPosition position) {
		this.internal.updateNeighborsAt(position.toInternal(),
				this.internal.getBlockState(position.toInternal()).getBlock()
		);
	}
	
	@Override
	public void breakBlock(final BlockPosition position) {
		breakBlock(position, true);
	}
	
	@Override
	public void breakBlock(final BlockPosition position, final boolean dropItems) {
		this.internal.destroyBlock(position.toInternal(), dropItems);
	}
	
	@Override
	public boolean isAir(final BlockPosition position) {
		return this.internal.isEmptyBlock(position.toInternal());
	}
	
	@Override
	public boolean isBlock(final BlockFoundation block, final BlockPosition position) {
		return this.internal.getBlockState(position.toInternal()).getBlock() == block.getInternal();
	}
	
	@Override
	public boolean isLoaded(final BlockPosition position) {
		return this.internal.isLoaded(position.toInternal());
	}
	
	@Override
	public float getBlockLightLevel(final BlockPosition position) {
		return this.internal.getBrightness(LightType.BLOCK, position.toInternal());
	}
	
	@Override
	public float getSkyLightLevel(final BlockPosition position) {
		return this.internal.getBrightness(LightType.SKY, position.toInternal());
	}
	
	@Override
	public long getTime() {
		return this.internal.getDayTime();
	}
	
	@Override
	public long getTicks() {
		return this.internal.getGameTime();
	}
}
