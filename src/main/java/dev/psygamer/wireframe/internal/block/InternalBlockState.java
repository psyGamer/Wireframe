package dev.psygamer.wireframe.internal.block;

import dev.psygamer.wireframe.block.state.property.BlockProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.Property;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class InternalBlockState extends net.minecraft.state.StateContainer<Block, BlockState> {
	
	public InternalBlockState(final dev.psygamer.wireframe.block.state.BlockState blockState) {
		super(
				Block::defaultBlockState,
				
				blockState.getBlock()
						  .getInternal(), BlockState::new,
				
				mapProperties(blockState.getValuePairs())
		);
	}
	
	private static Map<String, Property<?>> mapProperties(final Set<BlockProperty.ValuePair<?>> valuePairs) {
		final Map<String, Property<?>> properties = new LinkedHashMap<>();
		
		valuePairs.forEach(valuePair -> properties.put(
								   valuePair.getProperty()
											.getPropertyName(),
								   valuePair.getProperty()
											.getInternal()
						   )
		);
		
		return properties;
	}
}
