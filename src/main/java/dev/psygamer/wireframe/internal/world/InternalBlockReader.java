package dev.psygamer.wireframe.internal.world;

import dev.psygamer.wireframe.block.BlockFoundation;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.internal.block.InternalBlockFoundation;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.world.BlockReader;
import net.minecraft.world.IBlockReader;

public class InternalBlockReader implements BlockReader {
	
	private final net.minecraft.world.IBlockReader internalBlockReader;
	
	public InternalBlockReader(final IBlockReader internalBlockReader) {
		this.internalBlockReader = internalBlockReader;
	}
	
	@Override
	public int getHighestBlockPosition() {
		return 256;
	}
	
	@Override
	public int getLowestBlockPosition() {
		return 0;
	}
	
	@Override
	public BlockFoundation getBlock(final BlockPosition position) {
		return getBlockState(position).getBlock();
	}
	
	@Override
	public BlockState getBlockState(final BlockPosition position) {
		return InternalBlockFoundation.convertBlockState(this.internalBlockReader.getBlockState(position.toInternal()));
	}
}
