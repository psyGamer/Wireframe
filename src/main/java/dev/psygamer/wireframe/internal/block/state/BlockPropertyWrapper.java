package dev.psygamer.wireframe.internal.block.state;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import net.minecraft.state.Property;

import java.util.Optional;

public class BlockPropertyWrapper <T extends Comparable<T>> extends BlockProperty<T> {
	
	private final Property<T> property;
	
	private T defaultValue;
	
	public BlockPropertyWrapper(final Property<T> property) {
		super(property.getName(), Lists.newArrayList(property.getPossibleValues()).get(0));
		
		this.property = property;
	}
	
	@Override
	public Optional<T> getValue(final String valueName) {
		return this.property.getValue(valueName);
	}
	
	@Override
	public String getValueName(final T value) {
		return this.property.getName(value);
	}
	
	@Override
	public ImmutableSet<T> getPossibleValues() {
		return ImmutableSet.copyOf(this.property.getPossibleValues());
	}
	
	@Override
	public Property<T> getInternal() {
		return this.property;
	}
}
