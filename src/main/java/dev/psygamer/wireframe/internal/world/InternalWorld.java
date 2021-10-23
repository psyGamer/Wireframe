package dev.psygamer.wireframe.internal.world;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.world.World;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.world.LightType;

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
	public boolean setBlock(final dev.psygamer.wireframe.block.Block block, final BlockPosition position) {
		return setBlock(block, position, UpdateFlag.BLOCK_UPDATE);
	}
	
	@Override
	public boolean setBlock(final Block block, final BlockPosition position, final UpdateFlag updateFlag) {
		return setBlockState(block.getDefaultBlockState(), position);
	}
	
	@Override
	public boolean setBlockState(final BlockState blockState, final BlockPosition position) {
		return setBlockState(blockState, position, UpdateFlag.BLOCK_UPDATE);
	}
	
	@Override
	public boolean setBlockState(final BlockState blockState, final BlockPosition position, final UpdateFlag updateFlag) {
		return this.internalWorld.setBlock(position.toInternal(), blockState.getInternal(), updateFlag.getInternalFlag());
	}
	
	@Override
	public boolean isReplaceable(final BlockPosition position) {
		final net.minecraft.block.BlockState blockState = this.internalWorld.getBlockState(position.toInternal());
		final net.minecraft.block.Block block = blockState.getBlock();
		
		return blockState.getMaterial()
						 .isReplaceable() || block instanceof FlowingFluidBlock;
	}
	
	@Override
	public void notifyNeighbours(final BlockPosition position) {
		this.internalWorld.updateNeighborsAt(
				position.toInternal(),
				
				this.internalWorld.getBlockState(position.toInternal())
								  .getBlock()
		);
	}
	
	@Override
	public void notifyNeighbours(final BlockPosition position, final dev.psygamer.wireframe.block.Block block) {
		this.internalWorld.updateNeighborsAt(position.toInternal(), block.getInternal());
	}
	
	@Override
	public boolean breakBlock(final BlockPosition position) {
		return breakBlock(position, true);
	}
	
	@Override
	public boolean breakBlock(final BlockPosition position, final boolean dropItems) {
		return this.internalWorld.destroyBlock(position.toInternal(), dropItems);
	}
	
	@Override
	public boolean isAir(final BlockPosition position) {
		return this.internalWorld.isEmptyBlock(position.toInternal());
	}
	
	@Override
	public boolean isBlock(final dev.psygamer.wireframe.block.Block block, final BlockPosition position) {
		return this.internalWorld.getBlockState(position.toInternal())
								 .getBlock() == block.getInternal();
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
