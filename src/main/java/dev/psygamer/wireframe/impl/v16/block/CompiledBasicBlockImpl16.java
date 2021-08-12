package dev.psygamer.wireframe.impl.v16.block;

import dev.psygamer.wireframe.api.block.BasicBlock;
import dev.psygamer.wireframe.api.block.BlockEvents;

import dev.psygamer.wireframe.api.block.state.BlockPropertyContainer;
import dev.psygamer.wireframe.api.block.state.property.BlockProperty;
import dev.psygamer.wireframe.util.collection.FreezableArrayList;
import dev.psygamer.wireframe.util.collection.FreezableList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

import java.util.concurrent.atomic.AtomicReference;

public class CompiledBasicBlockImpl16 extends Block {
	
	private final BasicBlock block;
	private final FreezableList<BlockEvents> blockEvents = new FreezableArrayList<>();
	
	public CompiledBasicBlockImpl16(final BasicBlock block, final Properties properties) {
		super(properties);
		
		this.block = block;
		this.blockEvents.add(block);
	}
	
	private BlockPropertyContainer convertBlockState(final BlockState blockState) {
		final AtomicReference<BlockPropertyContainer> propertyContainerReference = new AtomicReference<>(
				this.block.getDefaultBlockPropertyContainer()
		);
		
		blockState.getProperties().forEach(property -> {
			final BlockProperty<?> blockProperty = CompiledBlockPropertyImpl16.getCachedBlockProperty(property);
			final Object value = blockState.getValue(property);
			
			propertyContainerReference.set(propertyContainerReference.get().withObjectValue(blockProperty, value));
		});
		
		return propertyContainerReference.get();
	}

//	@Override
//	@SuppressWarnings("deprecation")
//	public void onPlace(final BlockState blockState, final World world, final BlockPos blockPos, final BlockState oldBlockState, final boolean isMoving) {
//		this.blockEvents.forEach(event -> event.onBlockPlaced(
//
//		));
//
//		super.onPlace(blockState, world, blockPos, oldBlockState, isMoving);
//	}
}
