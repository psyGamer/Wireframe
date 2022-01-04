package dev.psygamer.wireframe.internal.world;

import dev.psygamer.wireframe.McNativeKt;
import dev.psygamer.wireframe.WfWrappedKt;
import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.entity.BlockEntity;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.world.BlockReader;
import net.minecraft.world.IBlockReader;

public class InternalBlockReader implements BlockReader {
	
	private final net.minecraft.world.IBlockReader internalBlockReader;
	
	public InternalBlockReader(final IBlockReader internalBlockReader) {
		this.internalBlockReader = internalBlockReader;
	}
	
	@Override
	public Block getBlock(final BlockPosition position) {
		return WfWrappedKt.getWfWrapped(this.internalBlockReader.getBlockState(McNativeKt.getMcNative(position)).getBlock());
	}
	
	@Override
	public BlockState getBlockState(final BlockPosition position) {
		return WfWrappedKt.getWfWrapped(this.internalBlockReader.getBlockState(McNativeKt.getMcNative(position)));
	}
	
	@Override
	public BlockEntity getBlockEntity(final BlockPosition position) {
		return WfWrappedKt.getWfWrapped(this.internalBlockReader.getBlockEntity(McNativeKt.getMcNative(position)));
	}
	
	@Override
	public int getHighestBlockPosition() {
		return 256;
	}
	
	@Override
	public int getLowestBlockPosition() {
		return 0;
	}
}
