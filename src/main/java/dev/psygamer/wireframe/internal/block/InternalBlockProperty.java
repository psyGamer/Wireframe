package dev.psygamer.wireframe.internal.block;

import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.util.collection.FreezableHashMap;
import dev.psygamer.wireframe.util.collection.FreezableMap;
import net.minecraft.state.Property;

import java.util.Collection;
import java.util.Optional;

public class InternalBlockProperty <T extends Comparable<T>> extends Property<T> {
	
	private static final FreezableMap<
			BlockProperty<? extends Comparable<?>>, Property<? extends Comparable<?>>
			> blockPropertyCache = new FreezableHashMap<>();
	
	private final BlockProperty<T> blockProperty;
	
	public InternalBlockProperty(final BlockProperty<T> blockProperty) {
		super(blockProperty.getPropertyName(),
			  (Class<T>) blockProperty.getDefaultValue()
									  .getClass()
		);
		
		this.blockProperty = blockProperty;
		
		blockPropertyCache.put(blockProperty, this);
	}
	
	public static Property<?> getCachedProperty(final BlockProperty<?> blockProperty) {
		return blockPropertyCache.get(blockProperty);
	}
	
	public static BlockProperty<?> getCachedBlockProperty(final Property<?> property) {
		for (final BlockProperty<? extends Comparable<?>> blockProperty : blockPropertyCache.keySet()) {
			final Property<?> value = getCachedProperty(blockProperty);
			
			if (value.equals(property))
				return blockProperty;
		}
		
		return null;
	}
	
	@Override
	public Collection<T> getPossibleValues() {
		return this.blockProperty.getPossibleValues();
	}
	
	@Override
	public String getName(final T value) {
		return this.blockProperty.getValueName(value);
	}
	
	@Override
	public Optional<T> getValue(final String valueName) {
		return this.blockProperty.getValue(valueName);
	}
}
