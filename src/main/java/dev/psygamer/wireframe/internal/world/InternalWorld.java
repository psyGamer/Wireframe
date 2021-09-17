package dev.psygamer.wireframe.internal.world;

import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.world.World;

import net.minecraft.block.*;
import net.minecraft.world.LightType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.Constants;

public class InternalWorld extends InternalBlockReader implements World {
	
	private final net.minecraft.world.World internalWorld;
	
	public InternalWorld(final net.minecraft.world.World internal) {
		super(internal);
		
		this.internalWorld = internal;
	}
	
	@Override
	public boolean isClientSide() {
		return this.internalWorld.isClientSide;
	}
	
	@Override
	public boolean isServerSide() {
		return !this.internalWorld.isClientSide;
	}
	
	@Override
	public void setBlock(final dev.psygamer.wireframe.block.Block block, final BlockPosition position) {
		setBlockState(block.getDefaultBlockState(), position);
	}
	
	@Override
	public void setBlockState(final BlockState blockState, final BlockPosition position) {
		this.internalWorld.setBlock(position.toInternal(), blockState.getInternal(), Constants.BlockFlags.BLOCK_UPDATE);
	}
	
	@Override
	public boolean isReplaceable(final BlockPosition position) {
		final net.minecraft.block.BlockState blockState = this.internalWorld.getBlockState(position.toInternal());
		final net.minecraft.block.Block block = blockState.getBlock();
		
		return blockState.getMaterial().isReplaceable() ||
				
				block instanceof IPlantable ||
				block instanceof LeavesBlock ||
				block instanceof FlowingFluidBlock ||
				block instanceof SnowBlock ||
				
				block == Blocks.SNOW_BLOCK;
	}
	
	@Override
	public void notifyNeighbours(final BlockPosition position) {
		this.internalWorld.updateNeighborsAt(position.toInternal(),
				this.internalWorld.getBlockState(position.toInternal()).getBlock()
		);
	}
	
	@Override
	public void breakBlock(final BlockPosition position) {
		breakBlock(position, true);
	}
	
	@Override
	public void breakBlock(final BlockPosition position, final boolean dropItems) {
		this.internalWorld.destroyBlock(position.toInternal(), dropItems);
	}
	
	@Override
	public boolean isAir(final BlockPosition position) {
		return this.internalWorld.isEmptyBlock(position.toInternal());
	}
	
	@Override
	public boolean isBlock(final dev.psygamer.wireframe.block.Block block, final BlockPosition position) {
		return this.internalWorld.getBlockState(position.toInternal()).getBlock() == block.getInternal();
	}
	
	@Override
	public boolean isLoaded(final BlockPosition position) {
		return this.internalWorld.isLoaded(position.toInternal());
	}
	
	@Override
	public float getBlockLightLevel(final BlockPosition position) {
		return this.internalWorld.getBrightness(LightType.BLOCK, position.toInternal());
	}
	
	@Override
	public float getSkyLightLevel(final BlockPosition position) {
		return this.internalWorld.getBrightness(LightType.SKY, position.toInternal());
	}
	
	@Override
	public long getTime() {
		return this.internalWorld.getDayTime();
	}
	
	@Override
	public long getTicks() {
		return this.internalWorld.getGameTime();
	}
}
