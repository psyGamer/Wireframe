package dev.psygamer.wireframe.internal.block.state;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import net.minecraft.state.Property;

import java.util.Optional;

public class BlockPropertyWrapper <T extends Comparable<T>> extends BlockProperty<T> {
	
	private final Property<T> property;
	
	private T defaultValue;
	
	public BlockPropertyWrapper(final Property<T> property) {
		super(property.getName());
		
		this.property = property;
		this.defaultValue = Lists.newArrayList(this.property.getPossibleValues()).get(0);
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
	public T getDefaultValue() {
		return this.defaultValue;
	}
	
	@Override
	protected void setDefaultValue(final T defaultValue) {
		if (!getPossibleValues().contains(defaultValue))
			throw new IllegalArgumentException("Default value does not exist in " + this.property);
		
		this.defaultValue = defaultValue;
	}
	
	@Override
	public ImmutableList<T> getPossibleValues() {
		return ImmutableList.copyOf(this.property.getPossibleValues());
	}
	
	@Override
	public void freeze() {
	
	}
	
	@Override
	public boolean isFrozen() {
		return true; // Internal Properties are always immutable.
	}
}
