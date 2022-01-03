package dev.psygamer.wireframe.internal.block;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.BlockKt;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.block.state.property.BlockProperty;

import java.util.Optional;

public class InternalBlockState implements BlockState {
	
	private final net.minecraft.block.BlockState internal;
	
	public InternalBlockState(final net.minecraft.block.BlockState internal) {
		this.internal = internal;
	}
	
	@Override
	public Block getBlock() {
		return BlockKt.getWfWrapped(this.internal.getBlock());
	}
	
	@Override
	public <T extends Comparable<T>> BlockState setComparableValue(final BlockProperty<T> property, final Comparable<?> value) {
		return BlockState.get(this.internal.setValue(property.getInternal(), (T) value));
	}
	
	@Override
	public <T extends Comparable<T>> BlockState setValue(final BlockProperty<T> property, final T value) {
		return BlockState.get(this.internal.setValue(property.getInternal(), value));
	}
	
	@Override
	public <T extends Comparable<T>> T getValue(final BlockProperty<T> property) {
		return this.internal.getValue(property.getInternal());
	}
	
	@Override
	public <T extends Comparable<T>> Optional<T> getOptionalValue(final BlockProperty<T> property
	) {
		return this.internal.getOptionalValue(property.getInternal());
	}
	
	@Override
	public boolean is(final Block block) {
		return this.getBlock().getMcNative$wireframe() == block.getMcNative$wireframe();
	}
	
	@Override
	public net.minecraft.block.BlockState getInternal() {
		return this.internal;
	}
}
