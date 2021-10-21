package dev.psygamer.wireframe.block.state.property;

import com.google.common.collect.ImmutableSet;

import java.util.Optional;

public class BooleanBlockProperty extends BlockProperty<Boolean> {
	
	public BooleanBlockProperty(final String propertyName) {
		super(propertyName, false);
	}
	
	@Override
	public Optional<Boolean> getValue(final String valueName) {
		if (valueName.equalsIgnoreCase("true"))
			return Optional.of(true);
		if (valueName.equalsIgnoreCase("false"))
			return Optional.of(false);
		
		return Optional.empty();
	}
	
	@Override
	public String getValueName(final Boolean value) {
		return value ? "true" : "false";
	}
	
	@Override
	public ImmutableSet<Boolean> getPossibleValues() {
		return ImmutableSet.of(true, false);
	}
}
