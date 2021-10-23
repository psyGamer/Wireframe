package dev.psygamer.wireframe.internal.block;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.block.state.BlockStateDefinition;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import net.minecraft.state.StateContainer;

import java.util.stream.Collectors;

public class InternalBlockStateDefinition implements BlockStateDefinition {
	
	private final StateContainer<net.minecraft.block.Block, net.minecraft.block.BlockState> stateContainer;
	
	public InternalBlockStateDefinition(
			final StateContainer<net.minecraft.block.Block, net.minecraft.block.BlockState> stateContainer
	) {
		this.stateContainer = stateContainer;
	}
	
	@Override
	public Block getBlock() {
		return Block.get(this.stateContainer.getOwner());
	}
	
	@Override
	public BlockState getDefaultBlockState() {
		return getPossibleStates().get(0);
	}
	
	@Override
	public ImmutableList<BlockState> getPossibleStates() {
		return ImmutableList.copyOf(
				this.stateContainer.getPossibleStates().stream()
								   .map(BlockState::get)
								   .collect(Collectors.toList())
		);
		
	}
	
	@Override
	public BlockProperty<?> getProperty(final String propertyName) {
		return BlockProperty.get(this.stateContainer.getProperty(propertyName));
	}
	
	@Override
	public StateContainer<net.minecraft.block.Block, net.minecraft.block.BlockState> getInternal() {
		return this.stateContainer;
	}
}
